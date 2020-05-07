package com.mao.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private StudentOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        1.获取帮助类 获得sqlitedatabases对象 帮助增删改查
        helper=new StudentOpenHelper(this);
    }

    public void add(View view){

        SQLiteDatabase db=helper.getWritableDatabase();

        db.execSQL("insert into student (name,score) values(?,?)",new Object[]{"张三", "98"});

        db.close();

        Toast.makeText(this,"添加成功", Toast.LENGTH_LONG).show();
    }

    public void delete(View view){

        SQLiteDatabase db=helper.getWritableDatabase();

        db.execSQL("delete from student where name=?",new Object[]{"张三"});

        db.close();

        Toast.makeText(this,"删除成功",Toast.LENGTH_LONG).show();
    }

    public  void update(View view){

        SQLiteDatabase db=helper.getWritableDatabase();

        db.execSQL("update student set score=? where name=?",new Object[]{"99.99","张三"});

        db.close();

        Toast.makeText(this,"更新成功",Toast.LENGTH_LONG).show();
    }

    public  void find(View view){

        SQLiteDatabase db=helper.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from student",null);

        ArrayList<Student>list=new ArrayList<Student>();
        while (cursor.moveToNext()){

            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            double score=cursor.getDouble(2);

            Student s=new Student(id, name, score);
            list.add(s);
        }

        for(Student s:list){

            System.out.println(s.toString());
        }

        cursor.close();
        db.close();
    }
}
