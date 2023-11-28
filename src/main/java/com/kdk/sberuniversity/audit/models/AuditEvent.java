package com.kdk.sberuniversity.audit.models;

import com.kdk.sberuniversity.api.adapters.LocalDateTimeAdapter;
import com.kdk.sberuniversity.api.models.car.Car;
import com.kdk.sberuniversity.api.models.order.Order;
import com.kdk.sberuniversity.api.models.person.Person;
import com.kdk.sberuniversity.audit.AuditAction;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "auditEvent", propOrder = {
        "actionDateTime", "entity", "action", "body"
})
@XmlSeeAlso({Car.class, Person.class, Order.class})
@XmlRootElement(name = "auditEvent")
public final class AuditEvent<T> {

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime actionDateTime;
    private String entity;
    private AuditAction action;
    private AuditBody<T> body;

    public AuditEvent(LocalDateTime actionDateTime, String entity, AuditAction action, AuditBody<T> body) {
        this.actionDateTime = actionDateTime;
        this.entity = entity;
        this.action = action;
        this.body = body;
    }

    public AuditEvent() {
    }

    public LocalDateTime getActionDateTime() {
        return actionDateTime;
    }

    public void setActionDateTime(LocalDateTime actionDateTime) {
        this.actionDateTime = actionDateTime;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public AuditAction getAction() {
        return action;
    }

    public void setAction(AuditAction action) {
        this.action = action;
    }

    public AuditBody<T> getBody() {
        return body;
    }

    public void setBody(AuditBody<T> body) {
        this.body = body;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "audit_body", propOrder = {"entity"})
    @XmlRootElement(name = "audit_body")
    public static class AuditBody<T> {

        @XmlAnyElement(lax = true)
        private T entity;

        public AuditBody(T entity) {
            this.entity = entity;
        }

        public AuditBody() {
        }

        public T getEntity() {
            return entity;
        }

        public void setEntity(T entity) {
            this.entity = entity;
        }

    }

}
