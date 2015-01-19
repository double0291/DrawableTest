package com.doublechen.drawabletest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import java.io.ByteArrayOutputStream;

public class BitmapUtils {
    /**
     * 获取Bitmap的大小
     */
    public static int getSize(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        } else {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat format) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(format, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap compress(byte[] data, Activity activity) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        /* 不分配图片内存，获取图片参数 */
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        /*正式压缩*/
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateInSampleSize(options, activity);
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);

        return bitmap;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, Activity activity) {
        // 屏幕宽高
        int screenWidth = DisplayUtils.getScreenWidthInPx(activity);
        int screenHeight = DisplayUtils.getScreenHeightInPx(activity);
        // 图片原始宽高
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;

        // 计算缩放比
        int inSampleSize = 1;
        while (imageHeight > screenHeight && imageWidth > screenWidth) {
            int heightRatio = Math.round((float) imageHeight / (float) screenHeight);
            int widthRatio = Math.round((float) imageWidth / (float) screenWidth);

            int ratio = heightRatio > widthRatio ? heightRatio : widthRatio;
            if (ratio >= 2) {
                imageWidth >>= 1;
                imageHeight >>= 1;
                inSampleSize <<= 1;
            } else {
                break;
            }
        }
        return inSampleSize;
    }
}
