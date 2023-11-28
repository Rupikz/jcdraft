package com.kdk.sberuniversity.audit.services;

import com.kdk.sberuniversity.api.mappers.car.CarMapper;
import com.kdk.sberuniversity.api.mappers.order.OrderMapper;
import com.kdk.sberuniversity.api.mappers.person.PersonMapper;
import com.kdk.sberuniversity.audit.AuditAction;
import com.kdk.sberuniversity.audit.annotation.Auditable;
import com.kdk.sberuniversity.audit.models.AuditEvent;
import com.kdk.sberuniversity.logging.LoggerHolder;
import com.kdk.sberuniversity.persistence.repository.car.dao.CarDAO;
import com.kdk.sberuniversity.persistence.repository.order.dao.OrderDAO;
import com.kdk.sberuniversity.persistence.repository.person.dao.PersonDAO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public final class AuditServiceImpl implements AuditService {

    private final CarMapper carMapper;
    private final PersonMapper personMapper;
    private final OrderMapper orderMapper;

    public AuditServiceImpl(CarMapper carMapper, PersonMapper personMapper, OrderMapper orderMapper) {
        this.carMapper = carMapper;
        this.personMapper = personMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public void sendEvent(String event) {
        LoggerHolder.auditLogger.info(event);
    }

    public <T> void sendEvent(T entity, AuditAction action) {
        if (!isAuditableMethod()) {
            return;
        }

        try {
            Object model = mapper(entity);
            String entityType = model.getClass().getSimpleName().toUpperCase();
            AuditEvent<Object> auditEvent = new AuditEvent<>(
                    LocalDateTime.now(), entityType, action, new AuditEvent.AuditBody<>(model)
            );
            sendEvent(marshall(auditEvent));
        } catch (Exception exception) {
            LoggerHolder.rootLogger.error("Audit service couldn't log the entity change. Cause: " + exception.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private <T, R> R mapper(T entity) {
        if (entity instanceof CarDAO) {
            return (R) carMapper.mapToCar((CarDAO) entity);
        } else if (entity instanceof PersonDAO) {
            return (R) personMapper.mapToPerson((PersonDAO) entity);
        } else if (entity instanceof OrderDAO) {
            return (R) orderMapper.mapToOrder((OrderDAO) entity);
        } else {
            throw new IllegalArgumentException("Unknown auditable entity");
        }
    }

    private <T> String marshall(AuditEvent<T> event) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(event.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(event, writer);

        return writer.toString();
    }

    private boolean isAuditableMethod() {
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Class<?> aClass = Class.forName(stackTraceElement.getClassName());
            String methodName = stackTraceElement.getMethodName();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName) && method.getAnnotation(Auditable.class) != null) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }

}
