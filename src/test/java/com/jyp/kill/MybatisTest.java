package com.jyp.kill;

import com.jyp.kill.dao.ItemKillMapper;
import com.jyp.kill.dao.ItemKillSuccessMapper;
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
public class MybatisTest {

    @Autowired
    DataSource dataSource;
    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;
    @Autowired
    ItemKillMapper itemKillMapper;

    @Test
    public void contextload() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void select() {
        System.out.println(itemKillMapper.selectAll());
    }

    @Test
    public void countbyuser(){
//        System.out.println(itemKillSuccessMapper.countByKillUserId());
    }


}
