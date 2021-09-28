package com.example.atividaden1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Banco extends SQLiteOpenHelper {

    private static final String Nome_Banco = "AtividadeN1";
    private static final int VERSAO = 1;

    public Banco(Context context){
        super(context, Nome_Banco, null, VERSAO);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL(     "CREATE TABLE IF NOT EXISTS fichas ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " nome TEXT  NOT NULL , " +
                " level TEXT NOT NULL, " +
                " classes TEXT NOT NULL"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
