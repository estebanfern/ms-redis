package com.esteban.ms.common.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class MSResponse<T> {

    private OffsetDateTime timestamp;
    private T result;
    private Error error;

    public MSResponse() {
        this.timestamp = OffsetDateTime.now();
    }

    public MSResponse(T result) {
        this();
        this.result = result;
    }

    public MSResponse(Error error) {
        this();
        this.error = error;
    }

    public MSResponse(T result, Error error) {
        this();
        this.result = result;
        this.error = error;
    }

}
