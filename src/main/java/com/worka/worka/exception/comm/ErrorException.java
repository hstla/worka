package com.worka.worka.exception.comm;

import com.worka.worka.exception.WorkaException;
import lombok.Getter;

@Getter
public class ErrorException extends WorkaException {

    public ErrorException( String errMsg) {
        super(errMsg);
    }
    @Override
    public int getStatusCode() {
        return 400;
    }
}
