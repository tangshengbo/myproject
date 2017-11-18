package com.tangshengbo.exception;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by TangShengBo on 2017-11-18.
 */
public class CustomException extends Exception {

    private List<Throwable>  exceptions = Lists.newArrayList();

    public CustomException(List<? extends Throwable> exceptions) {
        this.exceptions.addAll(exceptions);
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public List<Throwable> getExceptions() {
        return exceptions;
    }
}
