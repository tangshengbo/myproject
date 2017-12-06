package com.tangshengbo.design.decorate.example;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class Test {

    public static void main(String[] args) {
//        Component component = new ConcreteComponent();
//        Decorator decorator = new ConcreteDecorator(component);
//        decorator = new ConcreteBDecorator(decorator);
//        decorator.operation();

        Person person = new Person("唐声波");
        System.out.println("第一种装扮................");
        Costume shoe = new Shoe();
        Costume hat = new Hat();
        Costume sunglasses = new Sunglasses();
        sunglasses.decorate(person);
        hat.decorate(sunglasses);
        shoe.decorate(hat);
        shoe.show();
        System.out.println("第二种装扮................");
        shoe.decorate(person);
        sunglasses.decorate(shoe);
        hat.decorate(sunglasses);
        hat.show();



    }
}
