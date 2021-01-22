package br.com.neppo.kbase.knowledgebase.domain.service.util;

import br.com.neppo.kbase.knowledgebase.domain.model.Section;

public class SectionUtil {

    public static Section createSection(){
        Section section = new Section();
        section.setId(1L);
        section.setTitle("ExampleSection");
        return section;
    }
}
