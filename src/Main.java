import java.awt.font.NumericShaper;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

/*    public static Runnable runnable;
    public static Random random = new Random();

    public static void temp(){
        try {
            for(int i = 0; i < 100; i++){
                Thread t = new Thread(runnable, i+"");
                t.setPriority(random.nextInt(10)+1);
                t.start();
            }
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }*/

    public static void main(String[] args) {
/*         Object monitor = new Object();

       runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (monitor){
                    if(Thread.currentThread().getName().equals("main")) temp();
                    System.out.println(Thread.currentThread().getName());
                }
            }
        };
        new Thread(runnable, "main").start();


        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


/*        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if(lock.tryLock()){
                        print(lock);
                        Thread.currentThread().sleep(1000);
                        lock.unlock();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable, "first").start();

        new Thread(runnable, "second").start();*/

        //new Test().test();

        //List<String> list = Arrays.asList("agdf","dfg","srhd","adfgsdf");
        //int max = 7;
        //System.out.println(list.stream().reduce((acc, text) -> String.format("%s, %s", acc, text)).orElse(""));
        new FileService("service").textFileIndex(Paths.get("./assets"));
    }

/*    static void print(ReentrantLock lock){
        System.out.println(String.format("[%s] isLocked(): %s", Thread.currentThread().getName(), lock.isLocked()));
        System.out.println(String.format("[%s] getHoldCount(): %s", Thread.currentThread().getName(), lock.getHoldCount()));
        System.out.println(String.format("[%s] hasQueuedThreads(): %s", Thread.currentThread().getName(), lock.hasQueuedThreads()));
    }*/
}