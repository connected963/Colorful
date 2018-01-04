package org.polaric.colorful;

import android.content.Context;
import android.support.annotation.StyleRes;
import android.util.Log;

public class ThemeDelegate {
    private Colorful.ThemeColor primaryColor;
    private Colorful.ThemeColor accentColor;
    private Colorful.ButtonColor buttonColor;
    private boolean translucent;
    private boolean dark;
    @StyleRes
    private int styleResPrimary;
    @StyleRes
    private int styleResAccent;
    @StyleRes
    private int styleResBase;
    @StyleRes
    private int styleResButtonDefault;
    @StyleRes
    private int styleResButtonPressed;
    @StyleRes
    private int styleResButtonFocused;
    @StyleRes
    private int styleResButtonDisabled;

    ThemeDelegate(Context context, Colorful.ThemeColor primary, Colorful.ThemeColor accent, Colorful.ButtonColor buttonColor, boolean translucent, boolean dark) {
        this.primaryColor = primary;
        this.accentColor = accent;
        this.buttonColor = buttonColor;
        this.translucent = translucent;
        this.dark = dark;
        long curTime = System.currentTimeMillis();
        styleResPrimary = context.getResources().getIdentifier("primary" + primary.ordinal(), "style", context.getPackageName());
        styleResAccent = context.getResources().getIdentifier("accent" + accent.ordinal(), "style", context.getPackageName());
        styleResButtonDefault = context.getResources().getIdentifier("buttonDefault" + buttonColor.getDefaultButton().ordinal(), "style", context.getPackageName());
        styleResButtonPressed = context.getResources().getIdentifier("buttonPressed" + buttonColor.getPressedButton().ordinal(), "style", context.getPackageName());
        styleResButtonFocused = context.getResources().getIdentifier("buttonFocused" + buttonColor.getFocusedButton().ordinal(), "style", context.getPackageName());
        styleResButtonDisabled = context.getResources().getIdentifier("buttonDisabled" + buttonColor.getFocusedButton().ordinal(), "style", context.getPackageName());
        styleResBase = dark ? R.style.Colorful_Dark : R.style.Colorful_Light;
        Log.d(Util.LOG_TAG, "ThemeDelegate fetched theme in " + (System.currentTimeMillis() - curTime) + " milliseconds");
    }

    @StyleRes
    public int getStyleResPrimary() {
        return styleResPrimary;
    }

    @StyleRes
    public int getStyleResAccent() {
        return styleResAccent;
    }

    @StyleRes
    public int getStyleResBase() {
        return styleResBase;
    }

    @StyleRes
    public int getStyleResButtonDefault() {
        return styleResButtonDefault;
    }

    @StyleRes
    public int getStyleResButtonPressed() {
        return styleResButtonPressed;
    }

    @StyleRes
    public int getStyleResButtonFocused() {
        return styleResButtonFocused;
    }

    @StyleRes
    public int getStyleResButtonDisabled() {
        return styleResButtonDisabled;
    }

    public Colorful.ThemeColor getPrimaryColor() {
        return primaryColor;
    }

    public Colorful.ThemeColor getAccentColor() {
        return accentColor;
    }

    public Colorful.ButtonColor getButtonColor() {
        return buttonColor;
    }

    public boolean isTranslucent() {
        return translucent;
    }

    public boolean isDark() {
        return dark;
    }
}
