package l0raxeo.arki.engine.loaders;

public interface AssetLoader
{

    AssetLoader loadAssets(long minDurationMillis);

    void unloadAssets();

}
