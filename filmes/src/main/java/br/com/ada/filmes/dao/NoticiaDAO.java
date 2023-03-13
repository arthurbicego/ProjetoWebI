package br.com.ada.filmes.dao;

import br.com.ada.filmes.model.Noticia;
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
public class NoticiaDAO {
    private static Logger logger = LoggerFactory.getLogger(NoticiaDAO.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<Noticia> noticias = new ArrayList<>();
    private static int proximoId = 1;

    static {
        try {
            noticias = objectMapper.readValue(
                    new File("src/main/java/br/com/ada/filmes/database/noticias.json"),
                    new TypeReference<>() {
                    });

            logger.info("Arquivo 'noticias.json' foi lido!");

            if(noticias.size() > 0) proximoId = noticias.get( noticias.size() - 1 ).getId() + 1;

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void adicionar(Noticia noticia){
        noticia.setId(proximoId++);
        noticias.add(noticia);

        salvarArquivo();
    }

    public void remover(int id){
        noticias.removeIf(noticia -> noticia.getId() == id);

        salvarArquivo();
    }

    private static void salvarArquivo() {
        try{
            objectMapper.writeValue(
                    new File("src/main/java/br/com/ada/filmes/database/noticias.json"),
                    noticias
            );
        } catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    public List<Noticia> buscarTodos(){
        return noticias;
    }

    public List<Noticia> buscarUltimasNoticias(){
        return noticias.stream()
                .sorted(Comparator.comparing(Noticia::getId).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public Noticia buscarPorId(int id){
        return noticias.stream()
                .filter(noticia -> noticia.getId() == id)
                .findFirst().orElse(null);
    }

    public void atualizar(Noticia noticia){
        for(int i = 0; i < noticias.size(); i++){
            Noticia n = noticias.get(i);

            if(n.getId() == noticia.getId()) noticias.set(i, noticia);
        }

        salvarArquivo();
    }
}
