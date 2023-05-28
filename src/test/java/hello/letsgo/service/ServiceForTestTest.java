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
    ServiceForTest serviceForTest;
    @Test
    void successTest() {
        int result = serviceForTest.successTest();
        Assertions.assertEquals(result, 77);
    }

    @Test
    void failTest() {
        int result = serviceForTest.failTest();
        Assertions.assertEquals(result, 77);
    }
}