package com.example.concurrency.examples.AtomicClass;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AtomicClickCountServiceTest {

    @DisplayName("Atomic: Click 횟수만큼 click-count 가 증가한다.")
    @Test
    void atomic_click() throws InterruptedException {
        // given
        final int clickCount = 10000;
        AtomicClickCountService service = new AtomicClickCountService();
        ExecutorService executor = Executors.newFixedThreadPool(50);
        CountDownLatch executorCount = new CountDownLatch(clickCount);

        // when
        for (int i = 0; i < clickCount; i++) {
            executor.execute(() -> {
                service.click();
                executorCount.countDown();
            });
        }
        executorCount.await();
        executor.shutdown();

        // then
        int result = service.getClickCount().get();
        System.out.println("Atomic click count = " + result);
        assertThat(result).isEqualTo(clickCount);
    }
}