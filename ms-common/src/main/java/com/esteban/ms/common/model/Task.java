package com.esteban.ms.common.model;

import com.esteban.ms.common.dto.Error;
import lombok.Data;

@Data
public class Task<T, W> {
    private String id;
    private T data;
    private W result;
    private Error error;
}
