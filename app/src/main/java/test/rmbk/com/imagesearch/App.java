package test.rmbk.com.imagesearch;


import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiscCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.io.File;
import java.io.IOException;


public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        setupImageLoader();
    }

    private void setupImageLoader() {
        File cacheDir;
        File persistentCacheDir;
        if (BuildConfig.DEBUG) {
            cacheDir = new File(Environment.getExternalStorageDirectory() + "/debug/uil/tmpcache");
            persistentCacheDir = new File(Environment.getExternalStorageDirectory() + "/debug/uil/perscache");
        } else {
            cacheDir = new File(context.getCacheDir(), "uil");
            persistentCacheDir = new File(context.getExternalFilesDir(null), "uil");
        }

        cacheDir.mkdirs();
        persistentCacheDir.mkdirs();

        DiskCache tmpCache;
        DiskCache persistentCache = new UnlimitedDiscCache(persistentCacheDir);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(20))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .diskCache(persistentCache)
                .build();

        ImageLoader.getInstance().init(config);
    }
}
