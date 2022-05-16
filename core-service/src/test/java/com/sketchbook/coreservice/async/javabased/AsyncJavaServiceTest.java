package com.sketchbook.coreservice.async.javabased;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class AsyncJavaServiceTest {
    @Autowired
    private AsyncJavaService service;

    @Test
    void processSingle() throws InterruptedException {
        service.processSingle();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processSingleErrorOrElse() throws InterruptedException {
        service.processSingleErrorOrElse();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processSingleErrorHandle() throws InterruptedException {
        service.processSingleErrorHandle();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processSingleErrorAwait() throws InterruptedException {
        service.processSingleErrorAwait();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processMultiple() throws InterruptedException {
        service.processMultiple();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processMultipleAwait() throws InterruptedException {
        service.processMultipleAwait();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processCollection() throws InterruptedException {
        service.processCollection();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }
}