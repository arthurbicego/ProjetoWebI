package br.com.ada.filmes.controller;

import br.com.ada.filmes.dao.FilmeDAO;
import br.com.ada.filmes.dao.NoticiaDAO;
import br.com.ada.filmes.model.Filme;
import br.com.ada.filmes.model.Noticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private FilmeDAO filmeDAO;
    @Autowired
    private NoticiaDAO noticiaDAO;

    @GetMapping
    public String indexPage(Model model){

        List<Filme> filmes = filmeDAO.buscarFilmesMaisCurtidos();
        model.addAttribute("filmes", filmes);

        List<Noticia> noticias = noticiaDAO.buscarUltimasNoticias();
        model.addAttribute("noticias", noticias);

        return "index";
    }
}
