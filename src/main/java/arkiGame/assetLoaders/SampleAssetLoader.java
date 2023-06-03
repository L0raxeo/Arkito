package arkiGame.assetLoaders;

import l0raxeo.arki.engine.assetFiles.AssetPool;
import l0raxeo.arki.engine.loaders.AssetLoader;
import l0raxeo.arki.engine.loaders.LoadingScreen;

public class SampleAssetLoader implements AssetLoader
{

    @Override
    public void loadAssets(long minDurationMillis)
    {
        LoadingScreen.load(minDurationMillis);
        AssetPool.getBufferedImage("assets/samples/textures/sample_texture.png");
        AssetPool.getAudioClip("sample_audio", "assets/samples/audios/sample_audio.wav", 0);
        AssetPool.getFont("assets/samples/fonts/default_font.ttf", 16);
    }

    @Override
    public void unloadAssets()
    {
        AssetPool.unloadAllBufferedImages();
        AssetPool.unloadAllFonts();
        AssetPool.unloadAllBufferedImages();
    }

}
