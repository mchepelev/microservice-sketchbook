package com.sketchbook.coreservice.async.projectreactor;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class AsyncProjectreactorServiceTest {

    @Autowired
    private AsyncProjectreactorService service;

    @Test
    void processSingleFromFuture() throws InterruptedException {
        service.processSingleFromFuture();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processSingleJust() throws InterruptedException {
        service.processSingleJust();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processSingleDefer() throws InterruptedException {
        service.processSingleDefer();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processMultipleSubscribersJust() throws InterruptedException {
        service.processMultipleSubscribersJust();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processMultipleSubscribersDefer() throws InterruptedException {
        service.processMultipleSubscribersDefer();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processSingleMonoMethod() throws InterruptedException {
        service.processSingleMonoMethod();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processSingleMonoMethodBlock() throws InterruptedException {
        service.processSingleMonoMethodBlock();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processMultipleAsync() throws InterruptedException {
        service.processMultipleAsync();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processMultipleAwaitToBuildComplexResponse() throws InterruptedException {
        service.processMultipleAwaitToBuildComplexResponse();

        System.out.printf("[TEST] Thread: %s. Message: TEST WAIT.\n", Thread.currentThread().getId());
        Thread.sleep(60000);
        assertTrue(true);
    }

    @Test
    void processErrorInSubscriber() throws InterruptedException {
        service.processErrorInSubscriber();

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