package com.fsc.external.portal.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageImpl<T> implements Page<T> {

    private final List<T> content = new ArrayList<>();
    private final Pageable pageable;
    private final long total;

    public PageImpl(List<T> content, Pageable pageable, long total) {
        this.content.addAll(content);
        this.pageable = pageable;
        this.total = total;
    }

    @Override
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    @Override
    public long getTotalElements() {
        return total;
    }

    @Override
    public int getNumber() {
        return pageable.isPaged() ? pageable.getPageNumber() : 0;
    }

    @Override
    public int getSize() {
        return pageable.isPaged() ? pageable.getPageSize() : content.size();
    }

    @Override
    public int getNumberOfElements() {
        return content.size();
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public boolean hasContent() {
        return !content.isEmpty();
    }

    @Override
    public Sort getSort() {
        return pageable.getSort();
    }

    @Override
    public boolean isFirst() {
        return !hasPrevious();
    }

    @Override
    public boolean isLast() {
        return !hasNext();
    }

    @Override
    public boolean hasNext() {
        return getNumber() + 1 < getTotalPages();
    }

    @Override
    public boolean hasPrevious() {
        return getNumber() > 0;
    }

    @Override
    public Pageable nextPageable() {
        return hasNext() ? pageable.next() : Pageable.unpaged();
    }

    @Override
    public Pageable previousPageable() {
        return hasPrevious() ? pageable.previousOrFirst() : Pageable.unpaged();
    }

    @Override
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return new org.springframework.data.domain.PageImpl<>(getConvertedContent(converter), getPageable(), total);
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    protected <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {

        Assert.notNull(converter, "Function must not be null");

        return this.stream().map(converter::apply).collect(Collectors.toList());
    }

}
