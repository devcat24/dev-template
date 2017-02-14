package com.github.devcat24.mvc.entity;

import com.github.devcat24.mvc.entity.fi.Item01;
import com.github.devcat24.mvc.entity.hr.Emp01;
import com.github.devcat24.mvc.repo.fi.Item01Repo;
import com.github.devcat24.mvc.repo.hr.Emp01Repo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//    -> Currently(02/2017) '@DataJpaTest' has some issue with 'ApplicationContext' loading.
//       Do not use '@DataJpaTest' with '@SpringBootTest'
//        - https://github.com/spring-projects/spring-boot/issues/5776
@DataJpaTest
//    -> Designed for 'ERD/Entity/JPA validation', does not load with '@SpringBootTest'
//    -> Supports for rollback after test
//    -> Use in memory database(H2) instead of 'project-configured database' configuration
//         : which means '@DataJpaTest' should not be loaded 'ApplicationContext' simultaneously!
//    -> To test on 'project-configured database', use '@AutoConfigureTestDatabase(replace=Replace.NONE)'
//    -> '@JdbcTest' annotation can be used to test pure jdbc-related test on in memory database,
//       but, native SQLs are usually dependant to specific database -> might be 'not so useful'
public class Emp01Test {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private Item01Repo item01Repo;

    @Autowired
    private Emp01Repo emp01Repo;


    @Test
    public void createObjects(){
        Emp01 e01 = new Emp01(1002, "JaneSmith", "Sales", 2000);
        //emp01Repo.saveAndFlush(e01);
        this.entityManager.persist(e01);

        Item01 i01 = new Item01(2002, "RAM");

        item01Repo.saveAndFlush(i01);
        //this.entityManager.persist(i01); // -> works with '@DataJpaTest'

        Emp01 e = emp01Repo.findByEmpno(1002);
        assertThat(e.getEname()).isEqualTo("JaneSmith");
        assertThat(e.getMgr()).isGreaterThan(1100);
    }

}
