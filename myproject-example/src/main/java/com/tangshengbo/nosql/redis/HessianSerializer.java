package com.tangshengbo.nosql.redis;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Tangshengbo on 2018/6/21
 */
public class HessianSerializer implements RedisSerializer<Object> {

    public byte[] serialize(Object object) {
        if (object == null) {
            return null;
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(512);
            Hessian2Output out = new Hessian2Output(bos);

            try {
                out.writeObject(object);
                out.flush();
            } catch (IOException var5) {
            }
            return bos.toByteArray();
        }
    }

    public Object deserialize(byte[] bytes) {
        if (bytes != null && bytes.length != 0) {
            ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
            Hessian2Input in = new Hessian2Input(bin);

            try {
                return in.readObject(Object.class);
            } catch (IOException var5) {
                return null;
            }
        } else {
            return null;
        }
    }
}
