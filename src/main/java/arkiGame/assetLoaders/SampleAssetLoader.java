package arkiGame.assetLoaders;

import l0raxeo.arki.engine.dataStructure.AssetPool;
import l0raxeo.arki.engine.dataStructure.assetLoaders.AssetLoader;
import l0raxeo.arki.engine.dataStructure.assetLoaders.LoadingScreen;

public class SampleAssetLoader implements AssetLoader
{

    @Override
    public void loadAssets(long minDurationMillis)
    {
        LoadingScreen.load(minDurationMillis);
        AssetPool.getBufferedImage("assets/samples/textures/sample_texture.png");
    }

    @Override
    public void unloadAssets()
    {
        AssetPool.unloadAllBufferedImages();
    }

}
