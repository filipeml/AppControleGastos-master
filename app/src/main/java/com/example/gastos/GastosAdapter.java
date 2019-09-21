package com.example.gastos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GastosAdapter extends ArrayAdapter {
    public GastosAdapter(Context context, List<Gastos> objetcts) {
        super(context, 0, objetcts);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView =convertView;

        if(listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_gastos, parent, false);
        }

        Gastos current = (Gastos) getItem(position);
        TextView nome =  (TextView) listItemView.findViewById(R.id.txtDescricao );
        TextView data =  (TextView) listItemView.findViewById(R.id.txtData);
        TextView tipo =  (TextView) listItemView.findViewById(R.id.txtTipo);
        TextView valor = (TextView) listItemView.findViewById(R.id.txtValor);
        nome.setText( current.getDescricao ().toString() );
        data.setText( current.getData().toString() );
        tipo.setText( current.getTipo().toString() );
        valor.setText( current.getValor().toString() );
        return  listItemView;
    }

}
