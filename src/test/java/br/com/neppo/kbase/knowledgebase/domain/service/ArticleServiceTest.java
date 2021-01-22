package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.form.ArticleForm;
import br.com.neppo.kbase.knowledgebase.domain.repository.ArticleRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.util.ArticleUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class ArticleServiceTest {

    private ArticleService service;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private UserService userService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private TagService tagService;
    @Mock
    private SectionService sectionService;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.service = new ArticleService(articleRepository,userService,categoryService,tagService,sectionService);
    }

    @Test
    void shouldCreateAnArticle(){
        ArticleForm articleForm = ArticleUtil.gerenateArticleForm();
        service.createNewArticle(articleForm, 1L);
    }
}