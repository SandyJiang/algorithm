package js.com.simple_question;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
	三个线程，第一个线程打印1,2,3,4,5，第二个线程6,7,8,9,10，这样，打印结果为
	Thread[pool-1-thread-1,5,main]1  2  3  4  5  
	Thread[pool-1-thread-2,5,main]6  7  8  9  10 
	Thread[pool-1-thread-3,5,main]11 12 13 14 15 
	Thread[pool-1-thread-1,5,main]16 17 18 19 20 
	Thread[pool-1-thread-2,5,main]21 22 23 24 25 
	Thread[pool-1-thread-3,5,main]26 27 28 29 30 
	Thread[pool-1-thread-1,5,main]31 32 33 34 35 
	Thread[pool-1-thread-2,5,main]36 37 38 39 40 
	Thread[pool-1-thread-3,5,main]41 42 43 44 45 
	Thread[pool-1-thread-1,5,main]46 47 48 49 50 
	Thread[pool-1-thread-2,5,main]51 52 53 54 55 
	Thread[pool-1-thread-3,5,main]56 57 58 59 60 
	Thread[pool-1-thread-1,5,main]61 62 63 64 65 
	Thread[pool-1-thread-2,5,main]66 67 68 69 70 
	Thread[pool-1-thread-3,5,main]71 72 73 74 75 
 */
public class CurrentAlternatePrintTest {
    private static final int TASK_NUM = 3;
    private static int num = 0;
    private static int flag = 0;
    private static Lock lock = new ReentrantLock();
    private static List<Condition> list = new ArrayList<Condition>();
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static {
        for (int i = 0; i < TASK_NUM; i++) {
            list.add(lock.newCondition());
        }
    }

    private static void crit() {
        if (num >= 75) {
            System.exit(1);
        }
    }

    private static void print() {
        crit();
        System.out.print(Thread.currentThread());
        for (int i = 0; i < 5; i++) {
            System.out.format("%-2d ", ++num);
        }
        System.out.println();
    }

    private static void work(int i) {
        while (!Thread.interrupted()) {
            try {
                lock.lock();
                if (flag == i) {
                    crit();
                    print();
                    flag = (i + 1) % list.size();
                    list.get((i + 1) % list.size()).signal();
                } else {
                    try {
                        list.get(i % list.size()).await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private static class Task implements Runnable {
        private final int i;

        public Task(int i) {
            this.i = i;
        }

        public void run() {
            work(i);
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < list.size(); i++)
            exec.execute(new Task(i));
    }

}  

