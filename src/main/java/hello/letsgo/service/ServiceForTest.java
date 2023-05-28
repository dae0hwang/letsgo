package hello.letsgo.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceForTest {

    public int successTest() {
        return 77;
    }

    public int failTest() {
        return 44;
    }
}
