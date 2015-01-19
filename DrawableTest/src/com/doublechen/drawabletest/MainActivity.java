package com.doublechen.drawabletest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private static final String TAG = "DrawableTest";
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.imageView);

        // 获取图片
        Bitmap oldBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.snow);
        Log.d(TAG, "bitmap size: " + BitmapUtils.getSize(oldBitmap));
        Log.d(TAG, "bitmap height: " + oldBitmap.getHeight());
        Log.d(TAG, "bitmap width: " + oldBitmap.getWidth());

        // 压缩图片
        byte[] oldData = BitmapUtils.bitmap2Bytes(oldBitmap, Bitmap.CompressFormat.JPEG);
        Bitmap bitmap = BitmapUtils.compress(oldData, this);
        oldBitmap.recycle();
        oldBitmap = null;
        Log.d(TAG, "compressed bitmap size: " + BitmapUtils.getSize(bitmap));


        // 设置ImageView的宽度
        int imageViewWidth = DisplayUtils.getScreenWidthInPx(this) - DisplayUtils.dp2px(2 * 20, getResources());
        mImageView.getLayoutParams().width = imageViewWidth;
        // 设置ImageView的高度
        int imageViewHeight = imageViewWidth * bitmap.getHeight() / bitmap.getWidth();
        mImageView.getLayoutParams().height = imageViewHeight;
        // 刷一下
        mImageView.invalidate();

        mImageView.setImageBitmap(bitmap);

//        Drawable drawable = getResources().getDrawable(R.drawable.bridge);
//        mImageView.setBackgroundDrawable(drawable);
    }

}
