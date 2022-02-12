package com.vesko.chatserver.dto.view;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public class JsonPage<T> extends PageImpl<T> {

    public JsonPage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    @JsonView(OutputViews.Short.class)
    @Override
    public int getTotalPages() {
        return super.getTotalPages();
    }

    @JsonView(OutputViews.Short.class)
    @Override
    public long getTotalElements() {
        return super.getTotalElements();
    }

    @JsonView(OutputViews.Short.class)
    @Override
    public List<T> getContent() {
        return super.getContent();
    }

    @JsonView(OutputViews.Short.class)
    @Override
    public int getNumber() {
        return super.getNumber();
    }

    @JsonView(OutputViews.Short.class)
    @Override
    public int getSize() {
        return super.getSize();
    }

    @JsonView(OutputViews.Short.class)
    @Override
    public boolean isLast() {
        return super.isLast();
    }
}
