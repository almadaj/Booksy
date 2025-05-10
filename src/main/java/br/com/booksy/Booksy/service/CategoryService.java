package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.CategoryDTO;
import br.com.booksy.Booksy.domain.dto.CategoryResponseDTO;
import br.com.booksy.Booksy.domain.model.Category;
import br.com.booksy.Booksy.exception.CommonException;
import br.com.booksy.Booksy.domain.mapper.CategoryMapper;
import br.com.booksy.Booksy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper.INSTANCE::toCategoryResponseDTO)
                .collect(Collectors.toList());
    }

    public CategoryResponseDTO findById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "booksy.category.findById.notFound","Category not found"));
        return CategoryMapper.INSTANCE.toCategoryResponseDTO(category);
    }

    public Category findCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CommonException(HttpStatus.NOT_FOUND, "booksy.category.findById.notFound","Category not found"));
    }

    @Transactional
    public CategoryResponseDTO save(CategoryDTO categoryDTO) {
        Category savedCategory = categoryRepository.save(CategoryMapper.INSTANCE.toCategory(categoryDTO));
        return CategoryMapper.INSTANCE.toCategoryResponseDTO(savedCategory);
    }

    @Transactional
    public CategoryResponseDTO update(UUID id, CategoryDTO categoryDTO) {
        Category savedCategory = findCategoryById(id);
        Category category = CategoryMapper.INSTANCE.toCategory(categoryDTO);
        category.setId(savedCategory.getId());
        Category updatedCategory =  categoryRepository.save(category);
        return CategoryMapper.INSTANCE.toCategoryResponseDTO(updatedCategory);
    }

    @Transactional
    public void delete(UUID id) {
        categoryRepository.delete(findCategoryById(id));
    }

    public Set<Category> findAllByIds(Set<UUID> ids) {
        Set<Category> categories = categoryRepository.findAllByIdIn(ids);
        if (categories.size() != ids.size()) {
            throw new CommonException(HttpStatus.NOT_FOUND, "booksy.category.findAllByIds.notFound","Category not found");
        }
        return categories;
    }
}
