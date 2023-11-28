package com.kdk.sberuniversity.api.models;

import com.kdk.sberuniversity.api.models.car.Car;
import com.kdk.sberuniversity.api.models.order.Order;
import com.kdk.sberuniversity.api.models.person.Person;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "page", propOrder = {"offset", "size", "hasNext", "content"})
@XmlSeeAlso({Car.class, Person.class, Order.class})
@XmlRootElement(name = "page")
public final class PageResponse<T> {

    private Long offset;
    private Integer size;
    private Boolean hasNext;
    @XmlAnyElement(lax = true)
    @XmlElementWrapper(name = "content")
    private List<T> content;

    public PageResponse(Long offset, Integer size, Boolean hasNext, List<T> content) {
        this.size = size;
        this.offset = offset;
        this.hasNext = hasNext;
        this.content = content;
    }

    public PageResponse() {
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

}
