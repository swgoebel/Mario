package com.scottg.mariogame;

/**
 * Created by Babbas on 6/27/2015.
 */

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.scottg.framework.Game;
import com.scottg.framework.Graphics;
import com.scottg.framework.Image;
import com.scottg.framework.Input.TouchEvent;
import com.scottg.framework.Screen;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameScreen extends Screen {



    enum GameState {
        Ready, Running, Paused, GameOver, Transition, Dead, Victory, GameWin
    }

    GameState state = GameState.Ready;

    // Variable Setup

   private static Background bg1, bg2;
    private static Robot robot;
    public static Heliboy hb, hb2;
    public static Goomba g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11;
    public static Mario mar;
    public static Item item;
    public boolean play1, play2, powerupPlay, warningPlay = false;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;


   private Image currentSprite, character, character2, character3, mario, marioSmallWalkLeft1, marioSmallWalkLeft2, marioSmallWalkLeft3, marioSmallWalkRight1, marioSmallWalkRight2, marioSmallWalkRight3,
            standingMario, jumpingMario , duckingMario, marioBigWalkLeft1, marioBigWalkLeft2, marioBigWalkLeft3, marioBigWalkRight1, marioBigWalkRight2, marioBigWalkRight3, goomba1, goomba2;


    private Animation anim, hanim, smallMarioRightAnim, smallMarioLeftAnim, bigMarioRightAnim, bigMarioLeftAnim, ganim, manim, bigToSmallTransition, smallToBigTransition;

    private ArrayList tilearray = new ArrayList();
    private static ArrayList itemarray = new ArrayList();
    private ArrayList tubearray = new ArrayList();
    private static ArrayList enemyArray = new ArrayList();


    DecimalFormat decimalFormat = new DecimalFormat("#000000");
    DecimalFormat decimalFormat2 = new DecimalFormat("#000");
    DecimalFormat decimalFormat3 = new DecimalFormat("#00");

    int livesLeft = 1;
    int transitionTime, raiseHeight, flickerCount = 0;
    int timer = 30000;
    Paint paint, paint2, paint3;

    public GameScreen(Game game) {
        super(game);

        Assets.theme.setLooping(true);
        Assets.themeFast.setLooping(true);
        Assets.theme.seekBegin();
        Assets.theme.play();


        // Initialize game objects here
        // places the objects where they need to be and creates the animation
        //********3**********
        bg1 = new Background(0, 0);
        bg2 = new Background(3270, 1);
        mar = new Mario();
        robot = new Robot();
        hb = new Heliboy(340, 360);
        hb2 = new Heliboy(700, 360);
        g1 = new Goomba(300, 175);
        g2 = new Goomba(674, 176);
        g3 = new Goomba(850, 176);
        g4 = new Goomba(820, 176);
        g5 = new Goomba(1318, 50);
        g6 = new Goomba(1518, 176);
        g7 = new Goomba(1975, 176);
        g8 = new Goomba(2000, 176);
        g9 = new Goomba(2020, 176);
        g10 = new Goomba(2710, 176);
        g11 = new Goomba(2720, 176);

        enemyArray.add(g1);
        enemyArray.add(g2);
        enemyArray.add(g3);
        enemyArray.add(g4);
        enemyArray.add(g5);
        enemyArray.add(g6);
        enemyArray.add(g7);
        enemyArray.add(g8);
        enemyArray.add(g9);
        enemyArray.add(g10);
        enemyArray.add(g11);


       character = Assets.character;
        character2 = Assets.character2;
        character3 = Assets.character3;

        //*********4**********

        marioSmallWalkLeft1 = Assets.marioSmallWalkLeft1;
        marioSmallWalkLeft2 = Assets.marioSmallWalkLeft2;
        marioSmallWalkLeft3 = Assets.marioSmallWalkLeft3;
        marioSmallWalkRight1 = Assets.marioSmallWalkRight1;
        marioSmallWalkRight2 = Assets.marioSmallWalkRight2;
        marioSmallWalkRight3 = Assets.marioSmallWalkRight3;

        marioBigWalkLeft1= Assets.marioBigWalkLeft1;
        marioBigWalkLeft2 = Assets.marioBigWalkLeft2;
        marioBigWalkLeft3 = Assets.marioBigWalkLeft3;
        marioBigWalkRight1 = Assets.marioBigWalkRight1;
        marioBigWalkRight2 = Assets.marioBigWalkRight2;
        marioBigWalkRight3 = Assets.marioBigWalkRight3;

        goomba1= Assets.goomba1;
        goomba2 = Assets.goomba2;


        ganim = new Animation();
        ganim.addFrame(goomba1, 100);
        ganim.addFrame(goomba2, 100);

        smallMarioRightAnim = new Animation();
        smallMarioRightAnim.addFrame(marioSmallWalkRight1, 100);
        smallMarioRightAnim.addFrame(marioSmallWalkRight2, 100);
        smallMarioRightAnim.addFrame(marioSmallWalkRight3, 100);

        smallMarioLeftAnim = new Animation();
        smallMarioLeftAnim.addFrame(marioSmallWalkLeft1, 100);
        smallMarioLeftAnim.addFrame(marioSmallWalkLeft2, 100);
        smallMarioLeftAnim.addFrame(marioSmallWalkLeft3, 100);

        bigMarioRightAnim = new Animation();
        bigMarioRightAnim.addFrame(marioBigWalkRight1, 100);
        bigMarioRightAnim.addFrame(marioBigWalkRight2, 100);
        bigMarioRightAnim.addFrame(marioBigWalkRight3, 100);

        bigMarioLeftAnim = new Animation();
        bigMarioLeftAnim.addFrame(marioBigWalkLeft1, 100);
        bigMarioLeftAnim.addFrame(marioBigWalkLeft2, 100);
        bigMarioLeftAnim.addFrame(marioBigWalkLeft3, 100);

        bigToSmallTransition = new Animation();
        bigToSmallTransition.addFrame(marioBigWalkLeft1, 100);
        bigToSmallTransition.addFrame(marioSmallWalkLeft1, 100);

        smallToBigTransition = new Animation();
        smallToBigTransition.addFrame(marioSmallWalkLeft1, 100);
        smallToBigTransition.addFrame(marioBigWalkLeft1, 100);




        duckingMario = Assets.marioBigJumpRight;
        standingMario = Assets.marioSmallStandRight;
        jumpingMario = Assets.marioSmallJumpRight;
        mario = standingMario;

        loadMap();

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

        paint3 = new Paint();

        paint3.setTextSize(8);
        paint3.setTextAlign(Paint.Align.CENTER);

        paint3.setAntiAlias(true);
        paint3.setColor(Color.WHITE);

        Typeface typeface = Typeface.createFromAsset(this.game.getFileIO().assetManager(), "fonts/emulogic.ttf");


        paint3.setTypeface(typeface);
        paint3.setAntiAlias(true);
        paint3.setColor(Color.WHITE);




    }

    private void loadMap() {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        Scanner scanner = new Scanner(SampleGame.map);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // no more lines to read
            if (line == null) {
                break;
            }

            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());

            }
        }
        height = lines.size();

        for (int j = 0; j < 14; j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++) {

                if (i < line.length()) {
                    char ch = line.charAt(i);
                    Tile t = new Tile(i, j, Character.getNumericValue(ch));
                    tilearray.add(t);
                }

            }
        }
        Item item1 = new Item(500,500,0);
        itemarray.add(item1);
        Tube tube1 = new Tube(448, 160);
        Tube tube2 = new Tube(608, 144);
        Tube tube3 = new Tube(736, 128);
        Tube tube4 = new Tube(912, 128);
        Tube tube5 = new Tube(2608, 160);
        Tube tube6 = new Tube(2864, 160);
        tubearray.add(tube1);
        tubearray.add(tube2);
        tubearray.add(tube3);
        tubearray.add(tube4);
        tubearray.add(tube5);
        tubearray.add(tube6);

    }

    @Override
    public void update(float deltaTime) {
        List touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
        if (state == GameState.Transition)
            updateTransition(deltaTime);
        if (state == GameState.Dead)
            updateDead(deltaTime);
        if (state == GameState.Victory)
            updateVictory(deltaTime);
        if (state == GameState.GameWin)
            updateGameWin(touchEvents, deltaTime);
    }

    private void updateGameWin(List touchEvents, float deltaTime) {
        int len = touchEvents.size();
        if(play2==false) {
            Assets.gameWin.seekBegin();
            Assets.gameWin.play();
            play2=true;
        }
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP && Assets.gameWin.isStopped()) {
                if (inBounds(event, 0, 0, 800, 480)) {
/*                    Assets.gameOverMusic.reset();
                    Assets.marioDead.reset();*/
                    nullify();
                    game.setScreen(new SplashLoadingScreen(game));
                    return;
                }
            }
        }

    }


    private void updateVictory(float deltaTime) {
        Assets.theme.pause();

        if(play1 == false) {
            Assets.stageClear.seekBegin();
            Assets.stageClear.play();
            play1 = true;
        }
        if(timer >0){
            timer-=100;
             mar.setPoints(100);
            Assets.coinSound.play(.5f);
        }
        if(Assets.stageClear.isStopped()){
            Assets.stageClear.pause();
            state = GameState.GameOver;
        }
    }

    private void updateDead(float deltaTime){
        manim = null;
        standingMario = Assets.dead;
        Assets.theme.pause();
        Assets.themeFast.pause();

       if(play1==false){
           Assets.marioDead.play();
           play1 = true;
       }
        if(raiseHeight < 75){
            mar.setY(mar.getY()-2);
            raiseHeight+=2;
        } else {
            mar.setY(mar.getY()+5);
            if(mar.getY()>500 && Assets.marioDead.isStopped()){
                itemarray.clear();
                enemyArray.clear();
                state = GameState.GameOver;
            }
        }
    }

    private void updateTransition(float deltaTime) {

        if(transitionTime<=500) {
            if (mar.getBig()) {
                manim = bigToSmallTransition;
                mar.setInvincible(true);
                transitionTime += 10;
                bigToSmallTransition.update(25);

            } else {
                if(mar.hurt){
                    state = GameState.Dead;
                }
                else if(powerupPlay == false){
                    Assets.powerup.play(.85f);
                    powerupPlay = true;
                }
                manim = smallToBigTransition;
                transitionTime += 10;
                smallToBigTransition.update(10);

            }
        }
        else{
            if(mar.getBig()){
                mar.setBig(false);
                standingMario = Assets.marioSmallStandRight;
                mar.setY(mar.getY()-15);
            }
            else{
                mar.setY(mar.getY()-15);
                mar.setBig(true);
            }
            mar.transition= false;
            powerupPlay = false;
            transitionTime=0;

            state = GameState.Running;
        }
    }

    private void updateReady(List touchEvents) {

        // This example starts with a "Ready" screen.
        // When the user touches the screen, the game begins.
        // state now becomes GameState.Running.
        // Now the updateRunning() method will be called!
            state = GameState.Running;
    }


    private void updateRunning(List touchEvents, float deltaTime) {

        // 1. All touch input is handled here:

        int len = touchEvents.size();
        updateTubes();
        updateTiles();
        updateItems();
        updateEnemies();

        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {

                }

                if (event.x > 250) {
                    // Move right.
                    mar.setRight(true);
                    mar.moveRight();
                    if(mar.getBig() == true){
                        if(!mar.getJumping() || !mar.getFalling()){manim = bigMarioRightAnim;}
                        standingMario = Assets.marioBigStandRight;
                        mario = standingMario;
                    }
                    else{
                        if(!mar.getJumping() || !mar.getFalling()){manim = smallMarioRightAnim;}
                        standingMario = Assets.marioSmallStandRight;
                        mario = standingMario;
                    }
                }


                else if (event.x < 150) {
                    mar.setLeft(true);
                    mar.moveLeft();

                    if (mar.getBig() == true) {
                        if (!mar.getJumping() || !mar.getFalling()) {
                            manim = bigMarioLeftAnim;
                        }
                        standingMario = Assets.marioBigStandLeft;
                        mario = standingMario;
                    } else {
                        if (!mar.getJumping() || !mar.getFalling()) {
                            manim = smallMarioLeftAnim;
                        }
                        standingMario = Assets.marioSmallStandLeft;
                        mario = standingMario;
                    }
                }

                    if(event.y > 150){
                        if(mar.getBig()){
                            manim = null;
                            mar.setDuck(true);
                                if(mar.getLeft()) {
                                    duckingMario = Assets.marioBigDuckLeft;

                                    mar.setSpeedX(0);
                                }
                                else{
                                    duckingMario = Assets.marioBigDuckRight;

                                    mar.setSpeedX(0);
                                }
                        }
                    }

                   else if (event.y < 100) {
                        mar.jump();

                        manim = null;
                        if(mar.getBig() == true){
                            if(mar.getLeft()){
                               jumpingMario = Assets.marioBigJumpLeft;
                                mario = jumpingMario;
                            }
                            else if (mar.getRight()){
                                jumpingMario = Assets.marioBigJumpRight;
                                mario = jumpingMario;
                            }
                        }
                        else if(!mar.getBig()){
                            if(mar.getLeft()){
                                jumpingMario = Assets.marioSmallJumpLeft;
                                mario = jumpingMario;
                            }
                            else if (mar.getRight()){
                                jumpingMario = Assets.marioSmallJumpRight;
                                mario = jumpingMario;
                            }
                        }
            }



            if (event.type == TouchEvent.TOUCH_UP) {

                if (inBounds(event, 0, 415, 65, 65)) {
                    currentSprite = anim.getImage();
                    robot.setDucked(false);

                }

                if (inBounds(event, 0, 0, 35, 35)) {
                    pause();

                }

                if (event.x > 0) {
                    // Move right.
                    mar.setRight(false);
                    mar.setLeft(false);
                    mar.stop();
                    if(mar.isDuck()){
                    mar.setDuck(false);}
                    manim = null;
                    if(mar.getJumping() || mar.getFalling()){
                        if(mar.getBig()){
                            if(mar.getLeft()){
                                jumpingMario = Assets.marioBigJumpLeft;
                                mario = jumpingMario;
                            }
                            else{
                                jumpingMario = Assets.marioBigJumpRight;
                                mario = jumpingMario;
                            }
                        }
                        else {
                            if(mar.getLeft()){
                                jumpingMario = Assets.marioSmallJumpLeft;
                                mario = jumpingMario;
                            }
                            else{
                                jumpingMario = Assets.marioSmallJumpRight;
                                mario = jumpingMario;
                            }
                        }
                    }
                    else{
                    mario = standingMario;}

                }
            }

        }

        // 2. Check miscellaneous events like death:

        if (livesLeft == 0) {
            state = GameState.GameOver;
        }
        if(mar.transition){
            state = GameState.Transition;
        }

        if(mar.getY()>204){
            state = GameState.Dead;
        }

        if(mar.getX()>bg2.getBgX()){
            state = GameState.Victory;
        }

        if(timer <= 10000 && timer > 1){
            Assets.theme.pause();
            if(warningPlay==false){
                Assets.warning.play();
                warningPlay = true;
            }
            if(Assets.warning.isStopped()){
                Assets.themeFast.play();

            }
        }
        else if (timer <= 0){
            state = GameState.Dead;
        }
        // 3. Call individual update() methods here.
        // This is where all the game updates happen.

        robot.update();

        mar.update();
        if(mar.getX() > 250 && mar.getSpeedX()> 0 && bg2.getBgX()>278){
            bg1.setBgX(bg1.getBgX()-mar.getSpeedX());
            bg2.setBgX(bg2.getBgX()-mar.getSpeedX());
           // grnd1.setX(grnd1.getX()-mar.getSpeedX());
            mar.setX(250);
            bg1.setSpeedX(-mar.getSpeedX());
            bg2.setSpeedX(-mar.getSpeedX());
        }

           if(mar.getY() < 10){
            mar.setFalling(true);
               mar.setJumping(false);
        }
        if(mar.getX() < 40)
        {
            mar.setSpeedX(0);
            mar.setX(40);
        }

        this.timer -= 10;
        bg1.update();
        bg2.update();
        animate();

    }

    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        return event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1;
    }

    private void updatePaused(List touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 0, 800, 240)) {

                    if (!inBounds(event, 0, 0, 35, 35)) {
                        resume();
                    }
                }

                if (inBounds(event, 0, 240, 800, 240)) {
                    nullify();
                    goToMenu();
                }
            }
        }
    }

    private void updateGameOver(List touchEvents) {
        int len = touchEvents.size();
        if(play2==false) {
            Assets.gameOverMusic.seekBegin();
            Assets.gameOverMusic.play();
        play2=true;
        }
        for (int i = 0; i < len; i++) {
            TouchEvent event = (TouchEvent) touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP && Assets.gameOverMusic.isStopped()) {
                if (inBounds(event, 0, 0, 800, 480)) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    private void updateTiles() {

        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            t.update();
        }

    }

   private void updateItems() {
        for (int i = 0; i < itemarray.size(); i++) {
            Item item = (Item) itemarray.get(i);
            item.update();
        }
    }

    private void updateTubes() {
        for (int i = 0; i < tubearray.size(); i++) {
            Tube tube = (Tube) tubearray.get(i);
            tube.update();
        }
    }

    private void updateEnemies() {
        for (int i = 0; i < enemyArray.size(); i++) {
            Enemy enemy = (Enemy) enemyArray.get(i);
            enemy.update();
        }
    }
    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

      g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());


        paintItems(g);
        paintEnemies(g);
        paintTubes(g);
        paintTiles(g);

        // First draw the game elements.

        if(manim == null) {
            if(!mar.invincible) {
                if (mar.getJumping()) {
                    mario = jumpingMario;
                    g.drawImage(mario, mar.getX(), mar.getY());
                } else if (mar.isDuck()) {
                    mario = duckingMario;
                    g.drawImage(mario, mar.getX(), mar.getY() + 10);
                } else {
                    mario = standingMario;
                    g.drawImage(mario, mar.getX(), mar.getY());
                }
            }
            else if (mar.invincible){
                if( flickerCount<125) {
                    mario = Assets.blank;

                    flickerCount+=10;
                    g.drawImage(mario, mar.getX(), mar.getY());
                }
                else if (flickerCount >=125 && flickerCount<250){
                    mario = standingMario;
                    flickerCount+=10;
                    g.drawImage(mario, mar.getX(), mar.getY());
                }
                else{
                    flickerCount = 0;
                }
            }
        }
        else {
           if(!mar.invincible) {g.drawImage(manim.getImage(), mar.getX(), mar.getY());}
            else if (mar.invincible){
                if( flickerCount<125) {
                    mario = Assets.blank;

                    flickerCount+=10;
                    g.drawImage(mario, mar.getX(), mar.getY());
                }
                else if (flickerCount >=125 && flickerCount<250){

                    flickerCount+=10;
                    g.drawImage(manim.getImage(), mar.getX(), mar.getY());
                }
                else{
                    flickerCount = 0;
                }
            }
        }

        //g.drawRect(mar.getX(), mar.getY(), 16, 16, Color.RED);


/*
       g.drawString(String.valueOf(Assets.marioDead.prepared()), 30, 20, paint);
        g.drawString(String.valueOf(Assets.marioDead.isPlaying()), 30, 50, paint);
         g.drawString(String.valueOf(Assets.marioDead.isStopped()), 30,80,paint);
        g.drawString(String.valueOf(play1), 30, 110, paint);
        g.drawString(String.valueOf(flickerCount), 30, 140, paint);
        g.drawString(String.valueOf(mar.getInvincibleCount()), 30, 170, paint);
*/

        g.drawImage(Assets.background2, bg2.getBgX(), bg2.getBgY());

        g.drawString("MARIO", 125, 15, paint3);
      g.drawString("WORLD", 250, 15, paint3);
        g.drawString("TIME", 300, 15, paint3);
        g.drawString(decimalFormat.format(mar.getPoints()), 128, 28, paint3);
        g.drawImage(Assets.coin, 175, 15);
        g.drawString("x ", 195, 28, paint3);
        g.drawString(decimalFormat3.format(mar.getCoins()), 205, 28, paint3);
        g.drawString("1-1", 250, 28, paint3);
        g.drawString(decimalFormat2.format(timer / 100), 300, 28, paint3);


/*        g.drawRect(mar.getX()+2, mar.getY() + 15, 14, 2, Color.GREEN);
        g.drawRect(mar.getX()+15,mar.getY()+4, 3,25, Color.BLUE);

        g.drawRect(mar.rectRight.left, mar.rectRight.top, 15, 1, Color.GREEN);*/

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();
        if (state == GameState.Transition)
            drawTransitionUI();
        if (state == GameState.GameWin)
            drawGameWinUI();


    }

    private void drawGameWinUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);


        g.drawString("MARIO", 125, 15, paint3);
        g.drawString("WORLD", 250, 15, paint3);
        g.drawString("TIME", 300, 15, paint3);
        g.drawString(decimalFormat.format(mar.getPoints()), 128, 28, paint3);
        g.drawImage(Assets.coin, 175, 15);
        g.drawString("x ", 195, 28, paint3);
        g.drawString(decimalFormat3.format(mar.getCoins()), 205, 28, paint3);
        g.drawString("1-1", 250, 28, paint3);
        g.drawString(decimalFormat2.format(timer / 100), 300, 28, paint3);

        g.drawString("THANKS FOR PLAYING",210, 115, paint3);
    }

    private void paintTiles(Graphics g) {
        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            if (t.type != 0) {
                g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
            }
        }
    }

    private void paintItems(Graphics g) {
        for (int i = 0; i < itemarray.size(); i++) {
            Item item = (Item) itemarray.get(i);
            if (item.type != 0) {

                g.drawImage(item.getItemImage(), item.getX(), item.getY());

            }
        }
    }

    private void paintEnemies(Graphics g) {
        for (int i = 0; i < enemyArray.size(); i++) {
            Enemy enemy = (Enemy) enemyArray.get(i);

                g.drawImage(ganim.getImage(), enemy.getX(), enemy.getY());


        }
    }

    private void paintTubes(Graphics g) {
        for (int i = 0; i < tubearray.size(); i++) {
            Tube tube = (Tube) tubearray.get(i);
            g.drawImage(tube.getTubeImage(), tube.getX(), tube.getY());
            }
        }


    public void animate() {
        ganim.update(10);
        smallMarioRightAnim.update(25);
        smallMarioLeftAnim.update(25);
        bigMarioLeftAnim.update(25);
        bigMarioRightAnim.update(25);
    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;
        bg1 = null;
        bg2 = null;
        mar = null;
        mario = null;
        smallMarioRightAnim = null;
        smallMarioLeftAnim = null;
        bigMarioLeftAnim = null;
        bigMarioRightAnim = null;
        // Call garbage collector to clean up memory.
        System.gc();

    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap to Start.", 400, 240, paint);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);

    }

    private void drawTransitionUI(){
        Graphics g = game.getGraphics();

    }
    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);


        g.drawString("MARIO", 125, 15, paint3);
        g.drawString("WORLD", 250, 15, paint3);
        g.drawString("TIME", 300, 15, paint3);
        g.drawString(decimalFormat.format(mar.getPoints()), 128, 28, paint3);
        g.drawImage(Assets.coin, 175, 15);
        g.drawString("x ", 195, 28, paint3);
        g.drawString(decimalFormat3.format(mar.getCoins()), 205, 28, paint3);
        g.drawString("1-1", 250, 28, paint3);
        g.drawString(decimalFormat2.format(timer / 100), 300, 28, paint3);

        g.drawString("GAME OVER",210, 115, paint3);

    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

    private void goToMenu() {
        // TODO Auto-generated method stub
        game.setScreen(new MainMenuScreen(game));

    }

    public static Background getBg1() {
        // TODO Auto-generated method stub
        return bg1;
    }

    public static Background getBg2() {
        // TODO Auto-generated method stub
        return bg2;
    }


    public static Mario getMario() {
        // TODO Auto-generated method stub
        return mar;
    }



    public GameState getGameState(){
        return state;
    }
    public static ArrayList getGoomba(){
        return enemyArray;
    }
    public static ArrayList getItems(){
        return itemarray;
    }


}