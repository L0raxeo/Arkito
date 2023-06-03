package l0raxeo.arki.engine.loaders;

public interface AssetLoader
{

    void loadAssets(long minDurationMillis);

    void unloadAssets();

}
