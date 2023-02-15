package com.com.LeandroCosta.appVendas.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.com.LeandroCosta.appVendas.domain.Categoria;
import com.com.LeandroCosta.appVendas.dto.CategoriaDTO;
import com.com.LeandroCosta.appVendas.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}
@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // pegar id do obj e salvar uri
		return ResponseEntity.created(uri).build();
	}
@RequestMapping(value="/{id}", method = RequestMethod.PUT) 
public ResponseEntity<Void>update(@RequestBody Categoria obj,@PathVariable Integer id){
	obj.setId(id);
	obj= service.update(obj);
	return ResponseEntity.noContent().build();
	}
@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
public ResponseEntity<Void> delete(@PathVariable Integer id) {
	service.delete(id);
	return ResponseEntity.noContent().build();

	}
@RequestMapping(method = RequestMethod.GET) // bsuacr todas categorias  atravez da lista dto 
public ResponseEntity<List<CategoriaDTO>> findAll() {
List<Categoria> list = service.findAll();
List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); // para converter uma lista  para outra 
	return ResponseEntity.ok().body(listDTO);

}
}
