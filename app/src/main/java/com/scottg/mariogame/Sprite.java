package com.scottg.mariogame;

/**
 * Created by Babbas on 6/30/2015.
 */

import android.graphics.Rect;

import com.scottg.framework.Image;

public class Sprite {

    public int x;
    public int y;
    public int height;
    public int width;
    public Image image;
    boolean visible;
    public int ground;
    public int jumpHeight;

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getGround(){
        return y;
    }

    public void setGround(int g){
        this.ground = g;
    }
    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getJumpHeight(){
        return jumpHeight;
    }

    public void setJumpHeight(int x){
        this.jumpHeight = x;
    }


    public Image getImage(){
        return image;
    }

    public Rect getBounds(){
        return new Rect(x, y, width, height);
    }//creates a rectangle around objects

    public Rect getRectTop()
    {
        return new Rect(x,y, width, 3);
    }//creates a rectangle on the top of players and enemies. Used to register hits to the top of objects

    public Rect getRectBottom()
    {
        return new Rect(x,(y + height), width, 3);
    }//creates a rectangle on the bottom of players and enemies. Used to register hits to the top of objects

    public Rect getRectLeft()
    {
        return new Rect(x, y,  1, height);
    }//creates a rectangle to the left of players and enemies. Used to register hits to the top of objects

    public Rect getRectRight()
    {
        return new Rect((x + width), y,  1, height);
    }//creates a rectangle to the left of players and enemies. Used to register hits to the top of objects.

    public void setImage(Image i){
        image = i;

    }
    public boolean isvisible(){
        return visible;
    }

    public void setVisible(Boolean v){
        this.visible = v;
    }

    public void setHeight(int i){
        height = i;
    }

    public void setWidth(int i){
        width = i;
    }

}
