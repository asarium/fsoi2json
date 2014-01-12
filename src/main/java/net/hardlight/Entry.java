package net.hardlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entry
{
    @SerializedName("delete")
    private final List<String> deletePaths = new ArrayList<>();

    private final List<String> dependencies = new ArrayList<>();

    @SerializedName("hashes")
    private final List<HashTriple> hashList = new ArrayList<>();

    private final List<InstallFile> installFiles = new ArrayList<>();

    private final List<RenamePair> renamePairs = new ArrayList<>();

    private String description;

    private String folder;

    private String name;

    private String note;

    private String version;

    public void addDeletePath(String deletePath)
    {
        deletePaths.add(deletePath);
    }

    public void addDependency(Entry dependency)
    {
        dependencies.add(dependency.getName());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addHash(HashTriple hashTriple)
    {
        hashList.add(hashTriple);
    }

    public void addInstallFile(InstallFile file)
    {
        installFiles.add(file);
    }

    public void addRenamePair(RenamePair renamePair)
    {
        renamePairs.add(renamePair);
    }

    public List<String> getDeletePaths()
    {
        return Collections.unmodifiableList(deletePaths);
    }

    public List<String> getDependencies()
    {
        return Collections.unmodifiableList(dependencies);
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getFolder()
    {
        return folder;
    }

    public void setFolder(String folder)
    {
        this.folder = folder;
    }

    public List<HashTriple> getHashList()
    {
        return hashList;
    }

    public List<InstallFile> getInstallFiles()
    {
        return Collections.unmodifiableList(installFiles);
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public List<RenamePair> getRenamePairs()
    {
        return Collections.unmodifiableList(renamePairs);
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }
}
