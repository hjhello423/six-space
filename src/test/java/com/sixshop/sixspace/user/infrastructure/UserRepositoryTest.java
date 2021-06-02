package com.sixshop.sixspace.user.infrastructure;

import com.sixshop.sixspace.user.domain.User;
import com.sixshop.sixspace.user.presentation.dto.UserCreateRequest;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @DisplayName("유저 저장")
    @Test
    void save() {
        // given
        UserCreateRequest dto = new UserCreateRequest();
        dto.setId("hongjun");
        dto.setPassword("123");

        User user = User.of(dto);
        em.persist(user);

        // when
        userRepository.save(user);
        User target = userRepository.findById("hongjun")
            .get();

        // then
        Assertions.assertThat(user).isEqualTo(target);
    }

}