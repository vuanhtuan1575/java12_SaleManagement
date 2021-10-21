package java12.projectsalemanagement.brand.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java12.projectsalemanagement.brand.dto.BrandDto;
import java12.projectsalemanagement.brand.dto.CreateBrandDto;
import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.brand.service.BrandService;
import java12.projectsalemanagement.common.util.ResponseHandler;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    
	 private  BrandService service;
	 @Autowired
	    public BrandController(@Qualifier("brandServiceImpl") BrandService service) {
	        this.service = service;
	    }
	 
	 @PostMapping
	    public Object createBrand(@Valid @RequestBody CreateBrandDto dto,
	                             BindingResult errors) {
	        if (errors.hasErrors())
	            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

	        Brand newBrand = service.createBrand(dto);

	        return ResponseHandler.getResponse(newBrand, HttpStatus.OK);
	    }
	 @GetMapping
		public Object findAllBrand() {
			List<BrandDto> brands = service.findAllDto();
			
			return ResponseHandler.getResponse(brands, HttpStatus.OK);
		}
	 @DeleteMapping(value = "/{id}")
	 public Object deleteBrand(@PathVariable long id) {
		 
		 return service.deleteBrand(id);
	 }
	 
	 @GetMapping(value = "/{id}")
	 public Object findBrandById(@PathVariable long id) {
		 
		 return service.findBrandById(id);
	 }
}
