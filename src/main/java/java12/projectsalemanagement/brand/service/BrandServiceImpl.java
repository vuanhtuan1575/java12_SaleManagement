package java12.projectsalemanagement.brand.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java12.projectsalemanagement.brand.dto.BrandDto;
import java12.projectsalemanagement.brand.dto.CreateBrandDto;
import java12.projectsalemanagement.brand.entity.Brand;
import java12.projectsalemanagement.brand.repository.BrandRepository;
import java12.projectsalemanagement.common.util.ResponseHandler;

@Service
public class BrandServiceImpl implements BrandService  {
	private BrandRepository repository;
	
	 @Autowired
	    public BrandServiceImpl(BrandRepository brandRepository) {
	        repository = brandRepository;
	        //encoder = passwordEncoder;
	    }

	@Override
	public Brand createBrand(CreateBrandDto dto) {
		 Brand newBrand= new Brand();
		 newBrand.setBrandName(dto.getBrandName());
		 newBrand.setDescription(dto.getDescription());
		 newBrand.setUrlImage(dto.getUrlImage());
	        return repository.save(newBrand);
	}

	@Override
	public List<BrandDto> findAllDto() {
		return repository.findAllBrandDto();
		
	}

	@Override
	public Object deleteBrand(long id) {
		Optional<Brand> opBrand = repository.findById(id);
		if(opBrand.isEmpty()) {
			return ResponseHandler.getErrors("Brand not exist", HttpStatus.NOT_FOUND);
		}
		repository.delete(opBrand.get());
		return ResponseHandler.getResponse("delete success", HttpStatus.OK);
	}

	@Override
	public Object findBrandById(long id) {
		
		Optional<BrandDto> opBrandDto = repository.findBrandById(id);
		if(opBrandDto.isEmpty()) {
			return ResponseHandler.getErrors("Brand not exist", HttpStatus.NOT_FOUND);
		}
		return ResponseHandler.getResponse(opBrandDto.get(), HttpStatus.OK);
		
	}
	 
	 
}
