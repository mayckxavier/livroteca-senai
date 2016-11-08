package br.org.livropedia.livropedia.Services;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

//Biblioteca para requisição http
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by aluno on 08/11/2016.
 */
public class LivrosService {

    private static final String BASE_URL = "http://mayckxavier.com/projetos/livropedia/";

    public void getLivros() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                String endPoint = "get_livros.php";
                String url = BASE_URL + endPoint;

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String resposta = response.body().string();
                    Log.e("getLivros", resposta);
                } catch (IOException e) {
                    Log.e("getLivros", "error", e);
                }
                return null;
            }
        }.execute();
    }


    public void postLivro(final JSONObject livro) {
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        String endPoint = "create_livro.php";
        final String url = BASE_URL + endPoint;

        final OkHttpClient client = new OkHttpClient();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                RequestBody body = RequestBody.create(JSON, livro.toString());
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    Log.e("creteLivro: ", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();
    }
}
