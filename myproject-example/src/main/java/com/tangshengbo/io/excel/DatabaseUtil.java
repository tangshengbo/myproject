package com.tangshengbo.io.excel;

/**
 * Created by admin on 2019/7/29
 */

import cn.hutool.db.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DatabaseUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/myproject??useUnicode=true&amp;characterEncoding=UTF-8&allowMultiQueries=true&amp;zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            DbUtil.close(rs);
        }
        return tableNames;
    }


    public static Map<String, Map<String, TableEntity>> getColumnInfo(List<String> tableNames) {
        Map<String, Map<String, TableEntity>> dbTableEntity = new LinkedHashMap<>();

        for (String tableName : tableNames) {
            dbTableEntity.put(tableName, getColumnInfo(tableName));
        }

        return dbTableEntity;
    }



    public static Map<String, TableEntity> getColumnInfo(String tableName) {
        Map<String, TableEntity> dbTableEntity = new LinkedHashMap<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        ResultSet rs = null;

        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            rs = pStemt.executeQuery("show full columns from " + tableName);

            //表列数
            int size = rsmd.getColumnCount();
            System.out.println("table:" + tableName + "\t ColumnCount:" + size);
            while (rs.next()) {
                TableEntity tableEntity = new TableEntity();
                tableEntity.setColumnName(rs.getString("Field"));
                tableEntity.setColumnType(rs.getString("Type"));
                tableEntity.setColumnComment(rs.getString("Comment"));
                tableEntity.setDefaultValue(rs.getString("Default"));
                tableEntity.setNull("YES".equalsIgnoreCase(rs.getString("Null")));
                tableEntity.setExtra(rs.getString("Extra"));
                dbTableEntity.put(tableName + "-" + tableEntity.getColumnName(), tableEntity);
            }

        } catch (SQLException e) {
            LOGGER.error("getColumnNames failure", e);
        } finally {
            DbUtil.close(pStemt);
            DbUtil.close(rs);
        }
        return dbTableEntity;
    }

    public static void main(String[] args) {
//        List<String> tableNames = getTableNames();
//        System.out.println("tableNames:" + tableNames);
//        for (String tableName : tableNames) {
//
//        }
//
//        Map<String, TableEntity> columnInfo = getColumnInfo(tableName);
//        System.out.println("Field             Type         Default         Null      Comment");
//        columnInfo.forEach((k, v) -> {
//
//            String rs = String.format("%s     %s      %s      %s     %s", v.getColumnName(), v.getColumnType(), v.getDefaultValue(), v.getNullStr(), v.getColumnComment());
//            System.out.println(rs);
//
//
//        });

//        System.out.println(!ReUtil.contains("^[u4e00-u9fa5]{0,}$", "口口声声"));
        Pattern compile = Pattern.compile(".*[\\u4e00-\\u9faf].*");
        System.out.println(compile.matcher("fsdsdfsdf").matches());



    }
}