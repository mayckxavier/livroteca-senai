package br.org.livropedia.livropedia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroDeLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_livro);


        final EditText tituloEdt = (EditText) findViewById(R.id.ltitulo);
        Button cadastrar = (Button) findViewById(R.id.btncadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {

            public StringBuilder errorMessage;
            public Boolean is_valid;

            @Override
            public void onClick(View v) {
                String titulo = tituloEdt.getText().toString();

                /**
                 *  @TODO - Não esquecer as validações
                 *  1 - Verificar se o campo titulo está vazio
                 */

                Boolean is_valid = true;
                StringBuilder errorMessage = new StringBuilder("Por favor, preencha os seguintes campos:");

                if (titulo.toString().isEmpty()) {
                    errorMessage.append("\n");
                    errorMessage.append(" - Titulo");
                    is_valid = false;
                }
            }
        });
    }
}
