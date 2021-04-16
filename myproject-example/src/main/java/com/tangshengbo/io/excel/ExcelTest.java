package com.tangshengbo.io.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileInputStream;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by admin on 2019/7/29
 */
public class ExcelTest {

    public static void main(String[] args) {
            ZoneId defaultZone = ZoneId.systemDefault();
            System.out.println(defaultZone);


    //
    ////        parseExcelByDB();
    //        Map<String, String> newTableEntity = new LinkedHashMap<>();
    //        try {
    //            FileInputStream fis = new FileInputStream("C:\\Users\\admin\\Desktop\\中资美元债\\【中资美元债-海外评级部分】主体评级处理逻辑.xlsx");
    //            ExcelReader reader = new ExcelReader(fis, ExcelTypeEnum.XLSX, null,
    //                    new AnalysisEventListener<Rate>() {
    //                        @Override
    //                        public void invoke(Rate object, AnalysisContext context) {
    //
    ////                            System.out.println(object);
    ////                            String[] split = object.getDetail().split("\r\n");
    ////                            System.out.println(split);
    //
    //
    //
    //
    //                            newTableEntity.put(object.getCompany(), object.getDetail());
    //
    //                        }
    //
    //                        @Override
    //                        public void doAfterAllAnalysed(AnalysisContext context) {
    //                            System.out.println(context.getCurrentSheet());
    //                        }
    //                    });
    //
    //            reader.read(new Sheet(1, 1, Rate.class));
    //
    //
    ////            System.out.println(newTableEntity);
    //            List<String> keyword = Arrays.asList("级有限公司", 管理服务", "公司");
    //            PrintStream out = new PrintStream("e:/test.txt");
    //            System.setOut(out);
    //            newTableEntity.forEach((k, v) -> {
    //                boolean contains = false;
    //                boolean never = true;
    //                if (StringUtils.isBlank(v)) {
    //                    System.out.println(k);
    //                    return;
    //                }
    //                String[] split = v.split("\r\n");
    //                for (String s : split) {
    //                    for (String key : keyword) {
    //                        if (s.contains(key)) {
    //                            contains = true;
    //                            never = false;
    //                            break;
    //                        }
    //                    }
    //                    if (contains) {
    //                        System.out.println(k  + " \t" + s);
    //                    }
    //                    contains = false;
    //                }
    //
    //                if (never) {
    //                    System.out.println(k);
    //                }
    //
    //
    //            });

    //
    //
    //
    //            String value = newTableEntity.get("有限公司");
    //            String[] split = value.split("\r\n");
    //            System.out.println(split.length);
    //            for (String s : split) {
    //
    //                System.out.println(s);
    //
    //
    //            }

    //            System.out.println();
    //
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    }

    private static void parseExcelByDB() {
        Map<String, TableEntity> newTableEntity = new LinkedHashMap<>();

        try {
            FileInputStream fis = new FileInputStream("E:/数据字段.xlsx");
            ExcelReader reader = new ExcelReader(fis, ExcelTypeEnum.XLSX, null,
                    new AnalysisEventListener<TableEntity>() {
                        @Override
                        public void invoke(TableEntity object, AnalysisContext context) {
                            newTableEntity.put(object.getTableName() + "-" + object.getColumnName(), object);

                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext context) {
                            System.out.println(context.getCurrentSheet());
                        }
                    });

            for (int i = 2; i <= 5; i++) {
                reader.read(new Sheet(i, 1, TableEntity.class));
            }


            List<String> tableNameList = Collections.singletonList("online_quote");

            Pattern compile = Pattern.compile(".*[\\u4e00-\\u9faf].*");

            Map<String, Map<String, TableEntity>> columnInfo = DatabaseUtil.getColumnInfo(tableNameList);
            columnInfo.forEach((tableName, tableEntityMap) -> {
                        System.out.println("-- " + tableName + "-------------------------------------------------start");
                        tableEntityMap.forEach((k, v) -> {
                            TableEntity tableEntity = newTableEntity.get(k);

                            if (Objects.nonNull(tableEntity)
                                    && !compile.matcher(v.getColumnComment()).matches()) {

                                String sql = String.format("ALTER TABLE %s MODIFY COLUMN %s %s %s %s %s COMMENT '%s' ;",
                                        tableName, v.getColumnName(), v.getColumnType(),
                                        v.getNullStr(), v.getExtraStr(), v.getDefaultValueStr(),
                                        tableEntity.getColumnComment());
                                System.out.println(sql);

                            }

                        });
                        System.out.println("-- " + tableName + "-------------------------------------------------end");
                    }
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(1 << 30);
    }

}
