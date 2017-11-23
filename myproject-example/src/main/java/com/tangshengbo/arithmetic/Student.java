package com.tangshengbo.arithmetic;

/**
 * Created by Tangshengbo on 2017/11/23.
 */
public class Student {

    private String name;
    private String course;
    private double score;

    public Student() {
    }

    public Student(String name, String course, double score) {
        this.name = name;
        this.course = course;
        this.score = score;
    }

    public double getScore() {
        return this.score;
    }

    //为对象的所有成员重新赋值
    public void reInit(String n, String c, double s) {
        this.name = n;
        this.course = c;
        this.score = s;
    }

    //计算学生总成绩
    public double totalScore(String[] names, String[] courses, double[] scores) {
        int nLen = (null == names) ? 0 : names.length;
        int cLen = (null == courses) ? 0 : courses.length;
        int sLen = (null == scores) ? 0 : scores.length;

        double total = 0;
        Student s = new Student();
        if (nLen == cLen && cLen == sLen) {
            for (int i = 0; i < nLen; i++) {
                //在循环体内不断的复用对象引用s而不再实例化新的学生对象
                s.reInit(names[i], courses[i], scores[i]);
//                Student s = new Student(names[i], courses[i], scores[i]); //1
                total += s.getScore();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        long ts = System.currentTimeMillis();
        Student student = new Student();
        int size = 100000000;
        String[] names = new String[size];
        for (int i = 0; i < names.length; i++) {
            names[i] = "TT";
        }
        String[] courses = new String[size];
        for (int i = 0; i < courses.length; i++) {
            courses[i] = "SHU";
        }
        double[] scores = new double[size];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = i;
        }
        double result = student.totalScore(names, courses, scores);
        System.out.println(result);
        long te = System.currentTimeMillis();
        System.out.println(String.format("耗时: %s ms", te - ts));
    }
}
