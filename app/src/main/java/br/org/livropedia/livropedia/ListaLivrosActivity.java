package br.org.livropedia.livropedia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.org.livropedia.livropedia.Services.LivrosService;

/**
 * Created by aluno on 10/11/2016.
 */
public class ListaLivrosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livros);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LivrosService livrosService = new LivrosService(ListaLivrosActivity.this);
        livrosService.getLivros();
    }
}
