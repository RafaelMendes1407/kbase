package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.dto.CategoryDTO;
import br.com.neppo.kbase.knowledgebase.api.form.CategoryForm;
import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import br.com.neppo.kbase.knowledgebase.domain.repository.CategoryRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.ResourceAlreadyRegisteredException;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserService userService;

    public CategoryDTO saveCategory(CategoryForm categoryForm){
        Optional<User> user = userService.selectUser(categoryForm.getUserId());
        Optional<Category> categoryFind = categoryRepository.findByName(categoryForm.getName());
        if(categoryFind.isPresent()){
            throw new ResourceAlreadyRegisteredException("Category already registered");
        }
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        Category category = new Category().convertFromForm(categoryForm);
        category.setCreatedBy(user.get());

        return new CategoryDTO(categoryRepository.save(category));

    }

    public void deleteCategory(Category category){
        categoryRepository.deleteById(category.getId());
    }

    public long countCategory(){
        return categoryRepository.count();
    }

    public Page<CategoryDTO> getCategories(Pageable page) {
        Page<Category> categories = categoryRepository.findAll(page);
        return CategoryDTO.convertToPage(categories);
    }
}
