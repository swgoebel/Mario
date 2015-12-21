package com.scottg.mariogame;

/**
 * Created by Babbas on 6/27/2015.
 */

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.scottg.framework.Game;
import com.scottg.framework.Graphics;
import com.scottg.framework.Input.TouchEvent;
import com.scottg.framework.Screen;

import java.util.List;

public class MainMenuScreen extends Screen {


    public MainMenuScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.gameWin = game.getAudio().createMusic("sounds/world_clear.mp3");
        Assets.marioDead = game.getAudio().createMusic("sounds/mariodie.mp3");
        Assets.gameOverMusic = game.getAudio().createMusic("sounds/gameover.mp3");
        Assets.theme = game.getAudio().createMusic("menutheme.mp3");
        Assets.stageClear = game.getAudio().createMusic("sounds/stage_clear.mp3");
        Assets.themeFast = game.getAudio().createMusic("sounds/themeFast.mp3");

        Assets.theme.pause();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {

//                if (inBounds(event, 25, 160, 130, 205)) {
                    game.setScreen(new GameScreen(game));
//                }

            }
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        return event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1;
    }

    @Override
    public void paint(float deltaTime) {
        Paint paint3 = new Paint();
        paint3.setTextSize(8);
        paint3.setTextAlign(Paint.Align.CENTER);

      Typeface typeface = Typeface.createFromAsset(game.getFileIO().assetManager(), "fonts/emulogic.ttf");


        paint3.setTypeface(typeface);
        paint3.setAntiAlias(true);
        paint3.setColor(Color.WHITE);
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("MARIO", 125, 15, paint3);
        g.drawString("WORLD", 250, 15, paint3);
        g.drawString("TIME", 300, 15, paint3);
        g.drawString("000000", 128, 28, paint3);
        g.drawString("00", 200, 28, paint3);
        g.drawString("1-1", 250, 28, paint3);
        g.drawString("WORLD 1-1", 210, 75, paint3);
        g.drawString("x  1", 220, 115, paint3);
        g.drawImage(Assets.marioSmallStandRight, 175, 100);
    }



    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        android.os.Process.killProcess(android.os.Process.myPid());

    }
}

