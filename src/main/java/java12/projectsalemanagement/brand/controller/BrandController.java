package java12.projectsalemanagement.brand.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java12.projectsalemanagement.brand.dto.CreateBrandDto;
import java12.projectsalemanagement.brand.dto.UpdateBrandDto;
import java12.projectsalemanagement.brand.service.BrandService;
import java12.projectsalemanagement.common.util.ResponseHandler;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

	private BrandService service;

	@Autowired
	public BrandController(@Qualifier("brandServiceImpl") BrandService service) {
		this.service = service;
	}

	@PostMapping
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> createBrand(@Valid @RequestBody CreateBrandDto dto, BindingResult errors) {
		try {
			if (errors.hasErrors())
				return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

			return service.createBrand(dto);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", 500);
			map.put("message", "INTERNAL_SERVER_ERROR");
			return ResponseHandler.getResponse(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping
	public ResponseEntity<Object> findAllBrand() {

		return ResponseEntity.status(HttpStatus.OK)
				.body(service.findAllDto());
	}

	@DeleteMapping(value = "/{id}")
	@Secured("ROLE_ADMIN")
	public Object deleteBrand(@PathVariable long id) {

		return service.deleteBrand(id);
	}

	@GetMapping(value = "/{id}")
	public Object findBrandById(@PathVariable long id) {

		return service.findBrandById(id);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateBrand(@PathVariable long id, @RequestBody UpdateBrandDto dto){
		
		try {
			return service.updateBrand(id, dto);
		} catch (Exception e) {
	 Map<String, Object> responseCommon = ResponseHandler.ResponseCommon(500, "INTERNAL_SERVER_ERROR", false);
	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCommon);
			
		}
	}
}
