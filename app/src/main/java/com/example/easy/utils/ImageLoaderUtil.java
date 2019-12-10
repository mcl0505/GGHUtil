package com.example.easy.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.easy.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 网络图片加载
 */
public class  ImageLoaderUtil {
    private static final int THREAD_COUNT = 4;//标明我们uil最多可以有多少条线程
    private static final int PRIORITY = 2;//图片加载优先级
    private static final int MEMORY_CACHE_SIZE = 2 * 1024 * 1024;//最大缓存大小
    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;
    private static final int CONNECTION_TIME_OUT = 5 * 1000;//链接超时时间
    private static final int READ_TIME_OUT = 30 * 1000;//读写超时时间

    private static ImageLoaderUtil mInstance = null;
    private static ImageLoader mLoader = null;
    private Context context;

    public static ImageLoaderUtil getInstance(Context context){
        if (mInstance == null){
            synchronized (ImageLoaderUtil.class){
                if (mInstance == null){
                    mInstance = new ImageLoaderUtil(context);
                }
            }
        }
        return mInstance;
    }

    private ImageLoaderUtil(Context context){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(THREAD_COUNT)//缓存线程数量
                .threadPriority(Thread.NORM_PRIORITY - PRIORITY)//优先级   不同的手机优先级可能不一样，利用Thread.NORM_PRIORITY来获取手机的优先级
                .denyCacheImageMultipleSizesInMemory()//防止缓存多套图片到我们的内存中
                //.memoryCache(new UsingFreqLimitedMemoryCache(MEMORY_CACHE_SIZE))
                .memoryCache(new WeakMemoryCache())//使用弱引用，当内存不足时回收图片
                .diskCacheSize(DISK_CACHE_SIZE)//分配硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)//图片下载顺序
                .defaultDisplayImageOptions(getDefaultOptions())//默认的图片加载Option
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME_OUT, READ_TIME_OUT))//设置图片加载器
                .writeDebugLogs()//是否写日志  debug模式下
                .build();

        ImageLoader.getInstance().init(config);
        mLoader = ImageLoader.getInstance();

    }

    /**
     * 默认的图片加载
     * @return
     */
    private DisplayImageOptions getDefaultOptions() {
        DisplayImageOptions options = new
                DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.error)//在我们图片地址为空时显示
                .showImageOnFail(R.mipmap.error)//在我们图片下载失败时显示
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中, 重要，否则图片不会缓存到内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中, 重要，否则图片不会缓存到硬盘中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .decodingOptions(new BitmapFactory.Options())//设置图片的解码配置
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();
        return options;
    }

    /**
     * 默认的图片加载  圆形
     * @return
     */
    private DisplayImageOptions getDefaultOptionsCircle() {
        DisplayImageOptions options = new
                DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher_background)//在我们图片地址为空时显示
                .showImageOnFail(R.drawable.ic_launcher_background)//在我们图片下载失败时显示
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中, 重要，否则图片不会缓存到内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中, 重要，否则图片不会缓存到硬盘中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .displayer(new RoundedBitmapDisplayer(360))
                .decodingOptions(new BitmapFactory.Options())//设置图片的解码配置
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();
        return options;
    }

    /**
     * 图片加载
     * @param imageView   图片控件
     * @param url   地址String
     * @param options  配置
     * @param loadingListener   图片加载监听
     */
    private void displayImage(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener loadingListener){
        if (mLoader != null){
            mLoader.displayImage(url,imageView,options,loadingListener);
        }
    }

    /**
     * 图片加载
     * @param imageView   图片控件
     * @param url   地址 int
     * @param options  配置
     * @param loadingListener   图片加载监听
     */
    private void displayImage(ImageView imageView, int url, DisplayImageOptions options, ImageLoadingListener loadingListener){
        if (mLoader != null){
            mLoader.displayImage("drawable://"+url,imageView,options,loadingListener);
        }
    }

    /**
     * 加载圆形图片
     * @param imageView   控件
     * @param url  String地址
     * @param loadingListener
     */
    private void displayCircleImage(ImageView imageView, int url, ImageLoadingListener loadingListener){
        if (mLoader != null){
            mLoader.displayImage("drawable://"+url,imageView,getDefaultOptionsCircle(),loadingListener);
        }
    }

    /**
     * 加载圆形图片
     * @param imageView   控件
     * @param url  int 地址
     * @param loadingListener
     */
    private void displayCircleImage(ImageView imageView, String url,ImageLoadingListener loadingListener){
        if (mLoader != null){
            mLoader.displayImage(url,imageView,getDefaultOptionsCircle(),loadingListener);
        }
    }













    public static void loader(ImageView imageView,String url){
        mInstance.displayImage(imageView,url,null,null);
    }

    public static void loader(ImageView imageView,int url){
        mInstance.displayImage(imageView,url,null,null);
    }

    public static void loaderCircle(ImageView imageView,int url){
        mInstance.displayCircleImage(imageView,url,null);
    }

    public static void loaderCircle(ImageView imageView,String url){
        mInstance.displayCircleImage(imageView,url,null);
    }


}
