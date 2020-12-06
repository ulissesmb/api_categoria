package com.ulissesmb.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ulissesmb.core.impl.AbstractServiceImpl;
import com.ulissesmb.domain.entity.Categoria;
import com.ulissesmb.domain.entity.Categoria_;
import com.ulissesmb.domain.seach.CategoriaSearchFilter;
import com.ulissesmb.exception.NotFoundException;
import com.ulissesmb.repository.CategoriaRepository;
import com.ulissesmb.request.CategoriaDTO;
import com.ulissesmb.service.CategoriaService;

@Service
public class CategoriaServiceImpl
		extends AbstractServiceImpl<Long, Categoria, CategoriaSearchFilter, CategoriaDTO, CategoriaRepository>
		implements CategoriaService {

	public CategoriaServiceImpl() {
		super(CategoriaDTO.class, Categoria.class);
	}

	@Override
	public List<Categoria> getCategorias() throws Exception {
		
		List<Categoria> result = getDao().findAll(Sort.by(Sort.Direction.DESC, "id"));
		if(result.isEmpty())
			throw new NotFoundException();
		
		return result;
	}
	
	@Override
	public List<CategoriaDTO> filter(CategoriaSearchFilter filter) {
		super.buildPredicates();
		super.addLike(super.getRoot().get(Categoria_.NOME), filter.getNome());
		List<Categoria> categorias = super.search();
		if (categorias.isEmpty())
			throw new NotFoundException();

		return categorias.stream().map(f -> converter(f)).collect(Collectors.toList());
	}

}
