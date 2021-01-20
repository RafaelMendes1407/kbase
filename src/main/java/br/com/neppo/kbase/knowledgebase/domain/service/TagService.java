package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.dto.TagDTO;
import br.com.neppo.kbase.knowledgebase.api.form.TagForm;
import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.model.Tag;
import br.com.neppo.kbase.knowledgebase.domain.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private TagRepository tagRepository;
    private CategoryService categoryService;

    @Autowired
    public TagService(TagRepository tagRepository, CategoryService categoryService){
        this.tagRepository = tagRepository;
        this.categoryService = categoryService;
    }


    public TagDTO createNewTag(TagForm tagForm) {
        Category category = categoryService.findCategory(tagForm.getCategoryId());
        Tag tag = new Tag(tagForm);
        tag.setCreatedBy(category.getCreatedBy());
        tag.setCategory(category);
        return new TagDTO(tagRepository.save(tag));
    }
}
