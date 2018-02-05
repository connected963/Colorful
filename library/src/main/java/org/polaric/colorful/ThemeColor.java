package org.polaric.colorful;

import android.support.annotation.ColorRes;

public enum ThemeColor {
    RED(R.color.md_red_500, R.color.md_red_700),
    PINK(R.color.md_pink_500, R.color.md_pink_700),
    PURPLE(R.color.md_purple_500, R.color.md_purple_700),
    DEEP_PURPLE(R.color.md_deep_purple_500, R.color.md_deep_purple_700),
    INDIGO(R.color.md_indigo_500, R.color.md_indigo_700),
    BLUE(R.color.md_blue_500, R.color.md_blue_700),
    LIGHT_BLUE(R.color.md_light_blue_500, R.color.md_light_blue_700),
    CYAN(R.color.md_cyan_500, R.color.md_cyan_700),
    TEAL(R.color.md_teal_500, R.color.md_teal_700),
    GREEN(R.color.md_green_500, R.color.md_green_700),
    LIGHT_GREEN(R.color.md_light_green_500, R.color.md_light_green_700),
    LIME(R.color.md_lime_500, R.color.md_lime_700),
    YELLOW(R.color.md_yellow_500, R.color.md_yellow_700),
    AMBER(R.color.md_amber_500, R.color.md_amber_700),
    ORANGE(R.color.md_orange_500, R.color.md_orange_700),
    DEEP_ORANGE(R.color.md_deep_orange_500, R.color.md_deep_orange_700),
    BROWN(R.color.md_brown_500, R.color.md_brown_700),
    GREY(R.color.md_grey_500, R.color.md_grey_700),
    BLUE_GREY(R.color.md_blue_grey_500, R.color.md_blue_grey_700),
    WHITE(R.color.md_white_1000, R.color.md_white_1000),
    BLACK(R.color.md_black_1000, R.color.md_black_1000);

    @ColorRes
    private int colorRes;

    @ColorRes
    private int darkColorRes;

    ThemeColor(@ColorRes int colorRes, @ColorRes int darkColorRes) {
        this.colorRes = colorRes;
        this.darkColorRes = darkColorRes;
    }

    public @ColorRes
    int getColorRes() {
        return colorRes;
    }

    public @ColorRes
    int getDarkColorRes() {
        return darkColorRes;
    }

}
