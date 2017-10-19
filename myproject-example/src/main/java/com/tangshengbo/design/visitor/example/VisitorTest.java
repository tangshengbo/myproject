package com.tangshengbo.design.visitor.example;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public class VisitorTest {

    public static void main(String[] args) {
        Action successAction = new SuccessAction("成功");
        Action failAction = new FailAction("失败");
        Action loveAction = new LoveAction("恋爱");
        Action marriageAction = new MarriageAction("结婚");

        Man man = new Man("男人");
        Woman woman = new Woman("女人");

        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(man);
        objectStructure.attach(woman);
        objectStructure.display(successAction);
        objectStructure.display(failAction);
        objectStructure.display(loveAction);
        objectStructure.display(marriageAction);
        System.out.println(10 + (10 << 2));
    }
}
