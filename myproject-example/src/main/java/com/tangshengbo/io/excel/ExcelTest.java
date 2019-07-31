package com.tangshengbo.io.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileInputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by admin on 2019/7/29
 */
public class ExcelTest {

    public static void main(String[] args) {
        Map<String, TableEntity> newTableEntity = new LinkedHashMap<>();

        try {
            FileInputStream fis = new FileInputStream("E:/xxx.xlsx");
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


            List<String> tableNameList = Arrays.asList("xxxTable", "xxx");

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
    }

}
