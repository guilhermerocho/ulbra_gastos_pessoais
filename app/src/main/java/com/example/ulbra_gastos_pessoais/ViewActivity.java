package com.example.ulbra_gastos_pessoais;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        final DBController crud = new DBController(getBaseContext());
        cursor = crud.listExpenses();

        if (cursor.getCount() != 0) {
            String[] fieldNames = new String[]{DBHelper.AMOUNT, DBHelper.DATE, DBHelper.CURRENCY, DBHelper.LOCAL,DBHelper.TYPE_OF_EXPENSE, DBHelper.ID};
            int[] idViews = new int[]{R.id.txtAmount, R.id.txtDate, R.id.txtCurrency,R.id.txtLocal,R.id.txtTypeOfExpense, R.id.txtID};
            final SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.list, cursor, fieldNames, idViews, 0);
            final ListView list = (ListView) findViewById(R.id.list);
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            list.setAdapter(adaptador);
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    builder.setTitle("Delete entry")
                            .setMessage("Are you sure you want to delete this entry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String id;
                                    cursor.moveToPosition(position);
                                    id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ID));
                                    crud.deleteExpense(Integer.valueOf(id));
                                    Toast.makeText(getApplicationContext(), "Entry deleted.", Toast.LENGTH_SHORT).show();
                                    Intent it = new Intent(ViewActivity.this,ViewActivity.class);
                                    startActivity(it);
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                    return false;
                }
            });

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    builder.setTitle("Edit entry")
                            .setMessage("Are you sure you want to edit this entry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String id;
                                    cursor.moveToPosition(position);
                                    id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ID));
                                    //crud.editExpense(Integer.valueOf(id));
                                    Intent it = new Intent(ViewActivity.this,AddActivity.class);
                                    it.putExtra("ID", id);
                                    startActivity(it);
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });


             adaptador.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "Nenhuma Pessoa encontrado.", Toast.LENGTH_SHORT).show();
        }

        Button btnVoltar = (Button)findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ViewActivity.this,MainActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
