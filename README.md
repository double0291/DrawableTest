# DrawableTest

Bitmap.getSize() = bitmap.getByteCount() = bitmap.getHeight() * bitmap.getWidth() * 4 // ARGB_8888，每个像素点占4位

Bitmap Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.snow);
不同的分辨率的手机，decode出来的bitmap的size不同，nexus4的bitmap达到8M，原图的分辨率是600*900，到了nexus4上面变成了1200*1800，再*4等于8M。这跟dpi有关，不同屏幕的dpi不同。如果把高清大图不放在drawable，而是放在xhdpi里面，就会变小，2.16M，600*900；放在xxhdpi里面，就变成了960K，400*600

**所以不同的图片要放在不同的资源文件夹下面**
Android Developer [screens_support](http://developer.android.com/intl/zh-cn/guide/practices/screens_support.html)

bitmap.compress(format, 100, baos);
改变中间的值可以压缩图片，但是还是会保留bitmap的信息，比如分辨率等，所以，压缩后重新放入一个bitmap，bitmap的大小是不变的，保存为文件后会改变大小