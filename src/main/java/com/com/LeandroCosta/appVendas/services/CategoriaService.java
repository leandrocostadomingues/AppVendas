package com.com.LeandroCosta.appVendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.com.LeandroCosta.appVendas.domain.Categoria;
import com.com.LeandroCosta.appVendas.dto.CategoriaDTO;
import com.com.LeandroCosta.appVendas.repositories.CategoriaRepository;
import com.com.LeandroCosta.appVendas.services.exceptions.DataIntegrityException;
import com.com.LeandroCosta.appVendas.services.exceptions.ObjectNotFoundException;

@Service // classe de serviço
public class CategoriaService {
	@Autowired // dependecia
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);

	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não e Possível excluir uma Categoria Que possui produtos");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();

	}

	public Page<Categoria> findPage(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		// metodo para paginação
	}

	public Categoria fromDto(CategoriaDTO objDto) {
		// para instaciar uma categoria apartir dto
		return new Categoria(objDto.getId(), objDto.getNome());

	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setnome(obj.getnome());

	}
}
