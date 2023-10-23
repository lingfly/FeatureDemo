package org.lingfly.virtual;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualDemo {

    public static void main(String[] args) {
        VirtualDemo demo = new VirtualDemo();
        demo.testVirtual();
    }

    void testVirtual() {
        var a = new AtomicInteger(0);
        // 创建一个固定200个线程的线程池
        try (var vs = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Integer>> futures = new ArrayList<>();
            var begin = System.currentTimeMillis();
            // 向线程池提交1000个sleep 1s的任务
            for (int i = 0; i < 1_000; i++) {
                var future = vs.submit(new ComputeTask(a));
                futures.add(future);
            }
            // 获取这1000个任务的结果
            for (var future : futures) {
                var i = future.get();
                if (i % 100 == 0) {
                    System.out.print(i + " ");
                }
            }
            // 打印总耗时
            System.out.println("Exec finished.");
            System.out.printf("Exec time: %dms.%n", System.currentTimeMillis() - begin);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void computeN(long n) {
        long st = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {

        }
//        System.out.println("time: " + (System.currentTimeMillis() - st));
    }

    class ComputeTask implements Callable<Integer> {
        final AtomicInteger a;

        public ComputeTask(AtomicInteger a) {
            this.a = a;
        }

        @Override
        public Integer call() {
            computeN(2_000_000_000L);
            return a.addAndGet(1);
        }
    }
    class SleepTask implements Callable<Integer> {
        final AtomicInteger a;
        public SleepTask(AtomicInteger a) {
            this.a = a;
        }

        @Override
        public Integer call() throws InterruptedException {
            Thread.sleep(Duration.ofSeconds(1));
            return a.addAndGet(1);
        }
    }
}
