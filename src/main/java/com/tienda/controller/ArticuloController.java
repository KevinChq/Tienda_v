package com.tienda.controller;

import com.tienda.model.Articulo;
import com.tienda.service.ArticuloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/articulo/listado")
    public String inicio(Model model) {
        var articulos = articuloService.getArticulos(false);
        var activo = articuloService.getArticulos(true);

        var inventarioTotal = 0;
        for (var c : articulos) {
            inventarioTotal += c.existencias * c.precio;
        }

        model.addAttribute("inventarioTotal", inventarioTotal);
        model.addAttribute("totalArticulos", articulos.size());
        model.addAttribute("totalExisten", activo.size());

        model.addAttribute("articulos", articulos);
        return "/articulo/listado";
    }

    @GetMapping("/articulo/nuevo")
    public String nuevoArticulo(Articulo articulo) {
        return "/articulo/modifica";
    }

    @PostMapping("/articulo/guardar")
    public String guardarArticulo(Articulo articulo) {
        articuloService.save(articulo);
        return "redirect:/articulo/listado";
    }

    @GetMapping("/articulo/modificar/{idArticulo}")
    public String modificarArticulo(Articulo articulo, Model model) {
        articulo = articuloService.getArticulo(articulo);
        model.addAttribute("articulo", articulo);
        return "/articulo/modifica";
    }

    @GetMapping("/articulo/eliminar/{idArticulo}")
    public String eliminarArticulo(Articulo articulo) {
        articuloService.delete(articulo);
        return "redirect:/articulo/listado";
    }
}
