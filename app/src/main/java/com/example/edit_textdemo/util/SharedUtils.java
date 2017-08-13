package com.example.edit_textdemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 本类为sharedpreferences保存类    xml本地保存类
 * sharedpreferences：android 五大存储方式之一，存储数据类型为： K  V  文件已xml形式保存
 * 项目应用：1.导航页是否第一次进入
 * 2.用户信息，登陆信息
 * 好处：只要不卸载软件，或者不手动清除，基本上不会被清除
 */
public class SharedUtils {
    private String name = "login";
    private String person_msg = "info";
    private String photo = "bitmap";

    /*
     * 保存数据的方法
     * */
    public void saveShared(String key, String value, Context ctx) {
        SharedPreferences shared = ctx.getSharedPreferences(name, 0);
        Editor edit = shared.edit();
        edit.putString(key, value);
        edit.commit();
    }

    /*
     * 从本地获取数据
     * */
    public String getShared(String key, Context ctx) {
        String str = null;
        SharedPreferences shared = ctx.getSharedPreferences(name, 0);
        str = shared.getString(key, "");
        return str;
    }

    public void saveShared_info(String key, String value, Context ctx) {
        SharedPreferences shared = ctx.getSharedPreferences(person_msg, 0);
        Editor edit = shared.edit();
        edit.putString(key, value);
        edit.commit();
    }

    /*
     * 从本地获取数据
     * */
    public String getShared_info(String key, Context ctx) {
        String str = null;
        SharedPreferences shared = ctx.getSharedPreferences(person_msg, 0);
        str = shared.getString(key, "");
        return str;
    }

    /*
     * 保存int类型数据的方法
     * */
    public void saveShared_int(String key, int value, Context ctx) {
        SharedPreferences shared = ctx.getSharedPreferences(name, 0);
        Editor edit = shared.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    /*
     * 从本地获取int类型数据
     * */
    public int getShared_int(String key, Context ctx) {
        int str = 0;
        SharedPreferences shared = ctx.getSharedPreferences(name, 0);
        str = shared.getInt(key, 0);
        return str;
    }

    /*
    * 保存bitmap图片
    * */
    public void saveDrawable(String key, Bitmap bitmap, Context ctx) {
        SharedPreferences shared = ctx.getSharedPreferences(photo, 0);
        Editor edit = shared.edit();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
        String imageBase64 = new String(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
        edit.putString(key, imageBase64);
        edit.commit();
    }

    /*
    * 获取bitmap图片
    * */
    public Drawable getDrawable(String key, Context ctx) {
        SharedPreferences shared = ctx.getSharedPreferences(photo, 0);
        String temp = shared.getString(key, "");
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
        Drawable drawable = Drawable.createFromStream(bais, "");
        return drawable;
    }

    /*
    * drawable转换成bitmap
    * */
    public static Bitmap drawableToBitamp(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}
