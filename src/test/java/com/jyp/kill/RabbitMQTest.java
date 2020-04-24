package com.jyp.kill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void send() {
//        rabbitTemplate.send("","","");
        Map<String, Object> map = new HashMap<>();
        map.put("message", "rabbitmqtest");
        rabbitTemplate.convertAndSend("test.kill.item.success.kill.dead.prod.exchange", "test.kill.item.success.kill.dead.prod.routing.key", map);
    }

    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert("test.kill.item.success.kill.dead.queue");
        System.out.println(o);
    }


}
