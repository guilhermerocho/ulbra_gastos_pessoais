package com.example.ulbra_gastos_pessoais;

import java.util.Date;

public class Expense {
    private int _id;
    private float amount;
    private String date;
    private String currency;
    private String local;
    private String type_of_expense;

    public Expense(float amount, String date, String currency, String local, String type_of_expense) {
        this.amount = amount;
        this.date = date;
        this.currency = currency;
        this.local = local;
        this.type_of_expense = type_of_expense;
    }

    public Expense(int _id) {
        this._id = _id;
    }

    public Expense(int _id, float amount, String date, String currency, String local, String type_of_expense) {
        this._id = _id;
        this.amount = amount;
        this.date = date;
        this.currency = currency;
        this.local = local;
        this.type_of_expense = type_of_expense;
    }

    //Getters
    public int get_id() {
        return _id;
    }
    public float getAmount() {
        return amount;
    }
    public String getDate() {
        return date;
    }
    public String getCurrency() {
        return currency;
    }
    public String getLocal() {
        return local;
    }
    public String getType_of_expense() {
        return type_of_expense;
    }

    //Setters
    public void set_id(int _id) {
        this._id = _id;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public void setType_of_expense(String type_of_expense) {
        this.type_of_expense = type_of_expense;
    }
}
