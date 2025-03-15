package com.esteban.ms.common.exception;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class MSException extends Exception {

    private final Location location;
    private final ErrorCode errorCode;

    public MSException(Location location, ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.location = location;
        this.errorCode = errorCode;
    }

    public MSException(String message, Location location, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.location = location;
        this.errorCode = errorCode;
    }

    public MSException(Location location, ErrorCode errorCode) {
        this.location = location;
        this.errorCode = errorCode;
    }

    public MSException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.location = Location.UNKNOWN;
        this.errorCode = ErrorCode.U001;
    }

}
