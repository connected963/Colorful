package org.polaric.colorful;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class ColorfulActivityKeepTheme extends AppCompatActivity {
    private String themeString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupThemeString();
        applyStyles();
    }

    private void setupThemeString() {
        themeString = Colorful.getThemeString();
    }

    private void applyStyles() {
        Colorful.applyTheme(this, false);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!Colorful.getThemeString().equals(themeString)) {
            Log.d(Util.LOG_TAG, "Theme change detected, restarting activity");
            recreate();
        }
    }
}
