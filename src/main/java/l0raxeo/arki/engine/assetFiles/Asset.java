package l0raxeo.arki.engine.assetFiles;

public class Asset<Resource>
{

    private final Resource resource;
    private final String path;

    public Asset(String path, Resource resource)
    {
        this.resource = resource;
        this.path = path;
    }

    public Resource getResource()
    {
        return resource;
    }

    public String getPath()
    {
        return path;
    }

}
