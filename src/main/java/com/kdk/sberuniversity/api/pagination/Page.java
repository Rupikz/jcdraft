package com.kdk.sberuniversity.api.pagination;

import java.util.List;

public interface Page<T> {

    long getTotalSize();
    long getOffset();
    int getSize();
    boolean hasNext();
    List<T> getContent();

}
