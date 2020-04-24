package com.jyp.kill;

import com.jyp.kill.domain.Item;
import com.jyp.kill.domain.ItemKill;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;
@RunWith(SpringRunner.class)
@SpringBootTest
class KillApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {
        System.out.println(dataSource);
    }

}
