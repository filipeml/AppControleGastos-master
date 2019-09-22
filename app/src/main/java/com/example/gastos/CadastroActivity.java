package com.example.gastos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CadastroActivity extends AppCompatActivity {

    EditText edtDescricao;
    EditText edtTipo;
    EditText edtValor;
    EditText edtData;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cadastro );

        edtDescricao= findViewById( R.id.edtDescricao );
        edtData = findViewById( R.id.edtData );
        edtTipo = findViewById( R.id.edtTipo );
        edtValor = findViewById( R.id.edtValor );

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(edtData, smf);
        edtData.addTextChangedListener(mtw);

        btnSalvar = findViewById( R.id.btnSalvar );

        btnSalvar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper( getBaseContext() );

                Gastos gasto = null;
                gasto = new Gastos ( edtDescricao.getText().toString(), edtData.getText().toString(),
                                    edtTipo.getText().toString(), edtValor.getText().toString() );

                GastosDao gastosDao = new GastosDao ( getBaseContext() );

                String msg = gastosDao.save( gasto );
                Toast.makeText( getBaseContext(),msg,Toast.LENGTH_LONG ).show();
            }
        });
    }
}
