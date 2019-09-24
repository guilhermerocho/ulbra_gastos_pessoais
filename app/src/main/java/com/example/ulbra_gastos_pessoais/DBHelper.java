package com.example.ulbra_gastos_pessoais;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "ulbra_gastos_pessoais.db";
    public static final String TABLE = "expense";
    public static final String ID = "_id";
    public static final String AMOUNT = "amount";
    public static final String DATE = "date";
    public static final String CURRENCY = "currency";
    public static final String LOCAL = "local";
    public static final String TYPE_OF_EXPENSE = "type_of_expense";

    public static final int VERSION = 5;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE+"("+ID+" integer primary key autoincrement," + AMOUNT +" real not null," + DATE +" text not null unique,"+ CURRENCY +" text not null," + LOCAL + " text not null, "+ TYPE_OF_EXPENSE +" text not null)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
