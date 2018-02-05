package org.polaric.colorful;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;

public class Colorful {
    private static ThemeDelegate delegate;
    private static ThemeColor primaryColor = Defaults.primaryColor;
    private static ThemeColor accentColor = Defaults.accentColor;
    private static ButtonColor buttonColor = Defaults.buttonColor;
    private static boolean isTranslucent = Defaults.trans;
    private static boolean isDark = Defaults.darkTheme;
    private static String themeString;
    private static ThemeColor ThemeColor;

    private Colorful() {
        // prevent initialization
    }

    public static void init(Context context) {
        Log.d(Util.LOG_TAG, "Attatching to " + context.getPackageName());
        themeString = PreferenceManager.getDefaultSharedPreferences(context).getString(Util.PREFERENCE_KEY, null);
        if (themeString == null) {
            primaryColor = Defaults.primaryColor;
            accentColor = Defaults.accentColor;
            buttonColor = Defaults.buttonColor;
            isTranslucent = Defaults.trans;
            isDark = Defaults.darkTheme;
            themeString = generateThemeString();
        } else {
            initValues();
        }
        delegate = new ThemeDelegate(context, primaryColor, accentColor, buttonColor, isTranslucent, isDark);
    }

    public static void applyTheme(@NonNull Activity activity) {
        applyTheme(activity, true);
    }

    public static void applyTheme(@NonNull Activity activity, boolean overrideBase) {
        if (overrideBase) {
            activity.setTheme(getThemeDelegate().getStyleResBase());
        }

        activity.getTheme().applyStyle(getThemeDelegate().getStyleResPrimary(), true);
        activity.getTheme().applyStyle(getThemeDelegate().getStyleResAccent(), true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getTheme().applyStyle(getThemeDelegate().getStyleResButtonDefault(), true);
            activity.getTheme().applyStyle(getThemeDelegate().getStyleResButtonPressed(), true);
            activity.getTheme().applyStyle(getThemeDelegate().getStyleResButtonFocused(), true);
            activity.getTheme().applyStyle(getThemeDelegate().getStyleResButtonDisabled(), true);
        } else {
            activity.getTheme().applyStyle(getThemeDelegate().getStyleResButtonMLollipop(), true);
        }


        addTaskDescriptionAndTranslucentFlag(activity);
    }

    private static void addTaskDescriptionAndTranslucentFlag(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if (Colorful.getThemeDelegate().isTranslucent()) {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            final ActivityManager.TaskDescription tDesc = new ActivityManager.TaskDescription(null, null, activity.getResources().getColor(Colorful.getThemeDelegate().getPrimaryColor().getColorRes()));
            activity.setTaskDescription(tDesc);
        }
    }

    private static void writeValues(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(Util.PREFERENCE_KEY, generateThemeString()).apply();
    }

    private static void initValues() {
        String[] colors = themeString.split(":");
        isDark = Boolean.parseBoolean(colors[0]);
        isTranslucent = Boolean.parseBoolean(colors[1]);
        primaryColor = Colorful.ThemeColor.values()[Integer.parseInt(colors[2])];
        accentColor = Colorful.ThemeColor.values()[Integer.parseInt(colors[3])];
        ThemeColor defaultButton = Colorful.ThemeColor.values()[Integer.parseInt(colors[4])];
        ThemeColor pressedButton = Colorful.ThemeColor.values()[Integer.parseInt(colors[5])];
        ThemeColor focusedButton = Colorful.ThemeColor.values()[Integer.parseInt(colors[6])];
        ThemeColor disabledButton = Colorful.ThemeColor.values()[Integer.parseInt(colors[7])];

        buttonColor = new ButtonColor(defaultButton, pressedButton, focusedButton, disabledButton);
    }

    private static String generateThemeString() {
        return isDark + ":" + isTranslucent + ":" + primaryColor.ordinal() + ":" + accentColor.ordinal() + ":" + buttonColor.getDefaultButton().ordinal() + ":" + buttonColor.getPressedButton().ordinal() + ":" + buttonColor.getFocusedButton().ordinal() + ":" + buttonColor.getDisabledButton().ordinal();
    }

    public static ThemeDelegate getThemeDelegate() {
        if (delegate == null) {
            Log.e(Util.LOG_TAG, "getThemeDelegate() called before init(Context). Call Colorful.init(Context) in your application class");
        }
        return delegate;
    }

    public static String getThemeString() {
        return themeString;
    }


    public static Config config(Context context) {
        return new Config(context);
    }

    public static Defaults defaults() {
        return new Defaults();
    }

    public static class Defaults {

        private static ThemeColor primaryColor = ThemeColor.DEEP_PURPLE;
        private static ThemeColor accentColor = ThemeColor.RED;
        private static ButtonColor buttonColor = ButtonColor.of(ThemeColor.GREY);
        private static boolean trans = false;
        private static boolean darkTheme = false;

        public Defaults primaryColor(ThemeColor primary) {
            primaryColor = primary;
            return this;
        }

        public Defaults accentColor(ThemeColor accent) {
            accentColor = accent;
            return this;
        }

        public Defaults buttonColor(ButtonColor button) {
            buttonColor = button;
            return this;
        }

        public Defaults translucent(boolean translucent) {
            trans = translucent;
            return this;
        }

        public Defaults dark(boolean dark) {
            darkTheme = dark;
            return this;
        }

        //////////////////////////////////////////////////////

        public static ThemeColor getPrimaryColor() {
            return primaryColor;
        }

        public static ThemeColor getAccentColor() {
            return accentColor;
        }

        public static ButtonColor getButtonColor() {
            return buttonColor;
        }

        public static boolean isDarkTheme() {
            return isDark;
        }
    }

    public static class Config {
        private Context context;

        private Config(Context context) {
            this.context = context;
        }

        public Config primaryColor(ThemeColor primary) {
            primaryColor = primary;
            return this;
        }

        public Config accentColor(ThemeColor accent) {
            accentColor = accent;
            return this;
        }

        public Config buttomColor(ButtonColor button) {
            buttonColor = button;
            return this;
        }

        public Config translucent(boolean translucent) {
            isTranslucent = translucent;
            return this;
        }

        public Config dark(boolean dark) {
            isDark = dark;
            return this;
        }

        public void apply() {
            writeValues(context);
            themeString = generateThemeString();
            delegate = new ThemeDelegate(context, primaryColor, accentColor, buttonColor, isTranslucent, isDark);
        }
    }

    public static class ButtonColor {

        private final ThemeColor defaultButton;

        private final ThemeColor pressedButton;

        private final ThemeColor focusedButton;

        private final ThemeColor disabledButton;

        private ButtonColor(final ThemeColor defaultButton, final ThemeColor pressedButton, final ThemeColor focusedButton, final ThemeColor disabledButton) {
            this.defaultButton = defaultButton;
            this.pressedButton = pressedButton;
            this.focusedButton = focusedButton;
            this.disabledButton = disabledButton;
        }

        public static ButtonColor of(final ThemeColor buttonColor) {
            return new ButtonColor(buttonColor, buttonColor, buttonColor, buttonColor);
        }

        public static ButtonColor of(final ThemeColor defaultButton, final ThemeColor pressedButton, final ThemeColor focusedButton, final ThemeColor disabledButton) {
            return new ButtonColor(defaultButton, pressedButton, focusedButton, disabledButton);
        }

        public ThemeColor getDefaultButton() {
            return defaultButton;
        }

        public ThemeColor getPressedButton() {
            return pressedButton;
        }

        public ThemeColor getFocusedButton() {
            return focusedButton;
        }

        public ThemeColor getDisabledButton() {
            return disabledButton;
        }
    }

}
