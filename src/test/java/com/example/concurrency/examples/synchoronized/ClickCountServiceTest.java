package com.example.concurrency.examples.synchoronized;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClickCountServiceTest {

    @DisplayName("Synchronized: Click 횟수만큼 click-count 가 증가한다.")
    @Test
    void synchronized_click() throws InterruptedException {
        // given
        final int clickCount = 10000;
        ClickCountService service = new ClickCountService();
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
        int result = service.getClickCount();
        System.out.println("click count = " + result);
        assertThat(result).isEqualTo(clickCount);
    }
}
