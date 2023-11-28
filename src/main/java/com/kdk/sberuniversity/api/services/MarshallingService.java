package com.kdk.sberuniversity.api.services;

import com.kdk.sberuniversity.core.exceptions.HttpServerMappingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

import static com.kdk.sberuniversity.core.constants.ExceptionConstants.CANNOT_MARSHALL_DATA;
import static com.kdk.sberuniversity.core.constants.ExceptionConstants.CANNOT_UNMARSHALL_DATA;

public class MarshallingService {

    public static <T> String marshall(T content) {
        if (content == null) {
            return null;
        }

        try {
            JAXBContext context = JAXBContext.newInstance(content.getClass());
            Marshaller marshaller = MarshallingService.getMarshaller(context);

            StringWriter writer = new StringWriter();
            marshaller.marshal(content, writer);

            return writer.toString();
        } catch (JAXBException ex) {
            throw new HttpServerMappingException(CANNOT_MARSHALL_DATA, ex);
        }
    }

    public static <T, U> U unmarshall(String payload, Class<T> parent, Class<U> child) {
        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance(parent, child).createUnmarshaller();
            Object obj = unmarshaller.unmarshal(new StringReader(payload));
            return child.cast(obj);
        } catch (JAXBException ex) {
            throw new HttpServerMappingException(CANNOT_UNMARSHALL_DATA, ex);
        }
    }

    private static Marshaller getMarshaller(JAXBContext context) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

}
