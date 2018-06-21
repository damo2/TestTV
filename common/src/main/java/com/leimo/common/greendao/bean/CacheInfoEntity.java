package com.leimo.common.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 缓存
 * Created by wangru
 * Date: 2018/1/11  14:26
 * mail: 1902065822@qq.com
 * describe:
 */
@Entity
public class CacheInfoEntity {
    @Id(autoincrement = true)
    private Long _id;
    //根据key 存取数据
    private String key;
    private String versionCode;
    //保存数据
    private String data;
    private String ext;
    //创建时间戳
    private long timeCreate;
    //保存时长(ms)
    private long lengthTime;

    @Generated(hash = 1554682834)
    public CacheInfoEntity(Long _id, String key, String versionCode, String data,
                           String ext, long timeCreate, long lengthTime) {
        this._id = _id;
        this.key = key;
        this.versionCode = versionCode;
        this.data = data;
        this.ext = ext;
        this.timeCreate = timeCreate;
        this.lengthTime = lengthTime;
    }

    @Generated(hash = 897008331)
    public CacheInfoEntity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExt() {
        return this.ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public long getTimeCreate() {
        return this.timeCreate;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
    }

    public long getLengthTime() {
        return this.lengthTime;
    }

    public void setLengthTime(long lengthTime) {
        this.lengthTime = lengthTime;
    }

}
