package com.ulissesmb.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ulissesmb.domain.entity.Categoria;
import com.ulissesmb.domain.seach.impl.CategoriaSearchFilterImpl;
import com.ulissesmb.request.CategoriaDTO;
import com.ulissesmb.response.Response;
import com.ulissesmb.response.ResponseBuilder;
import com.ulissesmb.service.CategoriaService;

@RestController
@RequestMapping(CategoriaController.URI)
public class CategoriaController {

	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

	public static final String URI = "/categorias";

	private static final String DELETED_MESSAGE = "Registro removido com sucesso!";
	private static final String UPDATED_MESSAGE = "Registro atualizado com sucesso!";
	private static final String SAVED_MESSAGE = "Registro salvo com sucesso!";

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	@Secured({ "ROLE_ADMIN", "ROLE_USUARIO" })
	public ResponseEntity<Response<Categoria>> getAll() throws Exception {
		List<Categoria> result = categoriaService.getCategorias();
		return new ResponseBuilder<Categoria>()
				.withData(result)
				.build();
	}

	@Validated
	@PostMapping
	@Secured({ "ROLE_ADMIN", "ROLE_USUARIO" })
	public ResponseEntity<Response<CategoriaDTO>> add(@Valid @RequestBody CategoriaDTO dto) throws Exception {

		if (dto == null) {
			return new ResponseBuilder<CategoriaDTO>().withData(dto).withHttp(HttpStatus.NOT_FOUND).build();
		}

		if (dto.getId() == null || dto.getId() == 0) {
			dto.setId(null);
		}

		String categoria = dto.getNome();
		dto.setNome(categoria.toUpperCase());
		return new ResponseBuilder<CategoriaDTO>().withData(categoriaService.saveConverterDTO(dto))
				.withMessage(SAVED_MESSAGE).withHttp(HttpStatus.CREATED).build();
	}

	@PutMapping
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Response<CategoriaDTO>> update(@Valid @RequestBody CategoriaDTO dto) throws Exception {

		dto.setNome(dto.getNome().toUpperCase());
		CategoriaDTO updated = categoriaService.update(dto, categoriaService.getById(dto.getId()));
		log.info("Registro que esta sendo atualizando: {}", updated);

		return new ResponseBuilder<CategoriaDTO>().withData(updated).withMessage(UPDATED_MESSAGE)
				.withHttp(HttpStatus.CREATED).build();
	}

	@GetMapping(value = "{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USUARIO" })
	public ResponseEntity<Response<CategoriaDTO>> get(@PathVariable Long id) throws Exception {
		return new ResponseBuilder<CategoriaDTO>().withData(categoriaService.getByIdConverterDTO(id)).build();
	}

	@DeleteMapping(value = "{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Response<CategoriaDTO>> delete(@PathVariable Long id) throws Exception {
		categoriaService.deleteById(id);
		return new ResponseBuilder<CategoriaDTO>().withMessage(DELETED_MESSAGE).build();
	}

	@PatchMapping("filter")
	@Cacheable("brasilPrevCache")
	@Secured({ "ROLE_USUARIO", "ROLE_ADMIN" })
	public ResponseEntity<Response<CategoriaDTO>> filter(@Valid @RequestBody CategoriaSearchFilterImpl searchFilter)
			throws Exception {
		List<CategoriaDTO> response = categoriaService.filter(searchFilter);
		log.info("Registro localizado: {}", response.size());

		return new ResponseBuilder<CategoriaDTO>().withData(response).build();
	}

}
