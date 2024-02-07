package dev.maxnguyen.EcommerceSiteBE.service;

import dev.maxnguyen.EcommerceSiteBE.domain.Category;
import dev.maxnguyen.EcommerceSiteBE.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public Category save(Category entity) {
        return categoryRepository.save(entity);
    }
}
