
public class SomeObject {
    public boolean state = false;

    public synchronized void method1(String name, Thread other){
        try {
            System.out.println(String.format("[%s]: before", name));
            while(!state){
                System.out.println(String.format("[%s]: in", name));
                wait();
                System.out.println(String.format("[%s]: after in", name));
            }
            System.out.println(String.format("[%s]: after", name));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("[%s]: hello", name));
    }

    public synchronized void method2(String name, Thread first){
        System.out.println(String.format("[%s]: before", name));
        state = true;
        notify();
        System.out.println(String.format("[%s]: after", name));
        for(int i = 0; i < 1000000; i ++){}
        first.interrupt();
    }
}
