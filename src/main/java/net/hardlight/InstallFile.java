package net.hardlight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstallFile
{
    private final List<String> downloadMirros = new ArrayList<>();

    private String installPath = ".";

    private String name;

    public void addMirror(String url)
    {
        downloadMirros.add(url);
    }

    public List<String> getDownloadMirros()
    {
        return Collections.unmodifiableList(downloadMirros);
    }

    public String getInstallPath()
    {
        return installPath;
    }

    public void setInstallPath(String installPath)
    {
        this.installPath = installPath;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
