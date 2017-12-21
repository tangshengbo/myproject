package com.tangshengbo.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Objects;

/**
 * Created by Tangshengbo on 2017/12/21.
 */
public class JAXBUtil<T> {

    /**
     * 对象转换为xml
     *
     * @param element
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    public String marshal(T element) throws JAXBException, IOException {
        ByteArrayOutputStream os = null;
        String xml = null;
        try {
            os = new ByteArrayOutputStream();
            JAXBContext jc = JAXBContext.newInstance(element.getClass());
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(element, os);
            xml = new String(os.toByteArray(), "UTF-8");
        } finally {
            if (Objects.nonNull(os)) {
                os.close();
            }
        }
        return xml;

    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public T unmarshal(Class c, String xml) throws JAXBException {
        JAXBContext context;
        context = JAXBContext.newInstance(c);
        Unmarshaller unmoral = context.createUnmarshaller();
        return (T) unmoral.unmarshal(new StringReader(xml));

    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public T unmarshal(Class c, InputStream is) throws JAXBException {
        JAXBContext context;
        context = JAXBContext.newInstance(c);
        Unmarshaller unmoral = context.createUnmarshaller();
        return (T) unmoral.unmarshal(is);
    }
}
