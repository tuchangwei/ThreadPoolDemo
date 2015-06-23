package io.github.tuchangwei.threadpooldemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by vale on 6/23/15.
 */
public class ThreadPoolTest {

    ThreadPoolExecutor threadPoolExecutor;
    BlockingQueue<Runnable> queue;

    // Sets the amount of time an idle thread will wait for a task before terminating
    private static final int KEEP_ALIVE_TIME = 1;

    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    // Sets the initial threadpool size to 8
    private static final int CORE_POOL_SIZE = 8;

    // Sets the maximum threadpool size to 8
    private static final int MAXIMUM_POOL_SIZE = 8;

    public ThreadPoolTest() {

        queue = new LinkedBlockingQueue<Runnable>();
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                queue);

        for (int i = 0; i < 20; i++) {
            System.out.println("当前线程池大小[" + threadPoolExecutor.getPoolSize() + "],当前队列大小[" + queue.size() + "]");

            threadPoolExecutor.execute(new MyRunable("Thread"+i));
        }
        // 关闭线程池
        threadPoolExecutor.shutdown();
    }

    public class MyRunable implements Runnable {

        private String name;

        public MyRunable(String name) {
            this.name = name;
        }

        @Override
        public void run() {

            try {

                Thread.sleep(1000);
                System.out.println(name + " finished job!") ;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
