package bestaveiro.appsummercourse;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by joao on 17/09/2017.
 */

public class People extends AppCompatActivity {

    int position;
    String[] nomeDasPessoas;
    String[] telemoveisDasPessoas;
    String[] equipasDasPessoas;
    String[] cargoDasPessoas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        position=getIntent().getIntExtra("position",position);
//
//        nomeDasPessoas = getResources().getStringArray(R.array.nome_das_pessoas);
//        telemoveisDasPessoas = getResources().getStringArray(R.array.telemoveis_das_pessoas);
//        equipasDasPessoas = getResources().getStringArray(R.array.equipas_das_pessoas);
//        cargoDasPessoas = getResources().getStringArray(R.array.cargo_das_pessoas);
//
//
//        TextView nome=(TextView) findViewById(R.id.text_view_nome);
//        nome.setText(nomeDasPessoas[position]);
//        TextView cargo=(TextView) findViewById(R.id.text_view_cargo);
//        cargo.setText(cargoDasPessoas[position]);
//        TextView equipas=(TextView) findViewById(R.id.text_view_equipa);
//        equipas.setText(equipasDasPessoas[position]);
//        TextView numero=(TextView) findViewById(R.id.text_view_numero);
//        numero.setText(telemoveisDasPessoas[position]);


    }
}
