package com.cubecrusher.trancejourney;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Locale;

public class PrivacyPolicyScreen extends ScreenAdapter {

    private OrthographicCamera camera;
    private Dialog dialog;
    private Settings settings;
    private Stage stage;
    private String noticeTitle, noticeDescr, okBtn;
    private Skin skin;
    private SpriteBatch batch;

    // Note: This screen will probably be unneeded. It doesn't work as it is anyway, and making it work would mean
    // me becoming crazy. The code not only of this screen, but of the entire game is too abhorrently shitty to improve it.
    // If you wish to, you can figure it out your own self. I'm done. I'll be milking this monster, but I'll never groom it.

    public PrivacyPolicyScreen(OrthographicCamera camera){
        this.camera = camera;
        this.camera.position.set(new Vector3(TrJr.INSTANCE.getScrW() / 2f, TrJr.INSTANCE.getScrH() / 2f, 0));
        this.batch = new SpriteBatch();
        this.settings = new Settings();
        System.out.println("INFO: PrivacyPolicyScreen called.");

        if ((Locale.getDefault().getLanguage()).equals("ru")) {
            noticeTitle = "Внимание";
            noticeDescr = "Продолжая, вы соглашаетесь с Политикой Конфиденциальности, указанной на cubecrusher.ru/trjr-privacy-policy\n\n=== v1.3a ===\n- Добавлено окно Политики Конфиденциальности/Списка изменений\n- Исправлены недочёты\n- Добавлены счётчики FPS (F:) и скорости (V:)\n\n=== v1.2a (10 Сент 2021) ===\n- Первый релиз";
        }
        else {
            noticeTitle = "Warning";
            noticeDescr = "By continuing, you accept the Privacy Policy found at cubecrusher.ru/trjr-privacy-policy\n\n=== v1.0b ===\n- Added Privacy Policy/Changelog screen\n- Bug fixes\n- Added FPS (F:) and speed (V:) counters\n\n=== v1.2a (Sep 10 2021) ===\n- Initial release";
        }
        okBtn = "OK";

        skin = new Skin(Gdx.files.internal("ui/skin/dark-xhdpi/Holo-dark-xhdpi.json"),new TextureAtlas(Gdx.files.internal("ui/skin/dark-xhdpi/Holo-dark-xhdpi.atlas")));

        dialog = new Dialog(noticeTitle, skin){
            public void result(Object obj) {
                settings.setChangelogViewed(true);
                TrJr.INSTANCE.setScreen(new EpilepsyScreen(camera));
            }
        };
        dialog.text(noticeDescr);
        dialog.button(okBtn, true); //sends "true" as the result
        dialog.key(Input.Keys.ENTER, true); //sends "true" when the ENTER key is pressed

        dialog.show(stage);

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(dialog);
    }

    public void create(){

    }

    public void update(){
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0.3f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act(delta);
        stage.draw();
        batch.end();
    }

    @Override
    public void pause(){

    }

    @Override
    public void dispose(){

    }

    @Override
    public void hide(){

    }
}
