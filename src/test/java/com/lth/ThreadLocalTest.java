package com.lth;

import org.junit.jupiter.api.Test;


public class ThreadLocalTest {

    @Test
    public void testThreadLocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hello");
        System.out.println(threadLocal.get());
    }

}
