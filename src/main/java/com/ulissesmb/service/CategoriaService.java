package com.ulissesmb.service;

import java.util.List;

import com.ulissesmb.core.AbstractService;
import com.ulissesmb.domain.entity.Categoria;
import com.ulissesmb.domain.seach.CategoriaSearchFilter;
import com.ulissesmb.request.CategoriaDTO;

public interface CategoriaService extends AbstractService<Long, Categoria, CategoriaSearchFilter, CategoriaDTO> {
	
	List<Categoria> getCategorias() throws Exception;

}
