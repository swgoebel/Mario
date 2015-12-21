package com.scottg.framework;

import com.scottg.framework.Graphics.ImageFormat;

public interface Image {
    int getWidth();
    int getHeight();
    ImageFormat getFormat();
    void dispose();
}
