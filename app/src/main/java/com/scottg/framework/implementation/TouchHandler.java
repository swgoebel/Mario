package com.scottg.framework.implementation;

import java.util.List;

import android.view.View.OnTouchListener;

import com.scottg.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    boolean isTouchDown(int pointer);
    
    int getTouchX(int pointer);
    
    int getTouchY(int pointer);
    
    List<TouchEvent> getTouchEvents();
}
