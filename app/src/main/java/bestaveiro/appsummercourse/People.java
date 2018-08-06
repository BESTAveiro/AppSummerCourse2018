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

    private final String TAG = "People";
    int position;

    // Organisers

    String[] nomeDasPessoas_CoreTeam;
    String[] telemoveisDasPessoas_CoreTeam;
    String[] equipasDasPessoas_CoreTeam;
    String[] cargoDasPessoas_CoreTeam;


    String[] nomeDasPessoas_Organisers;
    String[] telemoveisDasPessoas_Organisers;
    String[] equipasDasPessoas_Organisers;
    String[] cargoDasPessoas_Organisers;

    //Pax

    String[] nomeDasPessoas_OrganisersPax;
    String[] telemoveisDasPessoas_OrganisersPax;
    String[] equipasDasPessoas_OrganisersPax;
    String[] cargoDasPessoas_OrganisersPax;

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
        position = getIntent().getIntExtra("position", position);


        groupPosition = position / 100;
        childPosition = (position - groupPosition * 100) - 1;
        Log.w(TAG, "GROUP POSITION: " + Integer.toString(groupPosition));
        Log.w(TAG, "CHILD POSITION: " + Integer.toString(childPosition));
        int groupPositionorgs = 10 + groupPosition;


        int login = 1; // Todo Mudar consoante o login
        switch (login) {
            // App dos Organisers
            case 1:
                if (groupPositionorgs == 11) {
                    nomeDasPessoas_CoreTeam = getResources().getStringArray(R.array.CoreTeam_Orgs);
                    telemoveisDasPessoas_CoreTeam = getResources().getStringArray(R.array.telemoveisDasPessoas_CoreTeam_Orgs);
                    equipasDasPessoas_CoreTeam = getResources().getStringArray(R.array.equipasDasPessoas_CoreTeam_Orgs);
                    cargoDasPessoas_CoreTeam = getResources().getStringArray(R.array.cargoDasPessoas_CoreTeam_Orgs);
                    Log.w(TAG, "ALERT Pussy:  Organisers");
                    TextView pais = (TextView) findViewById(R.id.texto2);
                    pais.setText("Cargo");
                    TextView sex = (TextView) findViewById(R.id.texto3);
                    sex.setText("Equipa");
                    TextView nome = (TextView) findViewById(R.id.text_view_nome);
                    nome.setText(nomeDasPessoas_CoreTeam[childPosition]);
                    TextView cargo = (TextView) findViewById(R.id.text_view_cargo);
                    cargo.setText(cargoDasPessoas_CoreTeam[childPosition]);
                    TextView equipas = (TextView) findViewById(R.id.text_view_equipa);
                    equipas.setText(equipasDasPessoas_CoreTeam[childPosition]);
                    final TextView numero = (TextView) findViewById(R.id.text_view_numero);
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
                } else if (groupPositionorgs == 12) {
                    nomeDasPessoas_Organisers = getResources().getStringArray(R.array.Organisers_Orgs);
                    telemoveisDasPessoas_Organisers = getResources().getStringArray(R.array.telemoveisDasPessoas_Organisers_Orgs);
                    equipasDasPessoas_Organisers = getResources().getStringArray(R.array.equipasDasPessoas_Organsiers_Orgs);
                    cargoDasPessoas_Organisers = getResources().getStringArray(R.array.cargoDasPessoas_Organisers_Orgs);
                    TextView cargoaserio = (TextView) findViewById(R.id.texto2);
                    cargoaserio.setText("Cargo");
                    TextView equipa = (TextView) findViewById(R.id.texto3);
                    equipa.setText("Equipa");
                    TextView nome = (TextView) findViewById(R.id.text_view_nome);
                    nome.setText(nomeDasPessoas_Organisers[childPosition]);
                    TextView cargo = (TextView) findViewById(R.id.text_view_cargo);
                    cargo.setText(cargoDasPessoas_Organisers[childPosition]);
                    TextView equipas = (TextView) findViewById(R.id.text_view_equipa);
                    equipas.setText(equipasDasPessoas_Organisers[childPosition]);
                    final TextView numero = (TextView) findViewById(R.id.text_view_numero);
                    numero.setText(telemoveisDasPessoas_Organisers[childPosition]);
                    numero.setPaintFlags(numero.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    numero.setOnClickListener(new TextView.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String input = numero.getText().toString();
                            Uri call = Uri.parse("tel:" + input);
                            Intent surf = new Intent(Intent.ACTION_DIAL, call);
                            startActivity(surf);
                        }
                    });

                } else if (groupPositionorgs == 13) {
                    nomeDasPessoas_Participants = getResources().getStringArray(R.array.Participants);
                    telemoveisDasPessoas_Participants = getResources().getStringArray(R.array.telemoveisDasPessoas_Participants);
                    equipasDasPessoas_Participants = getResources().getStringArray(R.array.equipasDasPessoas_Participants);
                    cargoDasPessoas_Participants = getResources().getStringArray(R.array.cargoDasPessoas_Participants);

                    TextView pais = (TextView) findViewById(R.id.texto2);
                    pais.setText("Country");

                    TextView sex = (TextView) findViewById(R.id.texto3);
                    sex.setText("Gender");

                    TextView nome = (TextView) findViewById(R.id.text_view_nome);
                    nome.setText(nomeDasPessoas_Participants[childPosition]);
                    TextView cargo = (TextView) findViewById(R.id.text_view_cargo);
                    cargo.setText(cargoDasPessoas_Participants[childPosition]);
                    TextView equipas = (TextView) findViewById(R.id.text_view_equipa);
                    equipas.setText(equipasDasPessoas_Participants[childPosition]);
                    final TextView numero = (TextView) findViewById(R.id.text_view_numero);
                    numero.setText(telemoveisDasPessoas_Participants[childPosition]);
                    numero.setPaintFlags(numero.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


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
                break;
                // App dos Participantes

            case 2: // TODO: Mudar a igualdade caso seja login de Pax
                if (groupPosition == 1) {

                    nomeDasPessoas_OrganisersPax = getResources().getStringArray(R.array.Organisers_Pax);
                    telemoveisDasPessoas_OrganisersPax = getResources().getStringArray(R.array.telemoveisDasPessoas_Organisers_Pax);
                    equipasDasPessoas_OrganisersPax = getResources().getStringArray(R.array.equipasDasPessoas_Organsiers_Pax);
                    cargoDasPessoas_OrganisersPax = getResources().getStringArray(R.array.cargosDasPessoas_Organisers_Pax);
                    Log.w(TAG, "ALERT Pussy:  Participantes");

                    TextView pais = (TextView) findViewById(R.id.texto2);
                    pais.setText("Cargo");

                    TextView sex = (TextView) findViewById(R.id.texto3);
                    sex.setText("Equipa");

                    TextView nome = (TextView) findViewById(R.id.text_view_nome);
                    nome.setText(nomeDasPessoas_OrganisersPax[childPosition]);
                    TextView cargo = (TextView) findViewById(R.id.text_view_cargo);
                    cargo.setText(cargoDasPessoas_OrganisersPax[childPosition]);
                    TextView equipas = (TextView) findViewById(R.id.text_view_equipa);
                    equipas.setText(equipasDasPessoas_OrganisersPax[childPosition]);
                    final TextView numero = (TextView) findViewById(R.id.text_view_numero);
                    numero.setText(telemoveisDasPessoas_OrganisersPax[childPosition]);
                    numero.setPaintFlags(numero.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


                    numero.setOnClickListener(new TextView.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String input = numero.getText().toString();
                            Uri call = Uri.parse("tel:" + input);
                            Intent surf = new Intent(Intent.ACTION_DIAL, call);
                            startActivity(surf);
                        }
                    });

                }else if (groupPosition == 2) {
                    nomeDasPessoas_Participants = getResources().getStringArray(R.array.Participants);
                    telemoveisDasPessoas_Participants = getResources().getStringArray(R.array.telemoveisDasPessoas_Participants);
                    equipasDasPessoas_Participants = getResources().getStringArray(R.array.equipasDasPessoas_Participants);
                    cargoDasPessoas_Participants = getResources().getStringArray(R.array.cargoDasPessoas_Participants);

                    TextView pais = (TextView) findViewById(R.id.texto2);
                    pais.setText("Country");

                    TextView sex = (TextView) findViewById(R.id.texto3);
                    sex.setText("Gender");

                    TextView nome = (TextView) findViewById(R.id.text_view_nome);
                    nome.setText(nomeDasPessoas_Participants[childPosition]);
                    TextView cargo = (TextView) findViewById(R.id.text_view_cargo);
                    cargo.setText(cargoDasPessoas_Participants[childPosition]);
                    TextView equipas = (TextView) findViewById(R.id.text_view_equipa);
                    equipas.setText(equipasDasPessoas_Participants[childPosition]);
                    final TextView numero = (TextView) findViewById(R.id.text_view_numero);
                    numero.setText(telemoveisDasPessoas_Participants[childPosition]);
                    numero.setPaintFlags(numero.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

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
                break;



        }
    }
}





