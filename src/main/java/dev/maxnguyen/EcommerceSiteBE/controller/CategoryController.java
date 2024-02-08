package dev.maxnguyen.EcommerceSiteBE.controller;

import dev.maxnguyen.EcommerceSiteBE.domain.Category;
import dev.maxnguyen.EcommerceSiteBE.dto.CategoryDto;
import dev.maxnguyen.EcommerceSiteBE.service.CategoryService;
import dev.maxnguyen.EcommerceSiteBE.service.MapValidationErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto dto,
                                            BindingResult result){
        ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationField(result);

        if(responseEntity !=null){
            return responseEntity;
        }

        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);

        entity = categoryService.save(entity);

        dto.setId(entity.getId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id,
                                            @RequestBody CategoryDto dto){
        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);

        entity = categoryService.update(id,entity);

        dto.setId(entity.getId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getCategory(
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable){
        return new ResponseEntity<>(categoryService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<?> getCategory(@PathVariable("id") Long id){
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }
}
