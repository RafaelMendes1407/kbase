package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.dto.CategoryDTO;
import br.com.neppo.kbase.knowledgebase.api.dto.UserDTO;
import br.com.neppo.kbase.knowledgebase.api.form.CategoryForm;
import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import br.com.neppo.kbase.knowledgebase.domain.repository.CategoryRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    public Category saveCategory(CategoryForm categoryForm){
        Optional<User> user = userService.selectUser(categoryForm.getUserId());
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        Category category = modelMapper.map(categoryForm, Category.class);
        category.setCreatedBy(user.get());
        CategoryDTO categoryDTO = new CategoryDTO().convertTo(category);
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category){
        categoryRepository.deleteById(category.getId());
    }

    public long countCategory(){
        return categoryRepository.count();
    }

}
