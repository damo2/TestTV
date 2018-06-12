package com.leimo.common.log.util;

/**
 * Created by wangru
 * Date: 2018/6/1  16:36
 * mail: 1902065822@qq.com
 * describe:
 */

public class LogInfo {
    private String filename;
    private String tag;
    private String msg;
    //根目录
    private String rootDir;
    //子目录
    private String[] subDirs;
    private int level;

    private LogInfo(Builder builder) {
        setFilename(builder.filename);
        setTag(builder.tag);
        setMsg(builder.msg);
        setRootDir(builder.rootDir);
        setSubDirs(builder.subDirs);
        setLevel(builder.level);
    }

    public boolean write() {
        return new LogToDevice().write(this);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDirs) {
        this.rootDir = rootDirs;
    }

    public String[] getSubDirs() {
        return subDirs;
    }

    public void setSubDirs(String[] subDirs) {
        this.subDirs = subDirs;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static final class Builder {
        private String filename;
        private String tag;
        private String msg;
        private String rootDir;
        private String[] subDirs;
        private int level;

        public Builder() {
        }

        /**
         * 文件名
         *
         * @param filename
         * @return
         */
        public Builder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        /**
         * 打印消息内容
         *
         * @param msg
         * @return
         */
        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        /**
         * @param rootDir 根目录
         * @return
         */
        public Builder rootDir(String rootDir) {
            this.rootDir = rootDir;
            return this;
        }

        /**
         * 字文件夹 eg:subDirs("zhangSan","debug")
         *
         * @param subDirs 字文件夹
         * @return
         */
        public Builder subDirs(String... subDirs) {
            this.subDirs = subDirs;
            return this;
        }

        public Builder level(@LogLvType int level) {
            this.level = level;
            return this;
        }

        public LogInfo build() {
            return new LogInfo(this);
        }
    }
}
