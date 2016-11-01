package br.org.livropedia.livropedia.Model;

/**
 * Created by aluno on 25/10/2016.
 */
public class Livro {

    private String titulo;
    private String autor;
    private String editora;
    private String foto;
    private String status;

    public Livro() {
    }

    public Livro(String titulo, String autor, String editora, String foto, String status) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.foto = foto;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
