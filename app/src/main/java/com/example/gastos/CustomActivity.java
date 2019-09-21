package com.example.gastos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;

import java.util.ArrayList;

public class CustomActivity extends AppCompatActivity {
   public GastosAdapter adapter;
    ArrayList<Gastos> listaGastos;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_custom );
        listaGastos = new ArrayList<>();

        final GastosDao gastosDao = new GastosDao ( getApplicationContext() );
        listaGastos = gastosDao.list();

        adapter = new GastosAdapter ( this, listaGastos );
        ListView list = findViewById( R.id.listaContas );
        list.setAdapter( adapter );

        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Gastos a = ( Gastos ) parent.getItemAtPosition( position );

                Intent intent = new Intent(getApplicationContext(), EditarActivity.class);

                intent.putExtra( "id", a.get_id() );

                intent.putExtra( "nome", a.getDescricao ().toString() );
                intent.putExtra( "tipo", a.getTipo().toString() );
                intent.putExtra( "data", a.getData().toString() );
                intent.putExtra( "valor", a.getValor().toString() );
                intent.putExtra( "conta", a );
                startActivity( intent );
            }
        } );
    }
}
