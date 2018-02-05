package com.tangshengbo.tutorial.txt;

/**
 * Created by Tangshengbo on 2018/2/5.
 */
public class TxtImportException extends RuntimeException {

    public TxtImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public TxtImportException(Throwable cause) {
        super(cause);
    }

    public TxtImportException(String message) {
        super(message);
    }
}
