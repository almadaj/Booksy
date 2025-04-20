package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.CategoryDTO;
import br.com.booksy.Booksy.domain.model.Category;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.mapper.CategoryMapper;
import br.com.booksy.Booksy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CommonException("Category not found"));
    }

    @Transactional
    public Category save(CategoryDTO categoryDTO) {
        return categoryRepository.save(CategoryMapper.INSTANCE.toCategory(categoryDTO));
    }

    @Transactional
    public Category update(UUID id, CategoryDTO categoryDTO) {
        Category savedCategory = findById(id);
        Category category = CategoryMapper.INSTANCE.toCategory(categoryDTO);
        category.setId(savedCategory.getId());
        return categoryRepository.save(category);
    }

    @Transactional
    public void delete(UUID id) {
        categoryRepository.delete(findById(id));
    }
}
