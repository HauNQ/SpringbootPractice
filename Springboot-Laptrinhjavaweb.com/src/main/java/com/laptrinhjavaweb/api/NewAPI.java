package com.laptrinhjavaweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.api.output.NewOutput;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;


@RestController
public class NewAPI {

	
	@Autowired
	private INewService newservice;
	
	@PostMapping(value = "/new")
	public NewDTO createNew(@RequestBody NewDTO model) {
		return newservice.save(model);
	}
	
	@GetMapping(value = "/new")
	public NewOutput showNew(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		NewOutput result = new NewOutput();
		result.setPage(page);
		result.setListResult(newservice.findAll(new PageRequest(page-1, limit)));
		result.setTotalPage((int)Math.ceil((double) newservice.totalItem()/limit));
		return result;
	}
	
	@PutMapping(value = "/new/{id}")
	public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable Long id) {
		model.setId(id);
		return newservice.save(model);
	}
	
	@DeleteMapping(value = "/new")
	public void deleteNew(@RequestBody Long[] ids) {
		newservice.delete(ids);
	}
	
}