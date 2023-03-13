package br.com.ada.filmes.model;

import java.util.ArrayList;
import java.util.List;

public class Filme {
    private int id;
    private String titulo;
    private String genero;
    private int duracao;
    private String sinopse;
    private String imagem;
    private int like;
    private int dislike;
    private boolean favorite = false;
    private List<Integer> atoresIds = new ArrayList<>();
    private List<Ator> atores = new ArrayList<>();
    public Filme(){

    }

    public Filme(String titulo, String genero, int duracao, String sinopse, String imagem) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.imagem = imagem;
        this.like = 0;
        this.dislike = 0;
    }

    public Filme(String titulo, String genero, int duracao, String sinopse, String imagem, List<Integer> atoresIds) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.imagem = imagem;
        this.like = 0;
        this.dislike = 0;
        this.atoresIds = atoresIds;
    }

    public List<Integer> getAtoresIds() {
        return atoresIds;
    }

    public void setAtoresIds(List<Integer> atoresIds) {
        this.atoresIds = atoresIds;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
