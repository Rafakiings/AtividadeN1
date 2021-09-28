package com.example.atividaden1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FichaDAO {

    public static void inserir(Context context, Ficha ficha){
        ContentValues valores = new ContentValues();
        valores.put("nome", ficha.getNome() );
        valores.put("level", ficha.getLevel() );
        valores.put("classe", ficha.getClasses() );

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("fichas", null, valores);
    }

    public static void editar(Context context, Ficha ficha){
        ContentValues valores = new ContentValues();
        valores.put("nome", ficha.getNome() );
        valores.put("level", ficha.getLevel() );
        valores.put("classe", ficha.getClasses() );

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("fichas", valores, " id = " + ficha.getId(), null);
    }

    public static void excluir(Context context, int idFicha){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("fichas", " id = " + idFicha , null);
    }

    public static List<Ficha> getFicha(Context context){
        List<Ficha> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produtos ORDER BY nome ", null );

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                Ficha pro = new Ficha();
                pro.setId( cursor.getInt( 0 ) );
                pro.setNome( cursor.getString(1) );
                pro.setLevel( cursor.getString(2) );
                pro.setClasses( cursor.getString(3) );
                lista.add( pro );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }


    public static Ficha getFichaById(Context context, int idFicha){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM fichas WHERE id =  " + idFicha, null );

        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            Ficha pro = new Ficha();
            pro.setId( cursor.getInt( 0 ) );
            pro.setNome( cursor.getString(1) );
            pro.setLevel( cursor.getString(2) );
            pro.setClasses( cursor.getString(3) );

            return pro;
        }else {
            return null;
        }
    }



}
