package com.tangshengbo.collection;

import java.io.File;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Tangshengbo
 *
 * @author Tangshengbo
 * @date 2020/3/11
 */
public class BloomFilterTest {

    public static void main(String[] args) {
//        BloomFilter<CharSequence> charSequenceBloomFilter =
//                BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 100000000, 0.0001);

//        charSequenceBloomFilter.put("tang");
//        charSequenceBloomFilter.put("12");
//        charSequenceBloomFilter.put("bo");
//        System.out.println(charSequenceBloomFilter.mightContain("bo"));

//        eachFile(new File("E://tools/"));
//        List<Person> personList = new ArrayList<>();
//        int count = 0;
//        while (true) {
//            personList.add(new Person("Name", 23423));
//            System.out.println("创建了第：" + count+ "个对象");
//            count++;
//        }

        int i = 1;

        Integer y = null;

//        System.out.println(Integer.valueOf(i).equals(y));
//
//// 获取今年的天数
//        int daysOfThisYear = LocalDate.of(2018, 1, 1).lengthOfYear();
//        System.out.println(daysOfThisYear);
//        String str = "123456789.SZ";
//        System.out.println(str.substring(0, str.indexOf(".")));
//
//
//        System.out.println(ClassLayout.parseInstance(new Person("tang", 10)).toPrintable());

        float f1 = 6.6f;
        float f2 = 1.3f;
        System.out.println(f1 + f2);
        System.out.println(BigDecimal.valueOf(6.6).add(BigDecimal.valueOf(1.3)));
    }

    private static void eachFile(File file) {
        if (file == null && !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (Objects.nonNull(files)) {
                for (File f : files) {
                    eachFile(f);
                }
            }
        } else if (file.isFile() && file.getName().endsWith(".exe")) {
            System.out.println(file.getAbsolutePath() + "\t" + file.length() / (1024) + "MB");
        }
    }
}
