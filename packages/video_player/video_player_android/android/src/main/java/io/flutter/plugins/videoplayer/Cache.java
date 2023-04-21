package io.flutter.plugins.videoplayer;

import android.content.Context;

import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider;
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;

public class Cache {
    private static final long CACHE_SIZE = 150 * 1024 * 1024;
    private static volatile SimpleCache instance = null;

    public static SimpleCache getInstance(Context context) {
        if (instance == null) {
            synchronized(Cache.class) {
                if (instance == null) {
                    DatabaseProvider cacheIndex = new StandaloneDatabaseProvider(context);
                    instance = new SimpleCache(new File(context.getCacheDir(), "video"), new LeastRecentlyUsedCacheEvictor(CACHE_SIZE), cacheIndex);
                }
            }
        }
        return instance;
    }
}
