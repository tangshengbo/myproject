package com.tangshengbo.optimization.multiextend;

/**
 * Created by Tangshengbo on 2017/10/16.
 */
public class Son extends FatherImpl implements Mother {

    @Override
    public int strong() {
        return super.strong() + 1;
    }

    @Override
    public int kind() {
        return new  MotherSpecial().kind();
    }

    private class MotherSpecial extends MotherImpl {

        @Override
        public int kind() {
            return super.kind() - 2;
        }
    }
}

