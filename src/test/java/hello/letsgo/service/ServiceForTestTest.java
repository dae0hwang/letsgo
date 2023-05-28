package hello.letsgo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
class ServiceForTestTest {

    @Autowired
    ServiceForTest2 serviceForTest2;
    @Test
    void successTest() {
        int result = serviceForTest2.successTest2();
        Assertions.assertEquals(result, 77);
    }

    @Test
    void failTest() {
        int result = serviceForTest2.failTest2();
        Assertions.assertEquals(result, 77);
    }
}