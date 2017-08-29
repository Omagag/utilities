package com.bbva.intranet.utilities;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

/**
 * Created by Omar on 3/9/17.
 */
public abstract class MemcacheGAEUtility {

    private static final Logger logger = LoggerFactory.getLogger(MemcacheGAEUtility.class);

    private static MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();

    public static void storageObjectIntoCache(String key, Object obj, boolean replace) {
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

        logger.info(String.format("Adding object with key [%s]", key));

        String dateOfSavedKey = String.format("%sTime", key);

        DateTime dateTime = new DateTime();
        Long dateTimeMillist = dateTime.getMillis();

        if (!contains(key) || replace) {
            syncCache.put(dateOfSavedKey, dateTimeMillist);
            syncCache.put(key, obj);
            logger.info(String.format("Added object with key [%s]", key));
        }
    }

    public static boolean contains(String key) {
        return syncCache.contains(key);
    }

    public static Object retrieveObjectFromStorage(String key) {
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

        logger.info(String.format("Retrieving object with key [%s]", key));

        return syncCache.get(key);
    }

    public static Object retrieveDateOfSavedOfObjectFromStorage(String key) {
        logger.info(String.format("Retrieving date of saved of object with key [%s]", key));

        return retrieveObjectFromStorage(buildDateOfSavedKey(key));
    }

    public static void removeObjectFromStorage(String key) {
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

        logger.info(String.format("Removing object with key [%s]", key));

        syncCache.delete(key);
        syncCache.delete(buildDateOfSavedKey(key));
    }

    private static String buildDateOfSavedKey(String key) {
        return String.format("%sTime", key);
    }

}
