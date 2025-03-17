package com.esteban.ms.common.exception;

import com.esteban.ms.common.dto.Error;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class MSException extends Exception implements Serializable {

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
        super(errorCode.getMessage());
        this.location = location;
        this.errorCode = errorCode;
    }

    public MSException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.location = Location.UNKNOWN;
        this.errorCode = ErrorCode.U001;
    }

    public MSException(String message, Location location, ErrorCode errorCode) {
        super(message);
        this.location = location;
        this.errorCode = errorCode;
    }

    public static MSException from(Error error) {
        return new MSException(
                error.getMessage(),
                Location.valueOf(error.getLocation()),
                ErrorCode.valueOf(error.getCode())
        );
    }

}
