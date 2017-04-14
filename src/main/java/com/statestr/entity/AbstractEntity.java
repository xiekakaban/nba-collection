package com.statestr.entity;

import com.alibaba.fastjson.JSON;
import com.statestr.filter.FastJsonLazyFIilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;

/**
 * Created by ruantianbo on 2017/4/9.
 */
@MappedSuperclass
public abstract class AbstractEntity {

    public String toString() {
//        Field[] fields = {};
//        fields = this.getClass().getDeclaredFields();
//        StringBuilder sb = new StringBuilder();
//        Field field = null;
//        for (int i = 0; i < fields.length; i++) {
//            try {
//                field = fields[i];
//                field.setAccessible(true);
//                sb.append("[").append(fields[i].getName()).append(":").append(fields[i].get(this)).append("]");
//            } catch (IllegalAccessException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
        return JSON.toJSONString(this,new FastJsonLazyFIilter());
    }
}
