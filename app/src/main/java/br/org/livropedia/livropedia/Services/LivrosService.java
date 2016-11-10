package br.org.livropedia.livropedia.Services;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


//Biblioteca para requisição http
import br.org.livropedia.livropedia.ListaLivrosActivity;
import br.org.livropedia.livropedia.Model.Livro;
import br.org.livropedia.livropedia.R;
import br.org.livropedia.livropedia.adapters.LivroArrayAdapter;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by aluno on 08/11/2016.
 */
public class LivrosService {

    private static final String BASE_URL = "http://www.mayckxavier.com/projetos/livropedia/";

    Activity minhaActivity;

    public LivrosService(Activity listaLivrosActivity) {
        minhaActivity = listaLivrosActivity;
    }


    public void getLivros() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                String endPoint = "livros/get_all";
                String url = BASE_URL + endPoint;

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String resposta = response.body().string();

                    JSONArray jsonArray = new JSONArray(resposta);

                    ArrayList<Livro> arrayLivros = new ArrayList<Livro>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject livroJson = (JSONObject) jsonArray.get(i);
                        Livro livro = new Livro();
                        livro.setTitulo(livroJson.getString("titulo"));
                        livro.setAutor(livroJson.getString("autor"));
                        livro.setEditora(livroJson.getString("editora"));
                        livro.setStatus(livroJson.getString("status"));
                        livro.setFoto(livroJson.getString("foto"));

                        arrayLivros.add(livro);
                    }


                    LivroArrayAdapter livroArrayAdapter =
                            new LivroArrayAdapter(minhaActivity,
                                    R.layout.custom_book_row_list,
                                    arrayLivros);

                    ListView listView = (ListView) (minhaActivity).findViewById(R.id.lista);
                    listView.setAdapter(livroArrayAdapter);

                    Log.e("getLivros", resposta);
                } catch (IOException e) {
                    Log.e("getLivros", "error", e);
                } catch (JSONException e) {
                    Log.e("getLivros", "error", e);
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }


    public void postLivro(final JSONObject livro) {
        String endPoint = "livros/create";
        final String url = BASE_URL + endPoint;

        final OkHttpClient client = new OkHttpClient();

        Log.e("clicando no botão", "");


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    RequestBody formBody = new FormBody.Builder()
                            .add("titulo", livro.getString("titulo"))
                            .add("autor", livro.getString("autor"))
                            .add("editora", livro.getString("editora"))
                            .add("foto", livro.getString("foto"))
                            .add("status", livro.getString("status"))
                            .build();

                    Request request = new Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build();
                    Response response = null;

                    response = client.newCall(request).execute();
                    Log.e("creteLivro: ", response.body().string());
                } catch (IOException e) {
                    Log.e("creteLivro: ", "", e);
                    e.printStackTrace();
                } catch (JSONException e) {
                    Log.e("creteLivro: ", "", e);
                }

                return null;
            }
        }.execute();
    }
}
