package br.com.ada.filmes.model;

public class Ator {
    private int id;
    private String nome;
    private String apelido;
    private String nacionalidade;
    private String imagem;

    public Ator(){

    }

    public Ator(String nome, String apelido, String nacionalidade, String imagem) {
        this.nome = nome;
        this.apelido = apelido;
        this.nacionalidade = nacionalidade;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "'" + nome + '\'';
    }
}
