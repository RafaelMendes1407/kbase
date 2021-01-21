package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.dto.TagDTO;
import br.com.neppo.kbase.knowledgebase.api.form.TagForm;
import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.model.Tag;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import br.com.neppo.kbase.knowledgebase.domain.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private TagRepository tagRepository;
    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public TagService(TagRepository tagRepository, CategoryService categoryService, UserService userService){
        this.tagRepository = tagRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }


    public TagDTO createNewTag(TagForm tagForm, Long id) {
        User user = userService.selectUser(id);
        Category category = categoryService.findCategory(tagForm.getCategoryId());
        Tag tag = new Tag(tagForm);
        tag.setCreatedBy(category.getCreatedBy());
        tag.setCategory(category);
        return new TagDTO(tagRepository.save(tag));
    }

    public List<Tag> getListTags(List<Long> tags) {
        return tagRepository.findAllById(tags);
    }

    public List<TagDTO> getTags(){
        return TagDTO.convertListTagToListDTO(tagRepository.findAll());
    }
}
