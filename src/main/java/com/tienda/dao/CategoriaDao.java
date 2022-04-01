package com.tienda.dao;

import com.tienda.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaDao extends CrudRepository<Categoria, Long>{
    
}
