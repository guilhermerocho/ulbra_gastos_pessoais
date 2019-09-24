package com.example.ulbra_gastos_pessoais;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBController {
    private SQLiteDatabase sqlite;
    private DBHelper db;

    public DBController(Context context){
        db = new DBHelper(context);
    }

    public String insertExpense(Expense e){
        ContentValues values;
        long result;
        sqlite = db.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.AMOUNT, e.getAmount());
        values.put(DBHelper.DATE, e.getDate());
        values.put(DBHelper.CURRENCY, e.getCurrency());
        values.put(DBHelper.LOCAL, e.getLocal());
        values.put(DBHelper.TYPE_OF_EXPENSE, e.getType_of_expense());
        result = sqlite.insert(DBHelper.TABLE, null, values);
        sqlite.close();
        if(result == -1)
            return "Error in insertion";
        else
            return "Insert Successful";
    }


    public Cursor listExpenses(){
        Cursor cursor;
        String[] fields = {DBHelper.ID,
                           DBHelper.AMOUNT,
                           DBHelper.DATE,
                           DBHelper.CURRENCY,
                           DBHelper.LOCAL,
                           DBHelper.TYPE_OF_EXPENSE};

        sqlite = db.getReadableDatabase();
        cursor = sqlite.query(DBHelper.TABLE, fields, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        sqlite.close();
        return cursor;
    }

    public void deleteExpense(int id){
        String where = DBHelper.ID + "=" + id;
        sqlite = db.getReadableDatabase();
        sqlite.delete(DBHelper.TABLE,where,null);
        sqlite.close();
    }

    public Cursor searchByID(int id){
        Cursor cursor;
        String[] fields = {DBHelper.ID, DBHelper.AMOUNT, DBHelper.DATE, DBHelper.CURRENCY, DBHelper.LOCAL, DBHelper.TYPE_OF_EXPENSE};
        String where = DBHelper.ID + "=" +id;
        sqlite = db.getReadableDatabase();
        cursor = sqlite.query(DBHelper.TABLE, fields, where,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        sqlite.close();
        return cursor;
    }

    public String editExpense(int id, String amount, String date, String currency, String local, String type_of_expense){
        ContentValues values;
        String where;
        long result;
        sqlite = db.getWritableDatabase();
        where = DBHelper.ID + "=" +id;
        values = new ContentValues();
        values.put(DBHelper.AMOUNT, amount);
        values.put(DBHelper.DATE, date);
        values.put(DBHelper.CURRENCY, currency);
        values.put(DBHelper.LOCAL, local);
        values.put(DBHelper.TYPE_OF_EXPENSE, type_of_expense);

        result = sqlite.update(DBHelper.TABLE, values, where,null);
        sqlite.close();
        if(result == -1)
            return "Error in update";
        else
            return "Update successful";
    }
}
