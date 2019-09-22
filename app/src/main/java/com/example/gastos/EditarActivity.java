package com.example.gastos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;


public class EditarActivity extends AppCompatActivity {

    EditText edtDescricao;
    EditText edtTipo;
    EditText edtValor;
    EditText edtData;
    Button btnSalvar;
    Button btnExcluir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtData = findViewById(R.id.edtData);
        edtTipo = findViewById(R.id.edtTipo);
        edtValor = findViewById(R.id.edtValor);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);

        //adicionando mascara para o campo data
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(edtData, smf);
        edtData.addTextChangedListener(mtw);

        Bundle extra = getIntent().getExtras();


        Intent intent = getIntent();
        final Gastos g = (Gastos)intent.getSerializableExtra("conta");
        if (extra != null) {
            int chave = extra.getInt("id");
            edtDescricao.setText( g.getDescricao () );
            edtTipo.setText( g.getTipo() );
            edtData.setText(  g.getData() );
            edtValor.setText( g.getValor() );
        }

        btnSalvar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(getBaseContext());

                g.setDescricao( edtDescricao.getText().toString());
                g.setTipo( edtTipo.getText().toString() );
                g.setData( edtData.getText().toString() );
                g.setValor( edtValor.getText().toString() );

                GastosDao gastosDao = new GastosDao ( getBaseContext() );

                String msg = gastosDao.editar( g );
                Toast.makeText( getBaseContext(),msg,Toast.LENGTH_LONG ).show();
            }
        } );

        btnExcluir.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(getBaseContext());

                GastosDao gastosDao = new GastosDao (getBaseContext());
                String msg = gastosDao.deletar(g);
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
            }
        } );
    }
}