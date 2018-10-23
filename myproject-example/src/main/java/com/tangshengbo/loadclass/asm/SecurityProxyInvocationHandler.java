package com.tangshengbo.loadclass.asm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Tangshengbo on 2018/10/23
 */
public class SecurityProxyInvocationHandler implements InvocationHandler {

    private Object object;

    public SecurityProxyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (proxy instanceof Account && method.getName().equals("operation")) {
            SecurityChecker.checkSecurity();
        }
        return method.invoke(object, args);
    }

    public static void main(String[] args) {
        Account account = (Account) Proxy.newProxyInstance(Account.class.getClassLoader(), new Class[]{Account.class},
                new SecurityProxyInvocationHandler(new AccountImpl()));
        account.operation();
    }
}
