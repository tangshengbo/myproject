package com.tangshengbo.nosql.redis;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by Tangshengbo on 2018/6/21
 */
public class HessianSerializer implements RedisSerializer<Object> {

    public byte[] serialize(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream(512);
        Hessian2Output out = new Hessian2Output(bos);
        try {
            out.writeObject(object);
            out.flush();
        } catch (IOException var5) {
            return null;
        }
        return bos.toByteArray();
    }

    public Object deserialize(byte[] bytes) {
        if (ArrayUtils.isEmpty(bytes)) {
            return null;
        }
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        Hessian2Input in = new Hessian2Input(bin);
        try {
            return in.readObject(Object.class);
        } catch (IOException var5) {
            return null;
        }
    }
}
