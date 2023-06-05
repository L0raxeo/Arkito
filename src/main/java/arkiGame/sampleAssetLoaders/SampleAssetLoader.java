package arkiGame.sampleAssetLoaders;

import l0raxeo.arki.engine.assetFiles.AssetPool;
import l0raxeo.arki.engine.loaders.AssetLoader;
import l0raxeo.arki.engine.loaders.LoadingScreen;
import l0raxeo.arki.engine.ui.GuiText;
import l0raxeo.arki.renderer.AppWindow;

import java.awt.*;

public class SampleAssetLoader implements AssetLoader
{

    @Override
    public void loadAssets(long minDurationMillis)
    {
        createLoadingScreen(minDurationMillis);

        AssetPool.indexBufferedImage("sampleTexture", "assets/samples/textures/sample_texture.png");
        AssetPool.getAudioClip("sample_audio", "assets/samples/audios/sample_audio.wav", 0);
        AssetPool.indexFont("sampleFont16", "assets/samples/fonts/default_font.ttf", 16);
        AssetPool.indexFont("sampleFont24", "assets/samples/fonts/default_font.ttf", 24);
    }

    @Override
    public void unloadAssets()
    {
        AssetPool.unloadAllBufferedImages();
        AssetPool.unloadAllFonts();
        AssetPool.unloadAllAudioClips();
    }

    private void createLoadingScreen(long minDurationMillis)
    {
        LoadingScreen.load(minDurationMillis, g -> {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, AppWindow.WINDOW_WIDTH, AppWindow.WINDOW_HEIGHT);
            GuiText.drawString(
                    g,
                    "Loading...",
                    AppWindow.WINDOW_WIDTH / 2,
                    AppWindow.WINDOW_HEIGHT / 2,
                    true,
                    Color.WHITE,
                    AssetPool.getFont("sampleFont24")
            );
        });
    }

}
