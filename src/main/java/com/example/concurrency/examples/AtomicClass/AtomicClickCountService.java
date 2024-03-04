package com.example.concurrency.examples.AtomicClass;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;

@Getter
public class AtomicClickCountService {

    private AtomicInteger clickCount;

    public AtomicClickCountService() {
        this.clickCount = new AtomicInteger();
    }

    // 클릭 횟수 + 1
    public void click() {
        clickCount.incrementAndGet();
    }
}
