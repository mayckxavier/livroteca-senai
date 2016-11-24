package br.org.livropedia.livropedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import br.org.livropedia.livropedia.Helpers.ImageHelper;
import br.org.livropedia.livropedia.Services.LivrosService;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView foto;
    String fotoBase64;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.foto = (ImageView) findViewById(R.id.fotoLivro);

        findViewById(R.id.fotoLivro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        findViewById(R.id.botaoEnviar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject livro = new JSONObject();

                EditText titulo = (EditText) findViewById(R.id.tituloLivro);
                EditText autor = (EditText) findViewById(R.id.autorLivro);
                EditText editora = (EditText) findViewById(R.id.editoraLivro);
                EditText status = (EditText) findViewById(R.id.statusLivro);

                try {
                    livro.put("titulo", titulo.getText().toString());
                    livro.put("autor", autor.getText().toString());
                    livro.put("editora", editora.getText().toString());
                    livro.put("status", status.getText().toString());
                    livro.put("foto", fotoBase64);
                    LivrosService livrosService = new LivrosService(MainActivity.this);
                    livrosService.postLivro(livro);

                } catch (JSONException e) {
                    Log.e("Post livro: ", "erro", e);
                }

            }
        });


    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            fotoBase64 = ImageHelper.encodeToBase64((Bitmap) extras.get("data"));


            foto.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
