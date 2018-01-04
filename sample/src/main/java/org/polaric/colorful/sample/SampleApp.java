package org.polaric.colorful.sample;

import android.app.Application;
import android.graphics.Color;

import org.polaric.colorful.Colorful;

public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Colorful.defaults()
                .primaryColor(Colorful.ThemeColor.RED)
                .accentColor(Colorful.ThemeColor.BLUE)
                .buttonColor(Colorful.ButtonColor.of(Colorful.ThemeColor.ORANGE))
                .translucent(false)
                .dark(false);

        Colorful.init(this);
    }
}
