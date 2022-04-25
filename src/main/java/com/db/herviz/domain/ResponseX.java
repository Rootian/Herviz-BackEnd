package com.db.herviz.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-04-17
 */
@Data
public class ResponseX {

    public static String success(Object data) {
        JSONObject obj = new JSONObject();
        obj.put("code", 200);
        obj.put("data", data);

        return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static String fail(String message) {
        JSONObject obj = new JSONObject();
        obj.put("code", 500);
        obj.put("message", message);

        return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static String page(Object data, long total) {
        JSONObject obj = new JSONObject();
        obj.put("code", 200);
        obj.put("data", data);
        obj.put("total", total);

        return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
    }
}
