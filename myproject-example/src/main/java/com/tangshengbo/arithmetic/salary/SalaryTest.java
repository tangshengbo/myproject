package com.tangshengbo.arithmetic.salary;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Tangshengbo on 2018/10/18
 */
public class SalaryTest {

    private static Random random = new Random();

    private String filePath = "/salary.txt";

    public static int build(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static String buildName() {
        // ASCII 码，A = 65;Z=90
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            int c = random.nextInt(90 - 65 + 1) + 65;
            sb.append((char) c);
        }
        return sb.toString();
    }

    public static Salary[] mockData(int total) {
        Salary[] salaries = new Salary[total];
        for (int i = 0; i < salaries.length; i++) {
            Salary salary = new Salary();
            salary.setName(buildName());
            salary.setBaseSalary(build(50000, 100_0000));
            salary.setBonus(build(0, 10_0000));
            salaries[i] = salary;
        }
        return salaries;
    }

    @Test
    public void main() throws IOException {
        Salary[] salarys = SalaryTest.mockData(1000 * 10000);
        writeFile(salarys);

        long start = System.currentTimeMillis();
        System.out.println("读取并解析...");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));
        String line = null;
        Map<String, SalaryGroup> groups = new HashMap<>();
        while ((line = reader.readLine()) != null) {
            Salary salary = Salary.parse(line);
            String namePrefix = salary.getName().substring(0, 2);
            if (!groups.containsKey(namePrefix)) {
                groups.put(namePrefix, new SalaryGroup(namePrefix));
            }
            SalaryGroup salaryGroup = groups.get(namePrefix);
            salaryGroup.getSalarys().add(salary);
            salaryGroup.sum(salary.yearlySalary());
        }
        reader.close();
        System.out.println("解析结束，耗时 " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        System.out.println("排序中...");
        ArrayList<SalaryGroup> groupList = new ArrayList<>(groups.values());
        Collections.sort(groupList, (g1, g2) -> {
            Long yearlySalaryTotal1 = g1.getYearlySalaryTotal();
            Long yearlySalaryTotal2 = g2.getYearlySalaryTotal();
            if (yearlySalaryTotal1 > yearlySalaryTotal2) return -1;
//
            if (yearlySalaryTotal1 == yearlySalaryTotal2) return 0;
            return 1;
        });

        for (int i = 0; i < 10; i++) {
            SalaryGroup group = groupList.get(i);
            System.out.println(group.getName() + " , " + group.getYearlySalaryTotal() + " , " + group.getSalarys().size());
        }
        System.out.println("排序结束，耗时 " + (System.currentTimeMillis() - start));
    }

    private void writeFile(Salary[] salarys) {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));) {
            for (Salary salary : salarys) {
                bufferedWriter.write(salary.getName() + "\t"
                        + salary.getBaseSalary() + "\t"
                        + salary.getBonus()
                );
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
