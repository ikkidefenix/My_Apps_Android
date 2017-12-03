package com.project.angel.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by AngelA on 02-Dec-17.
 */

public class Pantallazo extends AppCompatActivity {

    private static final String TAG = "notepad";
    private TextView noteTitle;
    private TextView createdAt;
    private TextView noteContent;
    private Database dbhelper;
    private SQLiteDatabase db;
    private String title = "defaultTitle";
    private int id = 0;
    private String content = "defaultContent";
    private String date = "date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantallazo);

        dbhelper = new Database(getApplicationContext());

        noteTitle = (TextView) findViewById(R.id.noteTitle);
        noteContent = (TextView) findViewById(R.id.noteContent);
        createdAt = (TextView) findViewById(R.id.createdAt);

        Intent mIntent = getIntent();

        id = mIntent.getIntExtra("id", 0);

        db = dbhelper.getReadableDatabase();

        Cursor c = dbhelper.getNote(db, id);

        db.close();

        title = c.getString(0).toString();
        content = c.getString(1).toString();
        date = c.getString(2).toString();

        noteTitle.setText(title);
        noteContent.setText(content);
        createdAt.setText(date);
    }
}
