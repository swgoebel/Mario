package com.scottg.mariogame;

/**
 * Created by Babbas on 6/27/2015.
 */
import com.scottg.framework.Image;
import com.scottg.framework.Music;
import com.scottg.framework.Sound;

public class Assets {

    public static Image menu, splash, background, background2, titleScreen, character, character2, character3, heliboy, heliboy2, heliboy3, heliboy4, heliboy5, mario;
    public static Image tiledirt, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, characterJump, characterDown;
    public static Image tileBlankBlock, tileBrickBlock, tileEndBlock, tileGroundBlock, tileQuestionBlock;
    public static Image coin, mushroom, flower;
    public static Image goomba1, goomba2, deadGoomba;
    public static Image tube;
    public static Image button;
    public static Sound click, smallJump, bigJump, coinSound, powerup, stomp;
    public static Music theme, gameOverMusic, marioDead, stageClear, gameWin, themeFast, warning;
    /*big mario images*/public static Image marioBigStandLeft, marioBigStandRight, marioBigDuckLeft, marioBigDuckRight, marioBigJumpLeft, marioBigJumpRight, marioBigWalkLeft1, marioBigWalkLeft2, marioBigWalkLeft3,marioBigWalkRight1, marioBigWalkRight2, marioBigWalkRight3;
    /*small mario images*/public static Image marioSmallStandLeft, marioSmallStandRight, marioSmallDuckLeft, marioSmallDuckRight, marioSmallJumpLeft, marioSmallJumpRight, marioSmallWalkLeft1, marioSmallWalkLeft2, marioSmallWalkLeft3, marioSmallWalkRight1, marioSmallWalkRight2, marioSmallWalkRight3;
    public static Image dead, blank;
    /*ground images*/ public static Image ground1, ground2, ground3, ground4;
    public static Sound powerupAppears;

    public static void load(SampleGame sampleGame) {
        // TODO Auto-generated method stub
/*      theme = sampleGame.getAudio().createMusic("menutheme.mp3");
        theme.pause();
        theme.setLooping(true);
        theme.setVolume(0.85f);*/

    }

}
