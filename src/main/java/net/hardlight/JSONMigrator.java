package net.hardlight;

import com.fsoinstaller.common.BaseURL;
import com.fsoinstaller.common.InstallerNode;
import com.fsoinstaller.common.InstallerNodeFactory;
import com.fsoinstaller.common.InstallerNodeParseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.hardlight.json.EntrySerializer;
import net.hardlight.json.HashTripleSerializer;
import net.hardlight.json.InstallFileSerializer;
import net.hardlight.json.RenamePairSerializer;

import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONMigrator
{
    private static String fixPath(String path)
    {
        return path.replace('\\', '/');
    }

    private List<Entry> entries = new ArrayList<>();

    private void addEntries(InstallerNode node, Entry dependency)
    {
        Entry e = new Entry();
        e.setName(node.getName());
        e.setDescription(node.getDescription());
        e.setFolder(fixPath(node.getFolder()));
        e.setNote(node.getNote());
        e.setVersion(node.getVersion());

        for (InstallerNode.InstallUnit installUnit : node.getInstallList())
        {
            for (String s : installUnit.getFileList())
            {
                InstallFile file = new InstallFile();
                file.setName(s);

                for (BaseURL baseURL : installUnit.getBaseURLList())
                {
                    try
                    {
                        file.addMirror(baseURL.toURL(s).toString());
                    }
                    catch (MalformedURLException e1)
                    {
                        e1.printStackTrace();
                    }
                }

                e.addInstallFile(file);
            }
        }

        for (String deletePath : node.getDeleteList())
        {
            e.addDeletePath(fixPath(deletePath));
        }

        for (InstallerNode.HashTriple hashTriple : node.getHashList())
        {
            e.addHash(new HashTriple(hashTriple.getType(), fixPath(hashTriple.getFilename()), hashTriple.getHash()));
        }

        for (InstallerNode.RenamePair renamePair : node.getRenameList())
        {
            e.addRenamePair(new RenamePair(fixPath(renamePair.getFrom()), fixPath(renamePair.getTo())));
        }

        if (dependency != null)
        {
            e.addDependency(dependency);
        }

        addEntry(e);

        for (InstallerNode n : node.getChildren())
        {
            addEntries(n, e);
        }
    }

    public void addEntry(Entry e)
    {
        for (Entry entry : entries)
        {
            if (entry.getName().equals(e.getName()))
            {
                return;
            }
        }

        entries.add(e);
    }

    public List<Entry> getEntries()
    {
        return Collections.unmodifiableList(entries);
    }

    public void migrate(Reader reader) throws IOException, InstallerNodeParseException
    {
        InstallerNode node;

        while ((node = InstallerNodeFactory.readNode(reader)) != null)
        {
            addEntries(node, null);
        }
    }

    public String toJSON()
    {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Entry.class, new EntrySerializer());
        builder.registerTypeAdapter(HashTriple.class, new HashTripleSerializer());
        builder.registerTypeAdapter(RenamePair.class, new RenamePairSerializer());
        builder.registerTypeAdapter(InstallFile.class, new InstallFileSerializer());
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        return gson.toJson(entries);
    }
}
