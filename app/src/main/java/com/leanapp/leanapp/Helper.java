package com.leanapp.leanapp;

import android.content.Context;
import android.util.Base64;
import android.view.Gravity;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import java.io.UnsupportedEncodingException;

public class Helper {

    public String BASE_URL(){
        byte[] valueDecoded= new byte[0];
        try {
            valueDecoded = Base64.decode("aHR0cHM6Ly9sdW9weS54eXovYXBpLw==".getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
        }
        return new String(valueDecoded);
    }

    public static void connectionErrorToast(Context context){

        SuperActivityToast.create(context, new Style(), Style.TYPE_BUTTON)
                .setText("Internet Not Found")
                .setDuration(Style.DURATION_VERY_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setGravity(Gravity.BOTTOM)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_FADE).show();

    }

    public static void wrongPasswordToast(Context context){

        SuperActivityToast.create(context, new Style(), Style.TYPE_BUTTON)
                .setText("Wrong username or password")
                .setDuration(Style.DURATION_VERY_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setGravity(Gravity.BOTTOM)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_FADE).show();

    }




}
