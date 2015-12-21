package com.scottg.mariogame;

import android.graphics.Rect;

/**
 * Created by Babbas on 9/14/2015.
 */
public class Ground extends Sprite {

    private int z;
    public Ground(int x, int y, int z){
        setX(x);
        setY(y);
        setZ(z);
        Rect rect = new Rect(x,y,1,z);
    }

    public int getZ(){
        return z;
    }

    public void setZ(int z){
        this.z = z;
    }
}
