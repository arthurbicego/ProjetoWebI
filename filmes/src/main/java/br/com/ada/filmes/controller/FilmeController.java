package br.com.ada.filmes.controller;

import br.com.ada.filmes.dao.AtorDAO;
import br.com.ada.filmes.dao.NoticiaDAO;
import br.com.ada.filmes.dao.FilmeDAO;
import br.com.ada.filmes.model.Ator;
import br.com.ada.filmes.model.Filme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private FilmeDAO filmeDAO;
    @Autowired
    private AtorDAO atorDAO;

    @GetMapping
    public String indexPage(Model model, @RequestParam(required = false, defaultValue = "") String genero){
        List<String> generos = filmeDAO.generosExistentes();
        List<Filme> filmes = filmeDAO.buscarTodos(genero);

        model.addAttribute("filmes", filmes);
        model.addAttribute("generos", generos);

        return "filmes";
    }

    @GetMapping("/novo")
    public String novo(Model model){
        model.addAttribute("filme", new Filme());
        model.addAttribute("todos_atores", atorDAO.buscarTodos());
        return "filme_novo";
    }

    @PostMapping("/novo")
    public String adicionar(Filme filme) {
        List<Ator> atores = atorDAO.buscarAtoresPorIds(filme.getAtoresIds());
        filme.setAtores(atores);
        filmeDAO.adicionar(filme);
        return "redirect:/filme";

    }

    @GetMapping("/favoritar/{id}")
    public String favoritarFilme(@PathVariable int id){
        filmeDAO.favoritarFilme(id);
        return "redirect:/";
    }

    @GetMapping("/desfavoritar/{id}")
    public String desfavoritarFilme(@PathVariable int id){
        filmeDAO.desfavoritarFilme(id);
        return "redirect:/";
    }
    @GetMapping("/like/{id}")
    public String darLikeFilme(@PathVariable int id){
        filmeDAO.likeFilme(id);
        return "redirect:/";
    }

    @GetMapping("/dislike/{id}")
    public String darDislikeFilme (@PathVariable int id){
        filmeDAO.dislikeFilme(id);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        Filme filme = filmeDAO.buscarPorId(id);
        model.addAttribute("filme", filme);
        return "filme_editar";
    }

    @PostMapping("/editar")
    public String editar(Filme filme){
        filmeDAO.atualizar(filme);
        return "redirect:/";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        try{
            filmeDAO.remover(id);
            return "redirect:/";
        } catch (Exception error){
            System.out.println(error.getMessage());
            return "redirect:/";
        }

    }

    @GetMapping("/favoritos")
    public String filmesFavoritos(Model model){
        List<Filme> filmes = filmeDAO.buscarFilmesFavoritos();
        model.addAttribute("filmes", filmes);

        return "filmes_favoritos";
    }

}
