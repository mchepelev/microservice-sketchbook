package com.sketchbook.coreservice.async.projectreactor;

import com.sketchbook.coreservice.sync.SlowOptionalService;
import com.sketchbook.coreservice.sync.SlowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncProjectreactorService {

    private SlowOptionalService slowOptionalService;

    @Autowired
    public AsyncProjectreactorService(SlowOptionalService slowOptionalService) {
        this.slowOptionalService = slowOptionalService;
    }

    public String processSingleFromFuture() {
        System.out.printf("Thread: %s.\n", Thread.currentThread().getId());
        Mono<SlowResponse> slowResponseMono = Mono.fromFuture(CompletableFuture.supplyAsync(slowOptionalService::doSlowOperationSingle)); // -- async

        System.out.println("mono created");
        slowResponseMono.subscribe(obj -> {
            /*try {
                System.out.println("sleep");
                Thread.sleep(3000);
                System.out.println("wake up");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), obj);
        });
        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processSingleJust() {
        System.out.printf("Thread: %s.\n", Thread.currentThread().getId());
        Mono<SlowResponse> slowResponseMono = Mono.just(slowOptionalService.doSlowOperationSingle());

        System.out.println("mono created");
        slowResponseMono.subscribe(obj -> {
            /*try {
                System.out.println("sleep");
                Thread.sleep(3000);
                System.out.println("wake up");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), obj);
        });
        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processSingleDefer() {
        System.out.printf("Thread: %s.\n", Thread.currentThread().getId());
        Mono<SlowResponse> slowResponseMono = Mono.defer(() -> Mono.just(slowOptionalService.doSlowOperationSingle()));

        System.out.println("mono created");
        slowResponseMono.subscribe(obj -> {
            /*try {
                System.out.println("sleep");
                Thread.sleep(3000);
                System.out.println("wake up");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), obj);
        });
        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processMultipleSubscribersJust() {
        Mono<SlowResponse> slowResponseMono = Mono.just(slowOptionalService.doSlowOperationSingle());
        System.out.println("mono created");
        slowResponseMono.subscribe(obj -> {
            System.out.printf("Thread: %s. Message: Slow operation result processor 1. Object: %s\n", Thread.currentThread().getId(), obj);
        });
        slowResponseMono.subscribe(obj -> {
            System.out.printf("Thread: %s. Message: Slow operation result processor 2. Object: %s\n", Thread.currentThread().getId(), obj);
        });

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processMultipleSubscribersDefer() {
        Mono<SlowResponse> slowResponseMono = Mono.defer(() -> Mono.just(slowOptionalService.doSlowOperationSingle()));
        System.out.println("mono created");
        slowResponseMono.subscribe(obj -> {
            System.out.printf("Thread: %s. Message: Slow operation result processor 1. Object: %s\n", Thread.currentThread().getId(), obj);
        });
        slowResponseMono.subscribe(obj -> {
            System.out.printf("Thread: %s. Message: Slow operation result processor 2. Object: %s\n", Thread.currentThread().getId(), obj);
        });

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processSingleMonoMethod() {
        System.out.printf("Thread: %s.\n", Thread.currentThread().getId());
        Mono<SlowResponse> slowResponseMono = slowOptionalService.doSlowMonoOperationSingle("operation-1 -");
        System.out.println("mono created");
        slowResponseMono
                .subscribeOn(Schedulers.boundedElastic()) // -- converts sync mono to async
                .subscribe(obj -> {
                    System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), obj);
                });

        System.out.printf("Thread: %s. Message: Non-blocking main process.\n", Thread.currentThread().getId());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Thread: %s. Message: Non-blocking main process Iteration: %d.\n", Thread.currentThread().getId(), i);
        }

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processSingleMonoMethodBlock() {
        System.out.printf("Thread: %s.\n", Thread.currentThread().getId());
        Mono<SlowResponse> slowResponseMono = slowOptionalService.doSlowMonoOperationSingle("operation-1 -");
        System.out.println("mono created");

//        Mono<SlowResponse> asyncMono = Mono.fromFuture(CompletableFuture.supplyAsync(slowResponseMono::block));
        Mono<SlowResponse> asyncMono = Mono.defer(() -> Mono.fromFuture(CompletableFuture.supplyAsync(slowResponseMono::block)));

        System.out.printf("Thread: %s. Message: Non-blocking main process.\n", Thread.currentThread().getId());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Thread: %s. Message: Non-blocking main process Iteration: %d.\n", Thread.currentThread().getId(), i);
        }

        System.out.printf("Thread: %s. Message: Main waiting for response.\n", Thread.currentThread().getId());
        SlowResponse awaitResponse = asyncMono.block(); // -- await
        System.out.printf("Thread: %s. Message: Main get response. Object: %s\n", Thread.currentThread().getId(), awaitResponse);

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processMultipleAsync() {
        Mono<SlowResponse> slowOperationMono1 = Mono.defer(() -> Mono.fromFuture(CompletableFuture.supplyAsync(() -> slowOptionalService.doSlowOperationSingle("operation-1 -"))));
        System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), slowOperationMono1);
        Mono<SlowResponse> slowOperationMono2 = Mono.defer(() -> Mono.fromFuture(CompletableFuture.supplyAsync(() -> slowOptionalService.doSlowOperationSingle("operation-2 -"))));
        System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), slowOperationMono2);

        Mono.zip(slowOperationMono1, slowOperationMono2, (slowResponse1, slowResponse2) -> {
            System.out.printf("Thread: %s. Message: Combine multiple monos.\n", Thread.currentThread().getId());
            return Arrays.asList(slowResponse1, slowResponse2);
        }).subscribe(slowResponses -> {
            System.out.printf("Thread: %s. Message: Combine multiple monos. Object: %s\n", Thread.currentThread().getId(), slowResponses);
        });

/*        Mono.just(new ArrayList<SlowResponse>())
                .zipWith(slowOperationMono1, (slowResponses, slowResponse) -> {
                    slowResponses.add(slowResponse);
                    return slowResponses;
                })
                .zipWith(slowOperationMono2, (slowResponses, slowResponse) -> {
                    slowResponses.add(slowResponse);
                    return slowResponses;
                })
                .subscribe(slowResponses -> {
                    System.out.printf("Thread: %s. Message: Combine multiple monos. Object: %s\n", Thread.currentThread().getId(), slowResponses);
                });*/

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processMultipleAwaitToBuildComplexResponse() {
        System.out.printf("Thread: %s.\n", Thread.currentThread().getId());
        Mono<SlowResponse> slowResponseMono1 = slowOptionalService.doSlowMonoOperationSingle("operation-1 -");
        Mono<SlowResponse> slowResponseMono2 = slowOptionalService.doSlowMonoOperationSingle("operation-2 -");
        System.out.println("mono created");

//        Mono<SlowResponse> asyncMono1 = Mono.fromFuture(CompletableFuture.supplyAsync(slowResponseMono1::block));
//        Mono<SlowResponse> asyncMono2 = Mono.fromFuture(CompletableFuture.supplyAsync(slowResponseMono2::block));
        Mono<SlowResponse> asyncMono1 = Mono.defer(() -> Mono.fromFuture(CompletableFuture.supplyAsync(slowResponseMono1::block)));
        Mono<SlowResponse> asyncMono2 = Mono.defer(() -> Mono.fromFuture(CompletableFuture.supplyAsync(slowResponseMono2::block)));

        System.out.printf("Thread: %s. Message: Non-blocking main process.\n", Thread.currentThread().getId());
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Thread: %s. Message: Non-blocking main process Iteration: %d.\n", Thread.currentThread().getId(), i);
        }

        ArrayList<SlowResponse> slowResponses = new ArrayList<>();

        System.out.printf("Thread: %s. Message: Main waiting for response.\n", Thread.currentThread().getId());
        SlowResponse awaitResponse1 = asyncMono1.block(); // -- await
        System.out.printf("Thread: %s. Message: Main get response. Object: %s\n", Thread.currentThread().getId(), awaitResponse1);
        SlowResponse awaitResponse2 = asyncMono2.block(); // -- await
        System.out.printf("Thread: %s. Message: Main get response. Object: %s\n", Thread.currentThread().getId(), awaitResponse2);

        slowResponses.add(awaitResponse1);
        slowResponses.add(awaitResponse2);
        System.out.printf("Thread: %s. Message: Combine multiple monos. Object: %s\n", Thread.currentThread().getId(), slowResponses);

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processErrorInSubscriber() {
        Mono.create(monoSink -> {
            try {
                SlowResponse slowResponse = slowOptionalService.doSlowFailure("operation-1 -");
                monoSink.success(slowResponse);
            } catch (Exception e) {
                monoSink.error(e);
            }
        })
        /*Mono.just(slowOptionalService.doSlowFailure("operation-1 -"))
                .onErrorResume(Mono::error)*/
                .doOnNext(r -> System.out.printf("Thread: %s. Message: Operation 1 success. Object: %s\n", Thread.currentThread().getId(), r))
                .doOnError(r -> System.out.printf("Thread: %s. Message: Operation 1 error. Object: %s\n", Thread.currentThread().getId(), r))
                .map(slowResponse -> slowOptionalService.doSlowOperationSingle("operation-2 -"))
                .onErrorResume(Mono::error)
                .doOnNext(r -> System.out.printf("Thread: %s. Message: Operation 2 success. Object: %s\n", Thread.currentThread().getId(), r))
                .doOnError(r -> System.out.printf("Thread: %s. Message: Operation 2 error. Object: %s\n", Thread.currentThread().getId(), r))
                .subscribe(r -> {
                    System.out.printf("Thread: %s. Message: Success subsciber. Object: %s\n", Thread.currentThread().getId(), r);
                }, throwable -> {
                    System.out.printf("Thread: %s. Message: General error.\n", Thread.currentThread().getId());
                    throw new RuntimeException("general error");
                });

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }

    public String processCollection() {
        slowOptionalService.doFluxOperations()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(item -> {System.out.printf("Thread: %s. Message: Slow operation result processor. Object: %s\n", Thread.currentThread().getId(), item);});

        System.out.printf("Thread: %s. Message: General sync process finished.\n", Thread.currentThread().getId());
        return "sync response";
    }
}
