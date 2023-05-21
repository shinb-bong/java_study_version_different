package java8;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        runAsync();
        supplyAsync();
        thenApply();
        thenAccept();
        thenRun();
        thenCompose();
        thenCombine();
        allOf();
        anyOf();
        exceptionally(true);
        handle(true);
    }

    /**
     * 반환값이 없는 경우
     * 비동기로 작업 실행 콜
     * 별도의 쓰레드에서 실행됨
     */
    private static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        future.get();
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    /**
     * 반환값이 있는 경우
     * 비동기로 작업 실행 콜
     * 별도의 쓰레드에서 실행됨
     */
    private static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Thread_supply: " + Thread.currentThread().getName();
        });

        System.out.println(future.get());
        System.out.println("Thread = " + Thread.currentThread().getName());
    }
    /**
     * thenApply는 값을 받아서 다른 값을 반환 시켜주는 콜백
     */
    private static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Thread_thenApply: " + Thread.currentThread().getName();
        }).thenApply(s ->{
            return s.toUpperCase();
        });

        System.out.println(future.get());
    }

    /**
     * thenAccept는 반환값을 받아서 사용하고,
     * 값을 반환하지 않는 콜백
     */
    private static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            return "Thread_thenAccept: " + Thread.currentThread().getName();
        }).thenAccept(s ->{
            System.out.println(s.toUpperCase());
        });

        future.get();
    }
    /**
     * thenRun은 반환값 X
     * 그냥 다른 작업
     */
    private static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            return "Thread_thenAccept: " + Thread.currentThread().getName();
        }).thenRun(() ->{
            System.out.println("Thread_Run: "+ Thread.currentThread().getName());
        });

        future.get();
    }
    /**
     * 두 작업이 이어서 실행하도록 조립, 앞선 결과를 받아서 사용가능
     * 함수영 인터페이스로 Fuction을 받음
     */
    private static void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });

        // Future간에 연관 관계가 있는 경우
        CompletableFuture<String> future = hello.thenCompose(CompletableFutureTest::addMsg);
        System.out.println(future.get());
    }

    private static CompletableFuture<String> addMsg(String msg){
        return CompletableFuture.supplyAsync(()->{
            return "thenCompose(): "+msg + " " + "Coodori";
        });
    }
    /**
     * thenCombine 각각의 작업들이 독립적으로 실행,
     * 얻어진 두 결과를 조합해서 작업을 처리
     */
    private static void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });
        CompletableFuture<String> coodori = CompletableFuture.supplyAsync(() -> {
            return "coodori";
        });

        CompletableFuture<String> future = hello.thenCombine(coodori, (h, w) -> "thenCombine(): " + h + " " + w);
        System.out.println(future.get());
    }

    /**
     * allOf() 는 모든 결과에 콜백이 적용된다.
     */
    private static void allOf() throws ExecutionException, InterruptedException {
        System.out.println("====CompletableFutureTest.allOf====");
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });
        CompletableFuture<String> coodori = CompletableFuture.supplyAsync(() -> {
            return "coodori";
        });
        List<CompletableFuture<String>> futures = List.of(hello, coodori);

        CompletableFuture<List<String>> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

        result.get().forEach(System.out::println);
    }

    /**
     * anyOf는 가장 빨리 끝난 작업 한개에 대해서 콜백 실행
     */
    private static void anyOf() throws ExecutionException, InterruptedException {
        System.out.println("====CompletableFutureTest.anyOf====");
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        });

        CompletableFuture<String> coodori = CompletableFuture.supplyAsync(() -> {
            return "coodori";
        });

        // 반환값을 사용하고 반환은 X
        CompletableFuture<Void> future = CompletableFuture.anyOf(hello, coodori).thenAccept(System.out::println);
        future.get();
    }

    /**
     * exceptionally()
     * 예외처리 발생한 에러를 받아서 예외를 처리함.
     * true일 경우 우리가 정의한 예외 메세지를
     * false 일 경우 정상 출력
     */
    private static void exceptionally(boolean doThrow) throws ExecutionException, InterruptedException {
        System.out.println("====CompletableFutureTest.exceptionally====");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (doThrow) {
                throw new IllegalArgumentException("Invalid Argument");
            }

            return "Thread: " + Thread.currentThread().getName();
        }).exceptionally(e -> {
            return e.getMessage();
        });

        System.out.println(future.get());
    }
    /**
     * handle()
     */
    private static void handle(boolean doThrow) throws ExecutionException, InterruptedException {
        System.out.println("====CompletableFutureTest.handle====");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (doThrow) {
                throw new IllegalArgumentException("Invalid Argument");
            }
            return "Thread: " + Thread.currentThread().getName();
        }).handle((result, e) -> {
            return e == null ? result : e.getMessage();
        });
        System.out.println(future.get());
    }

}
