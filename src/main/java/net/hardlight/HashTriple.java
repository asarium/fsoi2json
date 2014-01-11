package net.hardlight;

public class HashTriple
{
    private String file;

    private String type;

    private String value;

    public HashTriple(String type, String file, String value)
    {
        this.type = type;
        this.file = file;
        this.value = value;
    }

    public String getFile()
    {
        return file;
    }

    public void setFile(String file)
    {
        this.file = file;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
