package com.vesko.chatserver.dto.view;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class JsonPage<T> extends PageImpl<T> {
    public JsonPage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public JsonPage(List<T> content) {
        super(content);
    }

    @JsonView(OutputViews.Detailed.class)
    @Override
    public int getTotalPages() {
        return super.getTotalPages();
    }

    @JsonView(OutputViews.Detailed.class)
    @Override
    public int getNumber() {
        return super.getNumber();
    }

    @JsonView(OutputViews.Detailed.class)
    @Override
    public List<T> getContent() {
        return super.getContent();
    }

    @JsonView(OutputViews.Detailed.class)
    @Override
    public int getSize() {
        return super.getSize();
    }
}
