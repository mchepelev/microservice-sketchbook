package com.sketchbook.coreservice.sync;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

@Service
public class SlowOptionalService {

    public SlowResponse doSlowOperationSingle() {
        return doSlowOperationSingle("");
    }

    public SlowResponse doSlowOperationSingle(String prefix) {
        prefix = StringUtils.isBlank(prefix) ? "[no-prefix]" : prefix;
        try {
            System.out.printf("%s Thread: %s. Message: Slow operation start\n", prefix, Thread.currentThread().getId());
            Thread.sleep(3000);
            System.out.printf("%s Thread: %s. Message: Slow operation finish\n", prefix, Thread.currentThread().getId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SlowResponse response = new SlowResponse(LocalDateTime.now(), "done");
        System.out.printf("%s Thread: %s. Message: Slow operation response. Object: %s\n", prefix, Thread.currentThread().getId(), response);
        return response;
    }

    public Mono<SlowResponse> doSlowMonoOperationSingle(String prefix) {
        prefix = StringUtils.isBlank(prefix) ? "[no-prefix]" : prefix;
        String finalPrefix = prefix;
        return Mono.just(finalPrefix)
                .doOnNext(p -> {System.out.printf("%s Thread: %s. Message: Slow operation start\n", p, Thread.currentThread().getId());})
//                .publishOn(Schedulers.boundedElastic())
                .doOnNext(p -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .doOnNext(p -> {System.out.printf("%s Thread: %s. Message: Slow operation finish\n", p, Thread.currentThread().getId());})
                .thenReturn(new SlowResponse(LocalDateTime.now(), "done"))
                .doOnNext(response -> {System.out.printf("%s Thread: %s. Message: Slow operation response. Object: %s\n", finalPrefix, Thread.currentThread().getId(), response);});
    }

    public SlowResponse doSlowFailure(String prefix) {
        try {
            System.out.printf("%s Thread: %s. Message: Slow failure start\n", prefix, Thread.currentThread().getId());
            Thread.sleep(3000);
            System.out.printf("%s Thread: %s. Message: Slow failure finish\n", prefix, Thread.currentThread().getId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%s Thread: %s. Message: Slow operation failure successful.\n", prefix, Thread.currentThread().getId());
        throw new RuntimeException("smth failed");
    }

    public void doSlowOperationMultiple(Flow.Subscriber<SlowResponse> callback) {
        SubmissionPublisher publisher = new SubmissionPublisher();
        publisher.subscribe(callback);
        for (int i = 0; i < 5; i++) {
            SlowResponse slowResponse = doSlowOperationSingle("operation-" + i);
            publisher.submit(slowResponse);
        }
        System.out.printf("Thread: %s. Message: Slow multiple operation finish\n", Thread.currentThread().getId());
    }

    public Flux<SlowResponse> doFluxOperations() {
/*        return Flux.<SlowResponse>generate(fluxSink -> fluxSink.next(doSlowOperationSingle()))
                .take(5);*/

        return Flux.<SlowResponse>create(fluxSink -> {
            for (int i = 0; i < 5; i++) {
                CompletableFuture.supplyAsync(this::doSlowOperationSingle)
                        .thenAccept(fluxSink::next);
            }
        });

//        single tread. not working
/*        return Flux.<SlowResponse>push(fluxSink -> {
            for (int i = 0; i < 5; i++) {
                CompletableFuture.supplyAsync(this::doSlowOperationSingle)
                        .thenAccept(fluxSink::next);
            }
        });*/
    }
}
