package com.tangshengbo.exception;

import java.io.IOException;

/**
 * Created by TangShengBo on 2017-10-26.
 */
public class Base {

    public void func() throws IOException {
        throw new IOException("Base IOException");
    }
}
