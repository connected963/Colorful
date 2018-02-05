package org.polaric.colorful.sample;

import android.app.Application;
import android.graphics.Color;

import org.polaric.colorful.Colorful;
import org.polaric.colorful.ThemeColor;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Colorful.defaults()
                .primaryColor(ThemeColor.RED)
                .accentColor(ThemeColor.BLUE)
                .buttonColor(Colorful.ButtonColor.of(ThemeColor.GREEN))
                .translucent(false)
                .dark(false);

        Colorful.init(this);
    }
}
