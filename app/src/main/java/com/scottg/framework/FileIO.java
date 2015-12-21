package com.scottg.framework;

import android.content.SharedPreferences;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
    InputStream readFile(String file) throws IOException;

    OutputStream writeFile(String file) throws IOException;
    
    InputStream readAsset(String file) throws IOException;
    
    SharedPreferences getSharedPref();

    AssetManager assetManager();
}
