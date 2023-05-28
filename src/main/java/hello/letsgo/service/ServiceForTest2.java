package hello.letsgo.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceForTest2 {

    public int successTest2() {
        return 77;
    }

    public int failTest2() {
        return 44;
    }
}
