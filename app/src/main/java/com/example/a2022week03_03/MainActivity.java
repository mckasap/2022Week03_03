package com.example.a2022week03_03;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btn=(Button)findViewById(R.id.button);


        SQLiteDatabase db= openOrCreateDatabase("MyDB", MODE_PRIVATE,null);


        db.execSQL("create table if not exists MyTable( id Integer PRIMARY KEY AUTOINCREMENT not null, Ad varchar  not null, Soyad char(50) not null, Age int(3) not null )");

        db.execSQL("insert into MyTable(Ad,Soyad, age) values('CEM ','HODJA',46)");

        db.close();


        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                SQLiteDatabase db= openOrCreateDatabase("MyDB", MODE_PRIVATE,null);

                Cursor c=  db.rawQuery("Select * from MyTable",null);
                  StringBuilder sb= new StringBuilder("");

                  if(c!=null){

                      c.moveToFirst();
                      do{
                          sb.append( c.getString(c.getColumnIndex("Ad")));
                          sb.append( " ");
                          sb.append( c.getString(c.getColumnIndex("Soyad")));
                          sb.append( c.getInt(c.getColumnIndex("Age")));
                          sb.append( "\n");

                      }while(c.moveToNext());


                Toast.makeText(MainActivity.this,sb.toString(),Toast.LENGTH_LONG).show();

                  }

                db.close();
            }
        });
    }
}