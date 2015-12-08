package js.com.simple_question

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Worker w = new Worker();
        for (int i = 0; i < 3; i++)
            exec.execute(new Task(w,i));
    }
}


class Worker {
    private  final int TASK_NUM = 3;
    private  int num = 0;
    private  int flag = 0;
    private  Lock lock = new ReentrantLock();
    private  List<Condition> list = new ArrayList<Condition>();
    private  ExecutorService exec = Executors.newCachedThreadPool();

     {
        for (int i = 0; i < TASK_NUM; i++) {
            list.add(lock.newCondition());
        }
    }

    private  void crit() {
        if (num >= 75) {
            System.exit(1);
        }
    }

    private  void print() {
        System.out.print(Thread.currentThread());
        for (int i = 0; i < 5; i++) {
            System.out.format("%-2d ", ++num);
        }

        System.out.println();
        try {
            Random r= new Random(System.currentTimeMillis());
            Thread.sleep((r.nextInt(10)+1)*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void work(int i) {
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
}

public Task implements Runnable {
    Worker w;
    final private int i;
    Task(Worker w,int i){
        this.w = w;
        this.i = i;
    }



    public void run() {
       w.work(i);

    }

}

