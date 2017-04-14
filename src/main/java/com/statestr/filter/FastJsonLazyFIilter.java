package com.statestr.filter;

import com.alibaba.fastjson.serializer.PropertyFilter;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

/**
 * Created by ruantianbo on 2017/4/12.
 */
public class FastJsonLazyFIilter implements PropertyFilter{
    @Override
    public boolean apply(Object o, String name, Object value) {
        if(value instanceof HibernateProxy){
            LazyInitializer lazyInitializer = ((HibernateProxy)value).getHibernateLazyInitializer();
            //还未初始化
            if(lazyInitializer.isUninitialized()){
                return false;
            }
            //实体关联集合一对多等
        }else if(value instanceof PersistentCollection){
            PersistentCollection collection = (PersistentCollection) value;
            if (!collection.wasInitialized()) {
                return false;
            }
            Object val = collection.getValue();
            if (val == null) {
                return false;
            }
        }
        return true;
    }
}
