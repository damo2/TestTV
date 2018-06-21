package com.leimo.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wangru
 * Date: 2018/4/4  11:08
 * mail: 1902065822@qq.com
 * describe:
 */

public class GsonConvert{

    public static <T> T jsonToBean(String reader, Class<T> clazz, Class classType) {
        Type typeT = new ParameterizedTypeImpl(clazz, new Class[] {classType});
        return new Gson().fromJson(reader, typeT);
    }

    public static <T> List<T> jsonToBeanList(String json, Class<T> classType) {
        Type listType = new ParameterizedTypeImpl(List.class, new Class[] {classType});
        List<T> list = new Gson().fromJson(json, listType);
        return list;
    }

    public static <T,R> List<T> jsonToBeanList2(String json, Class<T> classType, Type typeJ, JsonDeserializer jsonDeserializer) {
        Type listType = new ParameterizedTypeImpl(List.class, new Class[] {classType});
        Gson gson = new GsonBuilder().registerTypeAdapter(typeJ, jsonDeserializer).create();
        List<T> list = gson.fromJson(json, listType);
        return list;
    }

//    public static <T> DataResponse<List<T>> fromJsonToDataResponseArray(String reader, Class<T> classType) {
//        // 生成List<T> 中的 List<T>
//        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{classType});
//        // 根据List<T>生成完整的Result<List<T>>
//        Type type = new ParameterizedTypeImpl(DataResponse.class, new Type[]{listType});
//        return new Gson().fromJson(reader, type);
//    }

    public static class ParameterizedTypeImpl implements ParameterizedType {
        private final Class raw;
        private final Type[] args;

        public ParameterizedTypeImpl(Class raw, Type[] args) {
            this.raw = raw;
            this.args = args != null ? args : new Type[0];
        }

        @Override
        public Type[] getActualTypeArguments() {
            return args;
        }

        @Override
        public Type getRawType() {
            return raw;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
