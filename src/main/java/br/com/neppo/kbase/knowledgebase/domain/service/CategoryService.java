package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.dto.CategoryDTO;
import br.com.neppo.kbase.knowledgebase.api.form.CategoryForm;
import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.model.Section;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import br.com.neppo.kbase.knowledgebase.domain.repository.CategoryRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.ResourceAlreadyRegisteredException;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private UserService userService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    public CategoryDTO saveCategory(CategoryForm categoryForm, Long id) {
        User user = userService.selectUser(id);
        Optional<Category> categoryFind = categoryRepository.findByName(categoryForm.getName());
        if (categoryFind.isPresent()) {
            throw new ResourceAlreadyRegisteredException("Category already registered");
        }
        Category category = new Category().convertFromForm(categoryForm);
        category.setCreatedBy(user);
        return new CategoryDTO(categoryRepository.save(category));

    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteById(category.getId());
    }

    public long countCategory() {
        return categoryRepository.count();
    }

    public Page<CategoryDTO> getCategories(Pageable page) {
        Page<Category> categories = categoryRepository.findAll(page);
        return CategoryDTO.convertToPage(categories);
    }

    public Category findCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("Resource not Found");
        }
        return category.get();
    }

    public List<CategoryDTO> getListCategoriesDTO(List<Category> categories) {
        return CategoryDTO.convertToList(categories);
    }

    public List<Category> getListCategories(List<Long> categories) {
        return categoryRepository.findAllById(categories);
    }

}
