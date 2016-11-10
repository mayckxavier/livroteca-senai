package br.org.livropedia.livropedia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import br.org.livropedia.livropedia.Model.Livro;
import br.org.livropedia.livropedia.R;

/**
 * Created by aluno on 10/11/2016.
 */
public class LivroArrayAdapter extends ArrayAdapter<Livro> {

    private LayoutInflater inflater = null;
    private ArrayList<Livro> listaLivros;
    private Context context;


    public LivroArrayAdapter(Context context, int resource, ArrayList<Livro> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listaLivros = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = inflater.inflate(R.layout.custom_book_row_list,parent);
        }

        TextView livroTitulo = (TextView) convertView.findViewById(R.id.livro_titulo);
        TextView livroAutor = (TextView) convertView.findViewById(R.id.livro_autor);
        TextView livroEditora = (TextView) convertView.findViewById(R.id.livro_editora);
        TextView livroStatus = (TextView) convertView.findViewById(R.id.livro_status);
        ImageView livroFoto = (ImageView) convertView.findViewById(R.id.livro_foto);

        Livro livro = listaLivros.get(position);

        livroTitulo.setText(livro.getTitulo());
        livroAutor.setText(livro.getAutor());
        livroEditora.setText(livro.getEditora());
        livroStatus.setText(livro.getStatus());

        Glide.with(context).load(livro.getFoto()).into(livroFoto);

        return convertView;
    }
}
