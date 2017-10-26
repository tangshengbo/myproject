package com.tangshengbo.exception;

import java.rmi.AccessException;

/**
 * Created by TangShengBo on 2017-10-26.
 */
public class Sub extends Base {

    @Override
    public void func() throws AccessException {
        throw new AccessException("Sub AccessException");
    }
}
