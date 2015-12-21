package com.scottg.mariogame;

/**
 * Created by Babbas on 6/27/2015.
 */
import android.graphics.Color;

import com.scottg.framework.Game;
import com.scottg.framework.Graphics;
import com.scottg.framework.Graphics.ImageFormat;
import com.scottg.framework.Screen;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {

        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();

        Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);

        Assets.background = g.newImage("Level/background.png", ImageFormat.RGB565);
        Assets.background2 = g.newImage("Level/background2.png", ImageFormat.RGB565);
        Assets.character = g.newImage("character.png", ImageFormat.ARGB4444);
        Assets.character2 = g.newImage("character2.png", ImageFormat.ARGB4444);
        Assets.character3  = g.newImage("character3.png", ImageFormat.ARGB4444);
        Assets.characterJump = g.newImage("jumped.png", ImageFormat.ARGB4444);
        Assets.characterDown = g.newImage("down.png", ImageFormat.ARGB4444);

        Assets.marioBigStandLeft = g.newImage("Mario/Big_Mario_Look_Left.png", ImageFormat.ARGB4444);
        Assets.marioBigStandRight = g.newImage("Mario/Big_Mario_Look_Right.png", ImageFormat.ARGB4444);
        Assets.marioBigDuckLeft = g.newImage("Mario/Big_Mario_Duck_Left.png", ImageFormat.ARGB4444);
        Assets.marioBigDuckRight = g.newImage("Mario/Big_Mario_Duck_Right.png", ImageFormat.ARGB4444);
        Assets.marioBigJumpLeft = g.newImage("Mario/Big_Mario_Jump_Left.png", ImageFormat.ARGB4444);
        Assets.marioBigJumpRight = g.newImage("Mario/Big_Mario_Jump_Right.png", ImageFormat.ARGB4444);
        Assets.marioBigWalkLeft1 = g.newImage("Mario/Big_Mario_Walk_Left_1.png", ImageFormat.ARGB4444);
        Assets.marioBigWalkLeft2 = g.newImage("Mario/Big_Mario_Walk_Left_2.png", ImageFormat.ARGB4444);
        Assets.marioBigWalkLeft3 = g.newImage("Mario/Big_Mario_Walk_Left_3.png", ImageFormat.ARGB4444);
        Assets.marioBigWalkRight1 = g.newImage("Mario/Big_Mario_Walk_Right_1.png", ImageFormat.ARGB4444);
        Assets.marioBigWalkRight2 = g.newImage("Mario/Big_Mario_Walk_Right_2.png", ImageFormat.ARGB4444);
        Assets.marioBigWalkRight3 = g.newImage("Mario/Big_Mario_Walk_Right_3.png", ImageFormat.ARGB4444);
        Assets.marioSmallStandLeft = g.newImage("Mario/Small_Mario_Look_Left.png", ImageFormat.ARGB4444);
        Assets.marioSmallStandRight = g.newImage("Mario/Small_Mario_Look_Right.png", ImageFormat.ARGB4444);

        Assets.marioSmallJumpLeft = g.newImage("Mario/Small_Mario_Jump_Left.png", ImageFormat.ARGB4444);
        Assets.marioSmallJumpRight = g.newImage("Mario/Small_Mario_Jump_Right.png", ImageFormat.ARGB4444);
        Assets.marioSmallWalkLeft1 = g.newImage("Mario/Small_Mario_Walk_Left_1.png", ImageFormat.ARGB4444);
        Assets.marioSmallWalkLeft2 = g.newImage("Mario/Small_Mario_Walk_Left_2.png", ImageFormat.ARGB4444);
        Assets.marioSmallWalkLeft3 = g.newImage("Mario/Small_Mario_Walk_Left_3.png", ImageFormat.ARGB4444);
        Assets.marioSmallWalkRight1 = g.newImage("Mario/Small_Mario_Walk_Right_1.png", ImageFormat.ARGB4444);
        Assets.marioSmallWalkRight2 = g.newImage("Mario/Small_Mario_Walk_Right_2.png", ImageFormat.ARGB4444);
        Assets.marioSmallWalkRight3 = g.newImage("Mario/Small_Mario_Walk_Right_3.png", ImageFormat.ARGB4444);
        Assets.blank = g.newImage("Mario/blank.png", ImageFormat.ARGB4444);
        Assets.dead = g.newImage("Mario/dead.png", ImageFormat.ARGB4444);

        Assets.goomba1 = g.newImage("Enemies/Goomba_Left.png", ImageFormat.ARGB4444);
        Assets.goomba2 = g.newImage("Enemies/Goomba_Right.png", ImageFormat.ARGB4444);
        Assets.deadGoomba = g.newImage("Enemies/Goomba_Dead.png", ImageFormat.ARGB4444);

        Assets.heliboy = g.newImage("heliboy.png", ImageFormat.ARGB4444);
        Assets.heliboy2 = g.newImage("heliboy2.png", ImageFormat.ARGB4444);
        Assets.heliboy3  = g.newImage("heliboy3.png", ImageFormat.ARGB4444);
        Assets.heliboy4  = g.newImage("heliboy4.png", ImageFormat.ARGB4444);
        Assets.heliboy5  = g.newImage("heliboy5.png", ImageFormat.ARGB4444);

        Assets.tileBlankBlock = g.newImage("Level/blankBlock.png", ImageFormat.ARGB4444);
        Assets.tileBrickBlock = g.newImage("Level/brickBlock.png", ImageFormat.ARGB4444);
        Assets.tileEndBlock = g.newImage("Level/endBlock.png", ImageFormat.ARGB4444);
        Assets.tileQuestionBlock = g.newImage("Level/questionBlock.png", ImageFormat.ARGB4444);
        Assets.tileGroundBlock = g.newImage("Level/groundBlock.png", ImageFormat.ARGB4444);

        Assets.coin = g.newImage("Items/Coin.png", ImageFormat.ARGB4444);
        Assets.mushroom = g.newImage("Items/Mushroom.png", ImageFormat.ARGB4444);

        Assets.tube = g.newImage("Level/tube.png", ImageFormat.ARGB4444);

        Assets.tiledirt = g.newImage("tiledirt.png", ImageFormat.RGB565);
        Assets.tilegrassTop = g.newImage("tilegrasstop.png", ImageFormat.RGB565);
        Assets.tilegrassBot = g.newImage("tilegrassbot.png", ImageFormat.RGB565);
        Assets.tilegrassLeft = g.newImage("tilegrassleft.png", ImageFormat.RGB565);
        Assets.tilegrassRight = g.newImage("tilegrassright.png", ImageFormat.RGB565);

        Assets.button = g.newImage("button.jpg", ImageFormat.RGB565);


        Assets.smallJump = game.getAudio().createSound("sounds/smalljump.mp3");
        Assets.coinSound = game.getAudio().createSound("sounds/coin.mp3");
        Assets.bigJump = game.getAudio().createSound("sounds/bigjump.mp3");
        Assets.powerup = game.getAudio().createSound("sounds/powerup.mp3");
        Assets.stomp = game.getAudio().createSound("sounds/stomp.mp3");
        Assets.powerupAppears = game.getAudio().createSound("sounds/powerupappears.mp3");
        Assets.warning = game.getAudio().createMusic("sounds/warning.mp3");

        Assets.titleScreen = g.newImage("Title-Screen.png", ImageFormat.ARGB4444);



        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawImage(Assets.titleScreen, 0, 0);
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

    }
}
