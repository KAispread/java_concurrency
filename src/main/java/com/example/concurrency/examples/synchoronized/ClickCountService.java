package com.example.concurrency.examples.synchoronized;

import lombok.Getter;

@Getter
public class ClickCountService {

    private int clickCount;

    public ClickCountService() {
        this.clickCount = 0;
    }

    // 클릭 횟수 + 1
    public synchronized void click() {
        clickCount += 1;
    }

    public void click2() {
        synchronized (this) {
            clickCount += 1;
        }
    }
}
