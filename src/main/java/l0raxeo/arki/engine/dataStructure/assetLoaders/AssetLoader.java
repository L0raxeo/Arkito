package l0raxeo.arki.engine.dataStructure.assetLoaders;

public interface AssetLoader
{

    void loadAssets(long minDurationMillis);

    void unloadAssets();

}
