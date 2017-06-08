package io.github.xudaojie.jdoc.controller;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xdj on 2017/6/4.
 */
//@RunWith(SpringJUnit4ClassRunner.class) SpringRunner extends SpringJUnit4ClassRunner
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @Autowired
    private AccountController mController;

    @Test
    public void login() throws Exception {
        Assert.assertNotNull(mController); // JUnit
        Assertions.assertThat(mController).isNotNull(); // AssertJ
    }

}