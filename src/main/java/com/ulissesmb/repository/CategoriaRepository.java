package com.ulissesmb.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ulissesmb.domain.entity.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long>{

	List<Categoria> findAll(Sort by);
	
}
