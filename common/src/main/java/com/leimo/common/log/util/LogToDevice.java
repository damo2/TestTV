package com.leimo.common.log.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.leimo.common.basicdata.ArrayUtil;
import com.leimo.common.file.FileSave;
import com.leimo.common.file.FileUtil;
import com.leimo.common.log.LogSD;
import com.leimo.common.path.SDPathUtils;
import org.w3c.dom.Text;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * 打印日志到手机文件
 * Created by wangru
 * Date: 2017/9/12  19:47
 * mail: 1902065822@qq.com
 * describe:
 * LogToDevice.setIsDebug(true);
 * LogToDevice.setRootDirDefault(SDPathUtils.getSDCardPublicDir(getApplicationContext(),"logTest"));
 * new LogInfo.Builder().filename("test").tag(TAG).subDir("abc","def").msg("啦啦啦").build().write();
 */
public class LogToDevice {
    private static final long LOG_MAX_LENGTH = 30 * 1024;//30k 每个日志文件最大大小(B)
    private static final String FILENAME_SUBDIRS = "subDirsDefalt.txt";
    private static final String FILENAME_ROOT = "rootDirsDefalt.txt";
    private static String TAG = LogToDevice.class.getSimpleName();
    private static boolean sIsDebug = false;
    //根目录
    private static String mRootDir;
    private static String sRootDirDefault;//log日志存放路径
    //子目录
    private static String[] mSubDirs;
    private static String[] sSubDirDefault;//子目录

    private String filePath;
    private LogInfo mLogInfo;


    public static void setIsDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }

    public static void setRootDirDefault(String rootDirDefault) {
        sRootDirDefault = rootDirDefault;

    }

    public static String getRootDirDefault() {
        if (TextUtils.isEmpty(sRootDirDefault)) {
            return SDPathUtils.getSDCardPublicDir("loginfo");
        } else {
            return sRootDirDefault;
        }
    }

    private static String getRootDir() {
        if (TextUtils.isEmpty(mRootDir)) {
            return getRootDirDefault();
        }
        return mRootDir;
    }

    private static void saveSubDirs(String... dirs) {
        String path = getRootDir() + "/" + FILENAME_SUBDIRS;
        if (new File(path).exists()) {
            new File(path).delete();
        }
        FileSave.saveStringToSD(path, Arrays.asList(dirs).toString());
    }

    private static String[] readSubDirsLog() {
        String[] dirsLog = null;
        String path = getRootDir() + "/" + FILENAME_SUBDIRS;
        String dirs = FileSave.getStringFromSDCard(path);
        if (!TextUtils.isEmpty(dirs) && dirs.contains(",")) {
            dirsLog = dirs.split(",");
        }
        return dirsLog;
    }

    /**
     * 获取打印信息所在方法名，行号等信息
     *
     * @return 类名、方法名、行号
     */
    private static String[] getAutoJumpLogInfos() {
        String[] infos = new String[] {"", "", ""};
        StackTraceElement elements = StackUtil.getTargetStackTraceElement(LogSD.class, LogToDevice.class, LogInfo.class);
        infos[0] = elements.getClassName().substring(elements.getClassName().lastIndexOf(".") + 1);
        infos[1] = elements.getMethodName() + "()";
        infos[2] = " at (" + elements.getClassName() + ".java:" + elements.getLineNumber() + ")";
        return infos;
    }

    private String[] getSubDirsDefault() {
        if (sSubDirDefault == null) {
            synchronized (this) {
                if (sSubDirDefault == null) {
                    sSubDirDefault = readSubDirsLog();
                }
            }
        }
        return sSubDirDefault;
    }

    public static void setSubDirsDefault(String... subDirDefaults) {
        sSubDirDefault = subDirDefaults;
    }

    private String[] getSubDirs() {
        return mSubDirs;
    }

    public boolean write(LogInfo logInfo) {
        if (sIsDebug) {
            mLogInfo = logInfo;
            if (init()) {
                String log = getLogWriteInfo(logInfo);
                filePath = getLogPath(null);
                return writeToFile(filePath, log);
            }
        }
        return false;
    }

    private boolean init() {
        if (mLogInfo == null) {
            Log.e(TAG, "write: logInfo 为空");
            return false;
        }
        if (SDPathUtils.isExist(mLogInfo.getRootDir())) {
            mRootDir = mLogInfo.getRootDir();
        }
        if (ArrayUtil.isNoEmpty(mLogInfo.getSubDirs())) {
            mSubDirs = mLogInfo.getSubDirs();
        } else {
            mSubDirs = getSubDirsDefault();
        }
        if (!SDPathUtils.isExist(getRootDir())) {
            Log.e(TAG, "write: 打印日志目录为空或不存在");
            return false;
        }
        if (sSubDirDefault != null) {
            saveSubDirs(sSubDirDefault);
        }
        return true;
    }

    // 将log信息写入文件中
    private boolean writeToFile(String filePath, String log) {
        boolean isSuc = false;
        //FileOutputStream会自动调用底层的close()方法，不用关闭
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            //这里的第二个参数代表追加还是覆盖，true为追加，flase为覆盖
            fos = new FileOutputStream(filePath, true);
            bw = new BufferedWriter(new OutputStreamWriter(fos, Charset.defaultCharset()));
            bw.write(log);
            isSuc = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSuc = false;
        } finally {
            try {
                if (bw != null) {
                    //关闭缓冲流
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isSuc;
    }

    @NonNull
    private String getLogWriteInfo(LogInfo logInfo) {
        String type = "INFO";
        if (logInfo.getLevel() == Lv.d) {
            type = "DEBUG";
        } else if (logInfo.getLevel() == Lv.e) {
            type = "ERROR";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US).format(new Date()));
        stringBuffer.append("  #" + type);
        if (!TextUtils.isEmpty(logInfo.getTag())) {
            stringBuffer.append("  #" + logInfo.getTag());
        }
        try {
            String[] functionInfos = getAutoJumpLogInfos();
            if (functionInfos != null) {
                stringBuffer.append("\n");
                for (String fun : functionInfos) {
                    stringBuffer.append("#" + fun);
                }
                stringBuffer.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        stringBuffer.append(logInfo.getMsg());
        stringBuffer.append("\n\n\n");
        return stringBuffer.toString();
    }

    //得到文件名
    private String getLogPath(String path) {
        if (TextUtils.isEmpty(path)) {
            filePath = getFilePath() + ".log";
        }
        File logFile = new File(filePath);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long logFileSize = FileUtil.getFileSize(filePath);
        if (logFileSize >= LOG_MAX_LENGTH) {
            int temp = 0;
            if (filePath.contains("(")) {
                temp = Integer.parseInt(filePath.substring(filePath.indexOf("(") + 1, filePath.lastIndexOf(")")));
            }
            filePath = getFilePath() + "(" + (++temp) + ")" + ".log";
            return getLogPath(filePath);
        } else {
            return filePath;
        }
    }

    @NonNull
    private String getFilePath() {
        String time = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
        StringBuilder dirBuilder = new StringBuilder(getRootDir() + File.separator + time);
        String[] dirs = getSubDirs();
        if (dirs != null && dirs.length > 0) {
            for (int i = 0; i < dirs.length; i++) {
                dirBuilder.append(File.separator + dirs[i]);
            }
        }
        String dir = dirBuilder.toString();
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = "log" + (TextUtils.isEmpty(mLogInfo.getFilename()) ? "" : "_" + mLogInfo.getFilename());
        if (!file.exists()) {
            dir = getRootDir();
        }
        return dir + File.separator + filename;
    }
}
