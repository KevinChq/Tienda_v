package com.tienda.dao;

import com.tienda.model.Articulo;
import org.springframework.data.repository.CrudRepository;

public interface ArticuloDao extends CrudRepository<Articulo, Long>{
    
}
