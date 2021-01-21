package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.dto.CategoryDTO;
import br.com.neppo.kbase.knowledgebase.api.dto.SectionDTO;
import br.com.neppo.kbase.knowledgebase.api.form.SectionForm;
import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.model.Section;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import br.com.neppo.kbase.knowledgebase.domain.repository.SectionRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.ResourceAlreadyRegisteredException;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionService {

    private SectionRepository sectionRepository;
    private CategoryService categoryService;
    private UserService userService;


    @Autowired
    public SectionService(SectionRepository sectionRepository, CategoryService categoryService, UserService userService){
        this.sectionRepository = sectionRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }


    public SectionDTO createSection(SectionForm sectionForm, Long id) {
        User user = userService.selectUser(id);
        Category category = categoryService.findCategory(sectionForm.getCategoryId());
        Optional<Section> sectionFind = sectionRepository.findByTitleAndCategoryId(sectionForm.getTitle(), category.getId());
        if(sectionFind.isPresent()){
            throw new ResourceAlreadyRegisteredException("Section already Registered");
        }
        Section section = new Section(sectionForm);
        section.setCreatedBy(user);
        section.setCategory(category);

        return new SectionDTO().optionalSectionDTO(sectionRepository.save(section));
    }

    public Section getSection(Long idSection){
        Optional<Section> section = sectionRepository.findById(idSection);
        if(section.isEmpty()){
            throw new ResourceNotFoundException("There is no section with this id.");
        }
        return section.get();
    }

    public CategoryDTO getCategorySections(Long id){
        Category category = categoryService.findCategory(id);
        return new CategoryDTO(category);
    }
}
