package com.example.atividaden1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CharActivity extends AppCompatActivity {

    private ListView lvPersonagens;
    private List<Ficha> Personagens;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( CharActivity.this, CreateChar.class);
                intent.putExtra("acao", "inserir");
                startActivity( intent );
            }
        });

        lvPersonagens = findViewById(R.id.lvPersonagens);
        lvPersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent( CharActivity.this, CreateChar.class);
                intent.putExtra("acao", "editar");
                int idFicha = Personagens.get(position).getId();
                intent.putExtra("idFicha", idFicha);
                startActivity( intent );
            }
        });
        lvPersonagens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                excluir( position );
                return true;
            }
        });
        carregarLista();
    }
    private void excluir(int posicao){
        Ficha charSelecionado = Personagens.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir");
        alerta.setIcon( android.R.drawable.ic_delete);
        alerta.setMessage("Confirma a exclusão do produto " + charSelecionado.getNome() + "? ");

        alerta.setNeutralButton("Cancelar", null);

        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FichaDAO.excluir(CharActivity.this, charSelecionado.getId() );
                carregarLista();
            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarLista();
    }

    private void carregarLista(){
        Personagens = FichaDAO.getFicha(this);

        if( Personagens.size() == 0 ){
            Ficha fake = new Ficha("Lista Vazia ", "");
            Personagens.add( fake );
            lvPersonagens.setEnabled(false);
        }else {
            lvPersonagens.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Personagens);
        lvPersonagens.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_char, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_container) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}