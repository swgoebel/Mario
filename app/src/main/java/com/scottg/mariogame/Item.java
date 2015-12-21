package com.scottg.mariogame;

import android.graphics.Rect;

import com.scottg.framework.Image;

import java.util.ArrayList;

/**
 * Created by Babbas on 9/24/2015.
 */
public class Item extends Sprite{
    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    private int moveSpeed = 1;

    public boolean isRise() {
        return rise;
    }

    private boolean rise= true;
    private boolean falling = true;
    private boolean sound = false;


    private int rising = 0;
    public int type;
    public Image itemImage;
    private Mario mario = GameScreen.getMario();
    private ArrayList itemArray = GameScreen.getItems();
    private Background bg = GameScreen.getBg1();

    public Rect rect = new Rect(0,0,0,0);
    Rect rect2 = new Rect(0,0,0,0);

    public Item(int x, int y, int t){
        setX(x);
        setY(y);
        type = t;
    }

    public void update(){
        setX(getX()+(bg.getSpeedX()*2));

        if(type == 1){
            itemImage= Assets.coin;
        }else if(type == 2){
            itemImage = Assets.mushroom;
        }

        if(rise==true){
            if(type==1){
                if(rising<30){
                    rising+=moveSpeed;
                    setY(getY() - moveSpeed);
                    if(!this.sound){
                        Assets.coinSound.play(0.85f);
                        this.sound=true;
                    }
                    mario.arbitrary3=1;
                }
                else{
                    rise = false;
                    if(type == 1){
                        itemArray.remove(this);
                        this.sound=false;
                    }
                    mario.arbitrary3=2;
                }
            }
            else if (type == 2){
            if(rising<17){
                rising+=moveSpeed;
                setY(getY() - moveSpeed);
                if(!this.sound){
                    Assets.powerupAppears.play(0.85f);
                    this.sound=true;
                }
                mario.arbitrary3=1;
            }
            else{
                rise = false;
                this.sound=false;
                mario.arbitrary3=2;
            }
            }
        }
        else if (!rise) {
            if(falling==false){
                setX(getX() + moveSpeed);
                mario.arbitrary3=3;
            }
            else{
                if(type == 2) {
                    mario.arbitrary3=4;

                    setY(getY()+2);
                }
            }
        }
        rect.set(getX(),getY(),getX()+20,getY()+ 18);
        rect2.set(getX()-1,getY()-2,getX()+17,getY()+17);
        falling(true);

    }

    public void falling(boolean fall){
        falling = fall;
    }
    public void setType(int t){
        type=t;
    }

    public int getType(int t){
        return type;
    }

    public Image getItemImage(){
        return itemImage;
    }
}
