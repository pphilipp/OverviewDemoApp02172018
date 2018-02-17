/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package philippbugayevskiy.example.com.data.cache;

import android.content.Context;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import philippbugayevskiy.example.com.domain.executor.ThreadExecutor;

/**
 * {@link DefaultCache} implementation.
 */
@Singleton
public class UserCacheImpl implements DefaultCache {

    private static final String SETTINGS_FILE_NAME = "com.valtech.baseline.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final Serializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link UserCacheImpl}.
     *
     * @param context A
     * @param serializer {@link Serializer} for object serialization.
     * @param fileManager {@link FileManager} for saving serialized objects to the file system.
     */
    @Inject
    UserCacheImpl(Context context, Serializer serializer, FileManager fileManager, ThreadExecutor executor) {
        if (context == null || serializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = serializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override
    public boolean isCached() {
        final File userEntityFile = buildFile();

        return fileManager.exists(userEntityFile);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            evictAll();
        }

        return expired;
    }

    @Override
    public void evictAll() {
        executeAsynchronously(new CacheEvictor(fileManager, cacheDir));
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @return A valid file.
     */
    private File buildFile() {
        String fileNameBuilder = cacheDir.getPath() +
                File.separator +
                DEFAULT_FILE_NAME;

        return new File(fileNameBuilder);
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        fileManager.writeToPreferences(context, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE,
                                            currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return fileManager.getFromPreferences(context, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            fileManager.clearDirectory(cacheDir);
        }
    }
}
