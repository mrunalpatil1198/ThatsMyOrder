package com.example.raunak.samrestaurant;

public class TableInfo {
    static int table_no;
    static String name;
    static String phone_no;

    public static int getTable_no() {
        return table_no;
    }

    public static void setTable_no(int table_no) {
        TableInfo.table_no = table_no;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        TableInfo.name = name;
    }

    public static String getPhone_no() {
        return phone_no;
    }

    public static void setPhone_no(String phone_no) {
        TableInfo.phone_no = phone_no;
    }
}
