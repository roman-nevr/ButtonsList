package org.berendeev.buttonslist.data.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class DatabaseOpenHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DATABASE_NAME = "magnit.db";
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE = "list";
    public static final String NUMBER = "number";
    public static final String COEF = "coef";

    private static DatabaseOpenHelper instance;

    public static DatabaseOpenHelper getInstance (Context context){
        if (instance == null){
            return new DatabaseOpenHelper(context);
        } else {
            return instance;
        }
    }

    private DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        String script = "create table " + DATABASE_TABLE + " (" +
                BaseColumns._ID + " integer primary key autoincrement, " +
                NUMBER + " integer not null, " +
                COEF + " real);";
        db.execSQL(script);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}
