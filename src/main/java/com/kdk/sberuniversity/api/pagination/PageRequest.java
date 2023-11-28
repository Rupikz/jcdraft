package com.kdk.sberuniversity.api.pagination;

public final class PageRequest implements Pageable {

    private final long offset;
    private final int size;

    private PageRequest(long offset, int size) {
        this.offset = offset;
        this.size = size;
    }

    public static PageRequest of(int offset, int size) {
        return new PageRequest(offset, size);
    }

    public long getOffset() {
        return offset;
    }

    public int getSize() {
        return size;
    }

}
