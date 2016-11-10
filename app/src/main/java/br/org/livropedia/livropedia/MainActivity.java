package br.org.livropedia.livropedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import br.org.livropedia.livropedia.Services.LivrosService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.botaoEnviar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject livro = new JSONObject();

                EditText titulo = (EditText) findViewById(R.id.tituloLivro);
                EditText autor = (EditText) findViewById(R.id.autorLivro);
                EditText editora = (EditText) findViewById(R.id.editoraLivro);
                EditText foto = (EditText) findViewById(R.id.fotoLivro);
                EditText status = (EditText) findViewById(R.id.statusLivro);

                try {
                    livro.put("titulo", titulo.getText().toString());
                    livro.put("autor", autor.getText().toString());
                    livro.put("editora", editora.getText().toString());
                    livro.put("foto", foto.getText().toString());
                    livro.put("status", status.getText().toString());

                    LivrosService livrosService = new LivrosService();
                    livrosService.postLivro(livro);

                } catch (JSONException e) {
                    Log.e("Post livro: ", "erro", e);
                }

            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        LivrosService livrosService = new LivrosService();
        livrosService.getLivros();
    }
}
