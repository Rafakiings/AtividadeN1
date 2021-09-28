package com.example.atividaden1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateChar extends AppCompatActivity {

    private EditText etNome;
    private Spinner spLevel;
    private Spinner spClasses;
    private Button btnSalvar;
    private String acao;
    private Ficha ficha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charcreate);

        etNome = findViewById(R.id.etNome);
        spLevel = findViewById(R.id.spLevel);
        spClasses = findViewById(R.id.spClasses);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarPersonagens();
        }
    }

    private void carregarPersonagens(){
        int idFicha = getIntent().getIntExtra("idFicha", 0);
        ficha = FichaDAO.getFichaById(this, idFicha);

        etNome.setText( ficha.getNome() );
        String[] level = getResources().getStringArray(R.array.level);
        String[] classes = getResources().getStringArray(R.array.classes);

        for( int i = 0; i < level.length ; i++){
            if( ficha.getLevel().equals( level[i]  )){
                spLevel.setSelection( i );
                break;
            }
        }
        for( int i = 0; i < classes.length ; i++){
            if( ficha.getClasses().equals( classes[i]  )){
                spClasses.setSelection( i );
                break;
            }
        }
    }

    private void salvar(){
        String nome = etNome.getText().toString();

        if( nome.isEmpty() || spLevel.getSelectedItemPosition() == 0 || spClasses.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Você deve preencher todos os campos!", Toast.LENGTH_LONG).show();
        }else {

            if(  acao.equals("inserir") ) {
                ficha = new Ficha();
            }

            ficha.setNome( nome );
            ficha.setLevel( spLevel.getSelectedItem().toString()  );
            ficha.setClasses( spClasses.getSelectedItem().toString()  );

            if(  acao.equals("inserir") ) {
                FichaDAO.inserir(this, ficha);
                etNome.setText("");
                spLevel.setSelection(0, true);
                spClasses.setSelection(0, true);
            }else{
                FichaDAO.editar(this, ficha);
                finish();
            }
        }
    }
}
