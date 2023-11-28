package com.kdk.sberuniversity.api.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String ld) {
        return LocalDate.parse(ld);
    }

    public String marshal(LocalDate ld) {
        return ld.toString();
    }

}
