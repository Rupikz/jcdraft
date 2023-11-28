package com.kdk.sberuniversity.api.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    public LocalDateTime unmarshal(String ld) {
        return LocalDateTime.parse(ld);
    }

    public String marshal(LocalDateTime ld) {
        return ld.toString();
    }

}
