package com.kdk.sberuniversity.api.pagination;

import java.util.List;

public final class PageImpl<T> implements Page<T> {

    private final long totalSize;
    private final int size;
    private final long offset;
    private final boolean hasNext;
    private final List<T> content;

    public PageImpl(List<T> content, Pageable pageable, long total) {
        this.totalSize = total;
        this.size = pageable.getSize();
        this.offset = pageable.getOffset();
        this.hasNext = totalSize > offset + size;
        this.content = content;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public int getSize() {
        return size;
    }

    public long getOffset() {
        return offset;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public List<T> getContent() {
        return content;
    }

}
