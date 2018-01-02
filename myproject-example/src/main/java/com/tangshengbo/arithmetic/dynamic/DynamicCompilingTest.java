package com.tangshengbo.arithmetic.dynamic;

import org.apache.commons.lang.exception.ExceptionUtils;

import javax.script.*;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Tangshengbo on 2018/1/2.
 */
public class DynamicCompilingTest {

    public static void main(String[] args) {
        //获取一个javaScript 引擎
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Bindings bindings = engine.createBindings();
        bindings.put("factor", 1);
        //建立上下文变量
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int first = scanner.nextInt();
            int sec = scanner.nextInt();
            System.out.println("输入参数是：" + first + "," + sec);
            //执行js代码
            try {
                engine.eval(new InputStreamReader(DynamicCompilingTest.class.getResourceAsStream("/model.js")));
                if (engine instanceof Invocable) {
                    Invocable invocable = (Invocable) engine;
                    Double result = (Double) invocable.invokeFunction("formula", first, sec);
                    System.out.println("计算结果：" + result.intValue());
                }
            } catch (Exception e) {
                System.out.println(ExceptionUtils.getStackTrace(e));
            }
        }
    }
}
