package br.com.ada.filmes.dao;

import br.com.ada.filmes.model.Filme;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmeDAO {
    private static Logger logger = LoggerFactory.getLogger(FilmeDAO.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<Filme> filmes = new ArrayList<>();

    private static int proximoId = 1;

    static {
        try {
            filmes = objectMapper.readValue(
                    new File("src/main/java/br/com/ada/filmes/database/filmes.json"),
                    new TypeReference<>() {
                    });

            logger.info("Arquivo 'filmes.json' foi lido!");

            if(filmes.size() > 0) proximoId = filmes.get( filmes.size() - 1 ).getId() + 1;

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


    public void adicionar(Filme filme)  {
        filme.setId(proximoId++);
        filmes.add(filme);

        salvarArquivo();
    }

    public List<Filme> buscarFilmesMaisCurtidos(){
        Comparator<Filme> compararPorCurtidas = Comparator.comparingInt(Filme::getLike).reversed();
        return filmes.stream()
                .sorted(compararPorCurtidas)
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<String> generosExistentes(){
        return filmes.stream()
                .map(Filme::getGenero)
                .distinct().collect(Collectors.toList());
    }

    public void remover(int id) {
        filmes.removeIf(filme -> filme.getId() == id);
        salvarArquivo();
    }

    public List<Filme> buscarTodos(String generoFiltrado){
        if(generoFiltrado.isEmpty()){
            return filmes;
        }
        return filmes.stream()
                .filter(filme -> filme.getGenero().equalsIgnoreCase(generoFiltrado) )
                .collect(Collectors.toList());
    }

    public List<Filme> buscarFilmesFavoritos(){
        return filmes.stream()
                .filter(Filme::getFavorite)
                .collect(Collectors.toList());
    }

    public Filme buscarPorId(int id){
        return filmes.stream()
                .filter(filme -> filme.getId() == id)
                .findFirst().orElse(null);
    }

    public void atualizar(Filme filme){
        for(int i=0; i < filmes.size(); i++){
            Filme f = filmes.get(i);

            if(f.getId() == filme.getId()) filmes.set(i, filme);
        }
    }

    public void favoritarFilme(int id){
        filmes.stream()
                .filter(filme -> filme.getId() == id)
                .forEach(filmeResponse -> filmeResponse.setFavorite(true));
        salvarArquivo();
    }
    public void desfavoritarFilme(int id){
        filmes.stream()
                .filter(filme -> filme.getId() == id)
                .forEach(filmeResponse -> filmeResponse.setFavorite(false));
        salvarArquivo();
    }
    public void likeFilme(int id){
        filmes.stream()
                .filter(filme -> filme.getId() == id)
                .forEach(filmeResponse -> filmeResponse.setLike(filmeResponse.getLike() + 1));
        salvarArquivo();
    }
    public void dislikeFilme(int id){
        filmes.stream()
                .filter(filme -> filme.getId() == id)
                .forEach(filmeResponse -> filmeResponse.setLike(filmeResponse.getDislike() + 1));
        salvarArquivo();
    }

    private static void salvarArquivo() {
        try {
            objectMapper.writeValue(
                    new File("src/main/java/br/com/ada/filmes/database/filmes.json"),
                    filmes);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


}
