package com.worka.worka.exception.comm;

import com.worka.worka.exception.WorkaException;

public class NotFoundException extends WorkaException {
    private static final String MESSAGE = "일치하는 데이터가 존재하지 않습니다.";

    public NotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
