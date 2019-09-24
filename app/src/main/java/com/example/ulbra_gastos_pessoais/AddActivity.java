package com.example.ulbra_gastos_pessoais;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        if(intent.hasExtra("ID")){
            String id = intent.getStringExtra("ID");

            DBController crud = new DBController(getBaseContext());
            cursor = crud.searchByID(Integer.parseInt(id));

            EditText fieldAmount, fieldDate, fieldCurrency, fieldLocal, fieldTypeOfExpense;
            fieldAmount = (EditText) findViewById(R.id.amount);
            fieldDate = (EditText) findViewById(R.id.date);
            fieldCurrency = (EditText) findViewById(R.id.currency);
            fieldLocal = (EditText) findViewById(R.id.local);
            fieldTypeOfExpense = (EditText) findViewById(R.id.type_of_expense);

            fieldAmount.setText(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.AMOUNT))));
            fieldDate.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.DATE)));
            fieldCurrency.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.CURRENCY)));
            fieldLocal.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LOCAL)));
            fieldTypeOfExpense.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TYPE_OF_EXPENSE)));

            Button btnSalvar =(Button) findViewById(R.id.btnSave);
            btnSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update();
                }
            });
        }


        Button btnSalvar =(Button) findViewById(R.id.btnSave);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                if(intent.hasExtra("ID")){
                    update();
                } else {
                    save();
                }
            }
        });

        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AddActivity.this,MainActivity.class);
                startActivity(it);
            }
        });
    }

    public void update(){
        String result;
        DBController crud = new DBController(getBaseContext());
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        cursor = crud.searchByID(Integer.parseInt(id));

        EditText fieldAmount, fieldDate, fieldCurrency, fieldLocal, fieldTypeOfExpense;
        fieldAmount = (EditText) findViewById(R.id.amount);
        fieldDate = (EditText) findViewById(R.id.date);
        fieldCurrency = (EditText) findViewById(R.id.currency);
        fieldLocal = (EditText) findViewById(R.id.local);
        fieldTypeOfExpense = (EditText) findViewById(R.id.type_of_expense);

        fieldAmount = (EditText) findViewById(R.id.amount);
        fieldDate = (EditText) findViewById(R.id.date);
        fieldCurrency = (EditText) findViewById(R.id.currency);
        fieldLocal = (EditText) findViewById(R.id.local);
        fieldTypeOfExpense = (EditText) findViewById(R.id.type_of_expense);

        if(fieldAmount.getText().toString().trim().length() != 0) {
            if (fieldDate.getText().toString().length() != 0) {
                if(fieldCurrency.getText().toString().length() != 0) {
                    if (fieldLocal.getText().toString().length() != 0) {
                        if (fieldTypeOfExpense.getText().toString().length() != 0) {


                            result = crud.editExpense(
                                    Integer.parseInt(id),
                                    fieldAmount.getText().toString(),
                                    fieldDate.getText().toString(),
                                    fieldCurrency.getText().toString(),
                                    fieldLocal.getText().toString(),
                                    fieldTypeOfExpense.getText().toString());

                            Intent it = new Intent(AddActivity.this,ViewActivity.class);
                            startActivity(it);

                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Type an expense type", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Type a local", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Type a currency", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Enter a date", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Enter an amount", Toast.LENGTH_SHORT).show();
        }
    }

    public void save(){
        String result;
        DBController crud = new DBController(getBaseContext());
        EditText fieldAmount, fieldDate, fieldCurrency, fieldLocal, fieldTypeOfExpense;
        fieldAmount = (EditText) findViewById(R.id.amount);
        fieldDate = (EditText) findViewById(R.id.date);
        fieldCurrency = (EditText) findViewById(R.id.currency);
        fieldLocal = (EditText) findViewById(R.id.local);
        fieldTypeOfExpense = (EditText) findViewById(R.id.type_of_expense);


        if(fieldAmount.getText().toString().trim().length() != 0) {
            if (fieldDate.getText().toString().length() != 0) {
                if(fieldCurrency.getText().toString().length() != 0) {
                    if (fieldLocal.getText().toString().length() != 0) {
                        if (fieldTypeOfExpense.getText().toString().length() != 0) {

                            Expense e = new Expense(Float.parseFloat(fieldAmount.getText().toString()),
                                    fieldDate.getText().toString(),
                                    fieldCurrency.getText().toString(),
                                    fieldLocal.getText().toString(),
                                    fieldTypeOfExpense.getText().toString());

                            fieldAmount.setText("");
                            fieldDate.setText("");
                            fieldCurrency.setText("");
                            fieldLocal.setText("");
                            fieldTypeOfExpense.setText("");
                            result = crud.insertExpense(e);

                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Type an expense type", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Type a local", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Type a currency", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Enter a date", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Enter an amount", Toast.LENGTH_SHORT).show();
        }
    }
}
