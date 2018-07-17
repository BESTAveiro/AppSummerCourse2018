package bestaveiro.appsummercourse;


import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


/**
 * Created by joao on 17/09/2017.
 */

public class People extends AppCompatActivity {

    private final String TAG="People";
    int position;
    String[] nomeDasPessoas_CoreTeam;
    String[] telemoveisDasPessoas_CoreTeam;
    String[] equipasDasPessoas_CoreTeam;
    String[] cargoDasPessoas_CoreTeam;
    String[] nomeDasPessoas_Organisers;
    String[] telemoveisDasPessoas_Organisers;
    String[] equipasDasPessoas_Organisers;
    String[] cargoDasPessoas_Organisers;
    String[] nomeDasPessoas_Participants;
    String[] telemoveisDasPessoas_Participants;
    String[] equipasDasPessoas_Participants;
    String[] cargoDasPessoas_Participants;
    int groupPosition;
    int childPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        position=getIntent().getIntExtra("position",position);


        groupPosition = position/100;
        childPosition = (position - groupPosition*100)-1;
        Log.w(TAG,"GROUP POSITION: "+Integer.toString(groupPosition));
        Log.w(TAG,"CHILD POSITION: "+Integer.toString(childPosition));


        if (groupPosition == 1){

            nomeDasPessoas_CoreTeam = getResources().getStringArray(R.array.CoreTeam);
            telemoveisDasPessoas_CoreTeam = getResources().getStringArray(R.array.telemoveisDasPessoas_CoreTeam);
            equipasDasPessoas_CoreTeam = getResources().getStringArray(R.array.equipasDasPessoas_CoreTeam);
            cargoDasPessoas_CoreTeam = getResources().getStringArray(R.array.cargoDasPessoas_CoreTeam);


            TextView nome=(TextView) findViewById(R.id.text_view_nome);
            nome.setText(nomeDasPessoas_CoreTeam[childPosition]);
            TextView cargo=(TextView) findViewById(R.id.text_view_cargo);
            cargo.setText(cargoDasPessoas_CoreTeam[childPosition]);
            TextView equipas=(TextView) findViewById(R.id.text_view_equipa);
            equipas.setText(equipasDasPessoas_CoreTeam[childPosition]);
            final TextView numero=(TextView) findViewById(R.id.text_view_numero);
            numero.setPaintFlags(numero.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            numero.setText(telemoveisDasPessoas_CoreTeam[childPosition]);

            numero.setOnClickListener(new TextView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String input = numero.getText().toString();
                    Uri call = Uri.parse("tel:" + input);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);

                }

            });

        }else if(groupPosition == 2){
            nomeDasPessoas_Organisers = getResources().getStringArray(R.array.Organisers);
            telemoveisDasPessoas_Organisers = getResources().getStringArray(R.array.telemoveisDasPessoas_Organisers);
            equipasDasPessoas_Organisers = getResources().getStringArray(R.array.equipasDasPessoas_Organsiers);
            cargoDasPessoas_Organisers = getResources().getStringArray(R.array.cargoDasPessoas_Organisers);


            TextView nome=(TextView) findViewById(R.id.text_view_nome);
            nome.setText(nomeDasPessoas_Organisers[childPosition]);
            TextView cargo=(TextView) findViewById(R.id.text_view_cargo);
            cargo.setText(cargoDasPessoas_Organisers[childPosition]);
            TextView equipas=(TextView) findViewById(R.id.text_view_equipa);
            equipas.setText(equipasDasPessoas_Organisers[childPosition]);
            final TextView numero=(TextView) findViewById(R.id.text_view_numero);
            numero.setText(telemoveisDasPessoas_Organisers[childPosition]);

            numero.setOnClickListener(new TextView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String input = numero.getText().toString();
                    Uri call = Uri.parse("tel:" + input);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
            });

        }else if(groupPosition == 3){
            nomeDasPessoas_Participants = getResources().getStringArray(R.array.Participants);
            telemoveisDasPessoas_Participants = getResources().getStringArray(R.array.telemoveisDasPessoas_Participants);
            equipasDasPessoas_Participants = getResources().getStringArray(R.array.equipasDasPessoas_Participants);
            cargoDasPessoas_Participants = getResources().getStringArray(R.array.cargoDasPessoas_Participants);


            TextView nome=(TextView) findViewById(R.id.text_view_nome);
            nome.setText(nomeDasPessoas_Participants[childPosition]);
            TextView cargo=(TextView) findViewById(R.id.text_view_cargo);
            cargo.setText(cargoDasPessoas_Participants[childPosition]);
            TextView equipas=(TextView) findViewById(R.id.text_view_equipa);
            equipas.setText(equipasDasPessoas_Participants[childPosition]);
            final TextView numero=(TextView) findViewById(R.id.text_view_numero);
            numero.setText(telemoveisDasPessoas_Participants[childPosition]);

            numero.setOnClickListener(new TextView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String input = numero.getText().toString();
                    Uri call = Uri.parse("tel:" + input);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);

                }

            });
        }

    }



}
