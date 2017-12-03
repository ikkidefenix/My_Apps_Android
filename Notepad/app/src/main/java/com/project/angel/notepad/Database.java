package com.project.angel.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by AngelA on 02-Dec-17.
 */

public class Database extends SQLiteOpenHelper {

    private Context ctx;
    private static final int version = 1;
    private static final String DB_NAME = "notesDB";
    private static final String TABLE_NAME = "notes";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "noteTitle";
    private static final String KEY_CONTENT = "noteContent";
    private static final String KEY_DATE = "date";

    //Para crear la tabla
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_TITLE+" TEXT NOT NULL, "+KEY_CONTENT+" TEXT NOT NULL, "+KEY_DATE+" TEXT);";

    public Database(Context context) {
        super(context, DB_NAME, null, version);
        this.ctx = context;
    }

    //Creando la tabla en la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    //Actualizar la tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);

    }

    //Funcion para agregar nota a la base de datos
    public void addNote(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("noteTitle", title);
        cv.put("noteContent", content);
        cv.put("date", new Date().toString());

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    //Llamar a las notas
    public Cursor getNotes(SQLiteDatabase db) {
        Cursor c = db.query(TABLE_NAME, new String[] {KEY_TITLE, KEY_CONTENT}, null, null, null, null, "id DESC");
        c.moveToFirst();
        return c;
    }

    public Cursor getNotes2(SQLiteDatabase db) {
        Cursor c = db.query(TABLE_NAME, new String[] {KEY_ID, KEY_TITLE}, null, null, null, null, "id DESC");
        c.moveToFirst();
        return c;
    }

    public Cursor getNote(SQLiteDatabase db, int id) {
        Cursor c = db.query(TABLE_NAME, new String[] {KEY_TITLE, KEY_CONTENT, KEY_DATE}, KEY_ID + " = ?", new String[] { String.valueOf(id) }, null, null, null);
        c.moveToFirst();
        return c;
    }

    public void removeNote(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    //Actualizar la nota
    public void updateNote(String title, String content, String editTitle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("noteTitle", title);
        cv.put("noteContent", content);
        cv.put("date", new Date().toString());

        db.update(TABLE_NAME, cv, KEY_TITLE + " LIKE '" +  editTitle +  "'", null);
        db.close();

    }
}
