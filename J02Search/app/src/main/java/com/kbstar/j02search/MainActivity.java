package com.kbstar.j02search;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
    Android DB : SQLite 임베디드 되어있다.  Embedded SQL ==> SQLite
    SQLite : 파일로 만들어진 하위 수준의 데이터베이스
        데이터 복사, 이동, 삭제
        데이터 조회 속도 빠르다.
        표준 SQL 지원 : INSERT, UPDATE, SELECT, DELETE
                CREATE, ALTER,

        create table test (
            name    char(20),
            age     int(3)
        );
        ALTER TALBE table add id char(20) after name;
 */

public class MainActivity extends AppCompatActivity {

    boolean isDebugMode = true;

    EditText inputDB;
    EditText inputTable;
    EditText inputName;
    EditText inputAge;
    EditText inputMobile;
    Button btnDB;
    Button btnTable;

    TextView debugText;

    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    String table;
    String getName;
    Integer getAge;
    String getMobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputDB = findViewById(R.id.inputDB);
        inputTable = findViewById(R.id.inputTable);
        inputName = findViewById(R.id.inputName);
        inputAge = findViewById(R.id.inputAge);
        inputMobile = findViewById(R.id.inputMobile);
        btnDB = findViewById(R.id.btnDB);
        btnTable = findViewById(R.id.btnTable);
        debugText = findViewById(R.id.debugText);

        btnDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDB(inputDB.getText().toString());
            }
        });

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table = inputTable.getText().toString();
                getName = inputName.getText().toString();
                getAge = Integer.parseInt(inputAge.getText().toString());
                getMobile = inputMobile.getText().toString();

                createTable();

            }
        });

    }


    public void printDebug(String msg) {
        debugText.append(msg + "\n");
    }

    public void createDB(String dbName) {
        //db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        //printDebug("DB Created ... : " + dbName );

        Log.d("MAIN", "======================== Call Helper()");

        if(isDebugMode)
            System.out.flush();
        dbHelper = new DatabaseHelper(this, dbName, 1);


        Log.d("MAIN", "======================== End of Call Helper()");
        db = dbHelper.getWritableDatabase();

        //dbHelper = new DatabaseHelper(this);
        // kbstar.db
        printDebug("DB Created by Helper : " + dbName);
    }
    public void createTable() {
        if(db == null) {
            printDebug("NO DATABASE SELECTED!!!");
        } else {
            String sql = "";
            sql = " CREATE TABLE if not exists " + table + "("
                    + "idx integer , "
                    + "name text, "
                    + "age integer, "
                    + "mobile text, "
                    + "primary key(idx)"
                    + ")";
            Log.d("D","---------------------- SQL : "+sql);
            db.execSQL(sql);

            printDebug("TABLE CREATED !!!! : " + table);

            insert();

            printDebug("Search..");
            sql = "select * from  " + table + " order by idx desc";
            Cursor cursor = db.rawQuery(sql, null);
            int dataCount = cursor.getCount();
            printDebug("data count = " + dataCount);

            for(int i = 0 ; i<dataCount; i++) {
                cursor.moveToNext();

                int idx = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String mobile = cursor.getString(3);

                String data = "";
                data = idx + "\t" + name + "\t" + age + "\t" + mobile;
                printDebug(data);
            }

            cursor.close();

        }
    }

    public void insert()
    {
        String sql = "INSERT INTO " + table
                + " (name, age, mobile) VALUES ('" + getName + "', '" + getAge + "', '" + getMobile + "') ";

        if(db == null)
        {
            printDebug("선택된 DB 없음.");
            return;
        }

        if(table == null)
        {
            printDebug("테이블 없음 : " + table);
            return;
        }

        db.execSQL(sql);
        printDebug("Record 추가 됨. ");

    }
}