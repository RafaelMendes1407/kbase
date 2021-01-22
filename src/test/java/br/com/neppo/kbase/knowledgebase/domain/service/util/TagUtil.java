package br.com.neppo.kbase.knowledgebase.domain.service.util;

import br.com.neppo.kbase.knowledgebase.domain.model.Tag;

public class TagUtil {
    public Tag createTag(){
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setCreatedBy(UserUtil.createUser());
        tag.setTitle("Example");
        tag.setCategory(CategoryUtil.createCategory());
        return tag;
    }
}
