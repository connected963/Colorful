package org.polaric.colorful;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

public abstract class ColorfulActivity extends AppCompatActivity {
    private String themeString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        themeString = Colorful.getThemeString();

        setUpTheme();

        setUpColorInputs();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Colorful.getThemeString().equals(themeString)) {
            Log.d(Util.LOG_TAG, "Theme change detected, restarting activity");
            recreate();
        }
    }

    private void setUpColorInputs() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResButtonDefault(), true);
            getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResButtonPressed(), true);
            getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResButtonFocused(), true);
            getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResButtonDisabled(), true);

            if (Colorful.getThemeDelegate().isTranslucent()) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            ActivityManager.TaskDescription tDesc =
                    new ActivityManager.TaskDescription(
                            null,
                            null,
                            getResources().getColor(
                                    Colorful.getThemeDelegate().getPrimaryColor().getColorRes()
                            ));

            setTaskDescription(tDesc);

        } else {

            getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResButtonMLollipop(), true);

        }
    }

    private void setUpTheme() {
        setTheme(Colorful.getThemeDelegate().getStyleResBase());
        getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResPrimary(), true);
        getTheme().applyStyle(Colorful.getThemeDelegate().getStyleResAccent(), true);
    }

}
