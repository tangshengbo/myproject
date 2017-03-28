package com.tangshengbo.refactor.skill;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class RefactorVariable {

    public static void main(String[] args) {
        System.out.println(getDistanceTravelled(100));
        System.out.println("=====================================");
        System.out.println(getPrice());
    }


    /**
     * split Temporary Variable （分解临时变量）
     * 针对每次赋值，创造一个独立，对应的临时变量
     *
     * @param time
     * @return
     */
    private static double getDistanceTravelled(int time) {
        double result;
        final double primaryAcc = 112 / 123;
        int primaryTime = Math.min(time, 10);
        result = 0.5 * primaryAcc * primaryTime;
        int secondaryTime = time - 10;
        if (secondaryTime > 0) {
            double primaryVel = primaryAcc * 100;
            final double acc = primaryVel / 10;
            result = acc + primaryAcc;
        }
        return result;
    }

    /**
     * Replace Temp with Query(以查询取代临时变量)
     * 将这个表达式提炼到一个独立的函数中，将这个临时变量的所有引用点替换为对新函数的调用
     * ，此后新函数就可被其他函数使用
     * @return
     */
    private static double getPrice() {
        return getBasePrice() * getDiscountFactor();
    }

    private static int getBasePrice() {
        return 100 * 100;
    }

    /**
     * 将所有对改变量的引用动作 替换为对它赋值的那个表达式自身
     * @return
     */
    public static double getDiscountFactor() {
        return getBasePrice() > 1000 ? 0.95 : 0.98;
    }

}
