package com.tienda.service;

import com.tienda.dao.CategoriaDao;
import com.tienda.model.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImlp implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activo) {
        var lista = (List<Categoria>) categoriaDao.findAll();
        
        if(activo) {
            lista.removeIf(e -> !e.isActivo());}
        return lista;
    }

    @Override
    @Transactional
    public void save(Categoria cliente) {
        categoriaDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Categoria cliente) {
        categoriaDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria cliente) {
        return categoriaDao.findById(cliente.getIdCategoria()).orElse(null);
    }

}
