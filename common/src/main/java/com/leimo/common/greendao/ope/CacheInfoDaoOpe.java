package com.leimo.common.greendao.ope;

import android.text.TextUtils;
import com.leimo.common.greendao.bean.CacheInfoEntity;
import com.leimo.common.greendao.gen.CacheInfoEntityDao;
import com.leimo.common.greendao.manage.DbManager;

/**
 * 缓存操作
 * Created by wangru
 * Date: 2018/1/11  16:03
 * mail: 1902065822@qq.com
 * describe:
 */

public class CacheInfoDaoOpe {

    private static CacheInfoEntityDao getCacheInfoDao() {
        return DbManager.instance().getDaoSession().getCacheInfoEntityDao();
    }


    public static String getVersionCodeByKey(String key) {
        CacheInfoEntity cacheInfo = getCacheInfoByKey(key);
        if (cacheInfo != null) {
            return cacheInfo.getVersionCode();
        }
        return null;
    }

    public static void insertOrUpdateVersion(CacheInfoEntity cacheInfoEntity) {
        if (cacheInfoEntity != null && !TextUtils.isEmpty(cacheInfoEntity.getKey())) {
            insertOrUpdateVersionByKey(cacheInfoEntity.getKey(), cacheInfoEntity);
        }
    }

    public static void insertOrUpdateVersionByKey(String key, CacheInfoEntity cacheInfoEntity) {
        if (cacheInfoEntity == null) {
            return;
        }
        cacheInfoEntity.setKey(key);
        CacheInfoEntity cacheInfo = getCacheInfoByKey(key);
        if (cacheInfo != null) {
            cacheInfoEntity.set_id(cacheInfo.get_id());
        }
        getCacheInfoDao().save(cacheInfoEntity);
    }

    public static CacheInfoEntity getCacheInfoByKey(String key) {
        return getCacheInfoDao().queryBuilder().where(CacheInfoEntityDao.Properties.Key.eq(key)).limit(1).build().unique();
    }

    public static String getCacheInfoDataByKey(String key) {
        CacheInfoEntity cacheInfo = getCacheInfoByKey(key);
        if (cacheInfo != null) {
            return cacheInfo.getData();
        }
        return null;
    }

}
