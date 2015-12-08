package com.js.simple_question;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class CurrentAlternatePrintTest2 {
    static final int FINAL_NUM = 75;
    private List<PrintTasks> tasks = new ArrayList<PrintTasks>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public CurrentAlternatePrintTest2(int nTasks, final int pause) {
        barrier = new CyclicBarrier(nTasks, new Runnable() {


            public void run() {
                for (PrintTasks task : tasks)
                    if (task.getNum() >= FINAL_NUM) {
                        exec.shutdownNow();
                        return;
                    } else {
                        task.printNum();
                    }
            }
        });
        try {
            TimeUnit.MILLISECONDS.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < nTasks; i++) {
            PrintTasks task = new PrintTasks(barrier);
            tasks.add(task);
            exec.execute(task);
        }
    }

    public static void main(String[] args) {
        new CurrentAlternatePrintTest2(3, 100);
    }
}

class PrintTasks implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static volatile int num = 0;

    private CyclicBarrier barrier;

    public PrintTasks(CyclicBarrier b) {
        barrier = b;
    }

    public synchronized int getNum() {
        return num;
    }


    public void run() {
        try {
            while (!Thread.interrupted()) {
                barrier.await();
            }
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    public void printNum() {
        System.out.print("Thread" + id + ": ");
        for (int i = 0; i < 5; i++) {
            System.out.format("%3d", ++num);
        }
        System.out.println();
    }
}
