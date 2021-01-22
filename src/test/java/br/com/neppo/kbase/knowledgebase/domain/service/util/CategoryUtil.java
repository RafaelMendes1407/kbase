package br.com.neppo.kbase.knowledgebase.domain.service.util;

import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.model.User;

import java.util.ArrayList;

public class CategoryUtil {
    public static Category createCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("ExampleCategory");
        category.setCreatedBy(UserUtil.createUser());
        return category;
    }
}
