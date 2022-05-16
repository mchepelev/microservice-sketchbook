package com.sketchbook.coreservice.async.javabased;

import com.sketchbook.coreservice.sync.SlowOptionalService;
import com.sketchbook.coreservice.sync.SlowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

@Service
public class AsyncJavaService {

    private SlowOptionalService slowOptionalService;

    @Autowired
    public AsyncJavaService(SlowOptionalService slowOptionalService) {
        this.slowOptionalService = slowOptionalService;
    }

    public String processSingle() {
        CompletableFuture.supplyAsync(slowOptionalService::doSlowOperationSingle)
                .thenAccept(obj -> System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), obj));
        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processSingleErrorOrElse() {
        CompletableFuture.supplyAsync(() -> slowOptionalService.doSlowFailure(""))
                .exceptionally(throwable -> {
                    System.out.printf("Thread: %s. Message: Exception handler. Error: %s\n", Thread.currentThread().getId(), throwable);
                    return SlowResponse.empty(); // return new object if error
                })
                .thenAccept(obj -> System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), obj));
        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processSingleErrorHandle() {
        CompletableFuture.supplyAsync(() -> slowOptionalService.doSlowFailure(""))
                .handle((slowResponse, throwable) -> {
                    if (throwable != null) {
                        System.out.printf("Thread: %s. Message: Exception handler. Error: %s\n", Thread.currentThread().getId(), throwable);
                    } else {
                        System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), slowResponse);
                    }
                    return slowResponse;
                });
        return "sync response";
    }

    public String processSingleErrorAwait() {
        try {
            CompletableFuture.supplyAsync(() -> slowOptionalService.doSlowFailure(""))
                    .join();
        } catch (Exception e) {
            System.out.printf("Thread: %s. Message: Exception handler. Error: %s\n", Thread.currentThread().getId(), e);
        }

        return "sync response";
    }

    public String processMultiple() {
        CompletableFuture<SlowResponse> completableFuture1 = CompletableFuture.supplyAsync(() -> slowOptionalService.doSlowOperationSingle("operation-1"));
        System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), completableFuture1);
        CompletableFuture<SlowResponse> completableFuture2 = CompletableFuture.supplyAsync(() -> slowOptionalService.doSlowOperationSingle("operation-2"));
        System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), completableFuture2);

        CompletableFuture.allOf(completableFuture1, completableFuture2)
                .thenApply(unused -> {
                    try {
                        return Arrays.asList(completableFuture1.get(), completableFuture2.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .thenAccept(slowResponses -> System.out.printf("Thread: %s. Message: Multiple slow responses. Object: %s\n", Thread.currentThread().getId(), slowResponses));

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processMultipleAwait() {
        CompletableFuture<SlowResponse> completableFuture1 = CompletableFuture.supplyAsync(() -> slowOptionalService.doSlowOperationSingle("operation-1"));
        System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), completableFuture1);
        CompletableFuture<SlowResponse> completableFuture2 = CompletableFuture.supplyAsync(() -> slowOptionalService.doSlowOperationSingle("operation-2"));
        System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), completableFuture2);

        CompletableFuture.allOf(completableFuture1, completableFuture2).join();
        List<SlowResponse> slowResponses = null;
        try {
            slowResponses = Arrays.asList(completableFuture1.get(), completableFuture2.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Thread: %s. Message: Multiple slow responses. Object: %s\n", Thread.currentThread().getId(), slowResponses);

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processCollection() {
        Flow.Subscriber subscriber = new Flow.Subscriber() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.printf("Thread: %s. Message: Slow operation result processor `subscribe()`.\n", Thread.currentThread().getId());
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Object item) {
                System.out.printf("Thread: %s. Message: Slow operation result processor `next()`. Object: %s\n", Thread.currentThread().getId(), item);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.printf("Thread: %s. Message: Slow operation result processor `complete()`.\n", Thread.currentThread().getId());
            }
        };

        Executors.newSingleThreadExecutor().execute(() -> slowOptionalService.doSlowOperationMultiple(subscriber));

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }
}
