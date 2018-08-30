package com.tangshengbo.nosql.redis;

import com.tangshengbo.xml.XmlUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by Tangshengbo on 2018/8/30
 */
public class XmlSeralizer implements RedisSerializer<Object> {

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null) {
            return null;
        }
        try {
            String xml = XmlUtil.toXMl(o);
            return xml.getBytes();
        } catch (Exception ex) {
            throw new SerializationException("Cannot serialize object", ex);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return null;
    }
}
