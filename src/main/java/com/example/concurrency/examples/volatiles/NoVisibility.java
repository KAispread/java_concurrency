package com.example.concurrency.examples.volatiles;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoVisibility {
    public static volatile boolean flag;

    public static class FlagCall extends Thread {
        @Override
        public void run() {
            log.info("Freeze");
            while (!flag) { /* 대기중 */ }
            log.info("Melt");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FlagCall flagCall = new FlagCall();

        flagCall.start();
        Thread.sleep(1000);
        melt();
        flagCall.join();
    }

    public static void melt() {
        flag = true;
    }
}
