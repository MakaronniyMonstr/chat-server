package com.vesko.chatserver.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.view.InputViews;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.entity.BaseEntity;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.GenericTypeResolver;

import javax.validation.constraints.Null;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

@NoArgsConstructor
public class BaseDTO<T extends BaseEntity> {
    @JsonView({OutputViews.Admin.class})
    @Null(groups = {InputViews.General.class})
    private Long id;

    @JsonView({OutputViews.Admin.class})
    @Null(groups = {InputViews.General.class})
    private Long version;

    @JsonView({OutputViews.Detailed.class})
    @Null(groups = {InputViews.General.class})
    private LocalDate createdAt;

    @JsonView({OutputViews.Detailed.class})
    @Null(groups = {InputViews.General.class})
    private LocalDate modifiedAt;

    public <E extends BaseEntity> BaseDTO(E entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public T toEntity() {
        T entity;

        try {
            Class<T> entityClass = (Class<T>) GenericTypeResolver.resolveTypeArguments(getClass(), BaseDTO.class)[0];
            entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(this, entity);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException();
        }

        return entity;
    }
}
