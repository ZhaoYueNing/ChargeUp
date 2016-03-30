package com.zhao.ChargeUp.unit;

import java.util.Date;

/**
 * Created by Zhao on 2016/3/27.
 * 收支记录
 */
public class Record  {
    //收入
    public final static int INCOME = 1;
    //支出
    public final static int EXPEND = 0;
    //对该条收支记录的备注
    private String note;
    //支出类型 收入或支出
    private int RecordType;
    //金额
    private double sum;
    //记录时间
    private Date date;
    /**
     *
     * @param note 记录备注
     * @param recordType 收支类型
     * @param sum 金额
     * @param date 日期
     */
    public Record(String note, int recordType, double sum,Date date) {
        this.note = note;
        RecordType = recordType;
        this.sum = sum;
        this.date = date;
    }

    public String getNote() {
        return note;
    }
    public int getRecordType() {
        return RecordType;
    }
    public double getSum() {
        return sum;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public void setRecordType(int recordType) {
        RecordType = recordType;
    }
    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
