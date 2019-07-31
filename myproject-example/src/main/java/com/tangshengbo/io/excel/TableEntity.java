package com.tangshengbo.io.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

/**
 * Created by admin on 2019/7/29
 */
public class TableEntity extends BaseRowModel {

//    table_name	table_comment	column_name	column_type	column_comment

    @ExcelProperty(value = "table_name", index = 0)
    private String tableName;

    @ExcelProperty(value = "table_comment", index = 1)
    private String tableComment;

    @ExcelProperty(value = "column_name", index = 2)
    private String columnName;

    @ExcelProperty(value = "column_type", index = 3)
    private String columnType;

    @ExcelProperty(value = "column_comment", index = 4)
    private String columnComment;

    private boolean isNull;

    private String precision;

    private String scale;

    private String defaultValue;

    private String extra;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtraStr() {
        if (StringUtils.isBlank(extra)) {
            return "";
        }
        return extra;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getDefaultValueStr() {
        if ("null".equalsIgnoreCase(defaultValue) || StringUtils.isBlank(defaultValue)) {
            return "";
        }

        return " DEFAULT " + defaultValue;

    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getPrecision() {
        return precision;
    }

    public String getScale() {
        return scale;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public boolean isNull() {
        return isNull;
    }

    public String getNullStr() {
        return isNull ? "NULL" : "NOT NULL";
    }

    public String getColumnTypeStr() {
        if ("date".equalsIgnoreCase(columnType) || "datetime".equalsIgnoreCase(columnType) || "timestamp".equalsIgnoreCase(columnType)) {
            return columnType;
        }

        if ("0".equals(scale)) {
            return columnType + " (" + precision + ")";
        }
        return columnType + "(" + precision + "," + scale + ")";
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
