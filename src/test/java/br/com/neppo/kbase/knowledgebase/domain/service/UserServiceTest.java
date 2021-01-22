package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.form.UserForm;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import br.com.neppo.kbase.knowledgebase.domain.repository.UserRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.EmailAlreadyRegisteredException;
import br.com.neppo.kbase.knowledgebase.domain.service.util.UserUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
class UserServiceTest {

    private UserService service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileService profileService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.service = new UserService(userRepository, profileService);
    }

    @Test
    void shouldCreateANewUser(){
        UserForm userForm = UserUtil.userForm();
        User userSaved = UserUtil.createUser();
        Mockito.when(userRepository.findByEmail(userForm.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(userSaved)).thenReturn(userSaved);
        userSaved = service.saveUser(userForm);
        Mockito.verify(userRepository).save(userCaptor.capture());
        User user = userCaptor.getValue();
        Assertions.assertThat(user.getPassword().length() > 20);
    }

    @Test
    void shouldNotCreateANewUserWithAnAlreadyRegisteredEmail(){
        UserForm userForm = UserUtil.userForm();
        User userSaved = UserUtil.createUser();
        Mockito.when(userRepository.findByEmail(userForm.getEmail())).thenReturn(Optional.of(userSaved));

        assertThrows(EmailAlreadyRegisteredException.class, () -> {
            service.saveUser(userForm);
        });
    }
 }