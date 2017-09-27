package com.tangshengbo.io;

/**
 * Created by Tangshengbo on 2017/9/26.
 */
public class MyAutoClosable implements AutoCloseable {

    private void doIt() {
        System.out.println("MyAutoClosable doing it!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("MyAutoClosable closed!");
    }

    public static void main(String[] args) {
        try(MyAutoClosable myAutoClosable = new MyAutoClosable()) {
            myAutoClosable.doIt();
            double result = 10 / 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
