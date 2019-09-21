package com.example.gastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class GastosDao {

    private SQLiteDatabase db;
    private DbHelper banco;


    public GastosDao(Context context){
        banco = new DbHelper(context);
    }

    public String save( Gastos gasto ){

        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        try{
            valores = new ContentValues();
            valores.put( banco.DESCRICAO , gasto.getDescricao ().toString() );
            valores.put( banco.TIPO, gasto.getTipo().toString() );
            valores.put( banco.VALOR, gasto.getValor().toString() );
            valores.put( banco.DATA, gasto.getData().toString() );
            resultado = db.insert( banco.TABELA,null, valores );
            System.out.printf("resultado insert: " + resultado);
            db.close();
            if ( resultado != -1 ) {
                return "Gasto incluído ="+ gasto.getDescricao ();
            }
        } catch ( SQLException e ) {
            Log.e( "ERRO - ", e.getMessage() );
        }
        return "ERRO!";
    }

    public ArrayList<Gastos> list() {
        ArrayList<Gastos> lista = new ArrayList<>();
        Cursor cursor;
        String[] campos = { DbHelper.ID, DbHelper.DESCRICAO, DbHelper.TIPO,DbHelper.DATA,DbHelper.VALOR };
        db = banco.getReadableDatabase();
        cursor = db.query( DbHelper.TABELA, campos,null,null,null,null,null );
          if ( cursor != null ) {
              cursor.moveToFirst();
              while ( cursor.moveToNext() ) {
                int id = cursor.getInt(0) ;
                String nome = cursor.getString(1) ;
                String tipo = cursor.getString(2) ;
                String data = cursor.getString(3) ;
                String valor = cursor.getString(4) ;
                Gastos gastos = new Gastos ( id, nome, data, tipo, valor );
                lista.add( gastos );
              }
              return lista;
          }
          return null;
    }

    public String deletar( Gastos g ) {
        String where = DbHelper.ID + "= " + g.getId();
        db = banco.getReadableDatabase();
        db.delete( DbHelper.TABELA, where, null );
        db.close();
        return "Gasto Removido!";
    }

    public String editar( Gastos g ) {
        ContentValues valores;

        valores = new ContentValues();
        valores.put( banco.DESCRICAO , g.getDescricao () );
        valores.put( banco.TIPO, g.getTipo() );
        valores.put( banco.VALOR, g.getValor() );
        valores.put( banco.DATA, g.getData() );
        String where = DbHelper.ID + "= " + g.getId();

        db = banco.getReadableDatabase();
        db.update( DbHelper.TABELA, valores, where, null );
        db.close();
        return "Gasto Editado!";
    }


}
