package com.kdk.sberuniversity.audit.services;

import com.kdk.sberuniversity.audit.AuditAction;

public interface AuditService {

    /**
     * Отправка события в систему аудита
     *
     * @param event XML- представление сущности
     */
    void sendEvent(String event);
    <T> void sendEvent(T entity, AuditAction action);

}
