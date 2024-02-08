package dev.maxnguyen.EcommerceSiteBE.service;

import dev.maxnguyen.EcommerceSiteBE.domain.Category;
import dev.maxnguyen.EcommerceSiteBE.exception.CategoryException;
import dev.maxnguyen.EcommerceSiteBE.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public Category save(Category entity) {
        return categoryRepository.save(entity);
    }

    public Category update(Long id, Category entity) {

        Optional<Category> existed = categoryRepository.findById(id);

        if(existed.isEmpty()){
            throw new CategoryException("Category id " + id + " does not exist");
        }

        try{
            Category existedCategory = existed.get();

            existedCategory.setName(entity.getName());
            existedCategory.setStatus(entity.getStatus());

            categoryRepository.save(existedCategory);

        } catch (Exception ex){
            throw new CategoryException("Category is updated fail");
        }

        return categoryRepository.save(entity);
    }
}
