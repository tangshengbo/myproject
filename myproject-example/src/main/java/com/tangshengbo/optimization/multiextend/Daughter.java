package com.tangshengbo.optimization.multiextend;

/**
 * Created by Tangshengbo on 2017/10/16.
 */
public class Daughter extends MotherImpl implements Father {

    @Override
    public int kind() {
        return super.kind() + 2;
    }

    @Override
    public int strong() {
        return new FatherImpl() {
            @Override
            public int strong() {
                return super.strong() - 2;
            }
        }.strong();
    }
}
