package br.com.ada.filmes.controller;

import br.com.ada.filmes.model.Noticia;
import br.com.ada.filmes.dao.NoticiaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/noticia")
public class NoticiaController {
    @Autowired
    private NoticiaDAO noticiaDAO;

    @GetMapping
    public String indexPage(Model model){
        List<Noticia> noticias = noticiaDAO.buscarTodos();
        model.addAttribute("noticias", noticias);

        return "noticias";
    }

    @GetMapping("/novo")
    public String novo(Model model){
        model.addAttribute("noticia", new Noticia());
        return "noticia_nova";
    }

    @PostMapping("/novo")
    public String adicionar(Noticia noticia){
        noticiaDAO.adicionar(noticia);
        return "redirect:/noticia";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        Noticia noticia = noticiaDAO.buscarPorId(id);
        model.addAttribute("noticia", noticia);

        return "noticia_editar";
    }

    @PostMapping("/editar")
    public String editar(Noticia noticia){
        noticiaDAO.atualizar(noticia);
        return "redirect:/";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        try{
            noticiaDAO.remover(id);
            return "redirect:/";
        } catch (Exception error){
            System.out.println(error.getMessage());
            return "redirect:/";
        }

    }

}
