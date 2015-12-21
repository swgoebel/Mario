package com.scottg.mariogame;

/**
 * Created by Babbas on 6/27/2015.
 */

import android.graphics.Rect;

import com.scottg.framework.Image;

import java.util.ArrayList;

public class Tile {

    private int tileX, tileY, speedX;
    public int type;
    public Image tileImage;
    public int arbitrary=0;
    public int tileHeight = 0;
    public boolean tileJump=false;
    public Item item1;
    public Goomba goomba1;


    private Background bg = GameScreen.getBg1();
    private Mario mario = GameScreen.getMario();
    public ArrayList items = GameScreen.getItems();
    public ArrayList goombas = GameScreen.getGoomba();

    public Rect r;

    public Tile(int x, int y, int typeInt) {
        tileX = x * 16;
        tileY = y * 16;

        type = typeInt;

        r = new Rect();

        if (type == 5) {
            tileImage = Assets.tileBrickBlock;
        } else if (type == 8) {
            tileImage = Assets.tileQuestionBlock;
        }
        else if (type == 9) {
            tileImage = Assets.tileQuestionBlock;
        }else if (type == 4) {
            tileImage = Assets.tileBlankBlock;

        } else if (type == 6) {
            tileImage = Assets.tileEndBlock;

        } else if (type == 2) {
            tileImage = Assets.tileGroundBlock;
        } else {
            type = 0;
        }

    }

    public void update() {
        speedX = bg.getSpeedX()*2;
        tileX += speedX;
        if (type == 5) {
            tileImage = Assets.tileBrickBlock;
        } else if (type == 8) {
            tileImage = Assets.tileQuestionBlock;
        }else if (type == 9) {
            tileImage = Assets.tileQuestionBlock;}
        else if (type == 4) {
            tileImage = Assets.tileBlankBlock;

        } else if (type == 6) {
            tileImage = Assets.tileEndBlock;

        } else if (type == 2) {
            tileImage = Assets.tileGroundBlock;
        } else {
            type = 0;
        }
        if(type!=0) {
            r.set(tileX, tileY, tileX + 16, tileY + 16);
        }
        if(tileJump==true){
            setTileY(getTileY()+6);
            tileJump(false);
        }
        for(int i =0; i<items.size();i++){
            item1 = (Item) items.get(i);

         checkItemCollision(item1.rect, r);
        }

        for(int i =0; i<goombas.size();i++){
            goomba1 = (Goomba) goombas.get(i);

            checkEnemyCollision(goomba1.r, r);
        }



        checkVerticalCollision(mario.rectBottom, mario.rectTop, r, mario.rectRight);
        checkSideCollision(mario.rectLeft, mario.rectRight, r);

    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }

    public void tileJump(Boolean tileJump){
        this.tileJump= tileJump;
    }

    public void checkEnemyCollision(Rect g1, Rect r){
        if(Rect.intersects(g1, r)){
            goomba1.setFalling(false);
            if(type==6) {
                goomba1.setMoveSpeed(goomba1.getMoveSpeed()*-1);
            }
        }
    }

    public void checkItemCollision(Rect rectItem, Rect r){
        if(Rect.intersects(rectItem,r)){
            item1.falling(false);
            mario.arbitrary=0;
            mario.arbitrary2=1;
        }
    }
    public void checkVerticalCollision(Rect rectBottom, Rect rectTop, Rect r, Rect rectRight) {
      if (Rect.intersects(rectTop, r)&& !Rect.intersects(rectRight, r)) {
          mario.setJumping(false);
          mario.setFalling(true);

          if(type==8){
              Item item = new Item(r.left,r.top, 2);
              items.add(item);
              this.type=4;

          }else if(type==9){
              Item item = new Item((r.left)+4,r.top, 1); //Plus four centers the item
              items.add(item);
              this.type = 4;
            tileJump(true);
            setTileY(getTileY() - 6);
              mario.setPoints(100);
              mario.setCoins(1);

              }

      }

          if (Rect.intersects(rectBottom, r)) {
            mario.setFalling(false);
            mario.setJumping(false);
            mario.jumpSound=false;
            mario.setJumpHeight(0);
            mario.setY((mario.getY()));
        }

    }

    public void checkSideCollision(Rect rectLeft,Rect rectRight,Rect r) {

    if(Rect.intersects(rectRight, r)){
         mario.setX(r.left - 17);
         mario.arbitrary=r.left-17;
      }
      if(Rect.intersects(rectLeft, r)){
          mario.setX(r.right + 1);
          mario.arbitrary=r.right+17;
        }
    }

}
