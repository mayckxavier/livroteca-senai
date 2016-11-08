package br.org.livropedia.livropedia.Services;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


//Biblioteca para requisição http
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import static android.R.attr.password;

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
        String endPoint = "create_livro.php";
        final String url = BASE_URL + endPoint;

        final OkHttpClient client = new OkHttpClient();

        Log.e("clicando no botão","");


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    RequestBody formBuilder = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("titulo", livro.getString("titulo"))
                            .addFormDataPart("autor", livro.getString("autor"))
                            .addFormDataPart("editora", livro.getString("editora"))
                            .addFormDataPart("foto", livro.getString("foto"))
                            .addFormDataPart("status", livro.getString("status"))
                            .build();


//                    FormBody.Builder formBuilder = new FormBody.Builder()
//                            .add("titulo", livro.getString("titulo"))
//                            .add("autor", livro.getString("autor"))
//                            .add("editora", livro.getString("editora"))
//                            .add("foto", livro.getString("foto"))
//                            .add("status", livro.getString("status"));

                    RequestBody body = formBuilder;
                    Request request = new Request.Builder()
                            .url(url)
                            .post(body)
                            .build();
                    Response response = null;

                    response = client.newCall(request).execute();
                    Log.e("creteLivro: ", response.body().string());
                } catch (IOException e) {
                    Log.e("creteLivro: ", "",e);
                    e.printStackTrace();
                } catch (JSONException e) {
                    Log.e("creteLivro: ", "",e);
                }

                return null;
            }
        }.execute();
    }
}
