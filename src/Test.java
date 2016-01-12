
public class Test {

    public void test(){
        SomeObject so = new SomeObject();
        Thread t1 = null;
        Thread t2 = null;


        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String name = "first";
                //so.method1(name, t2);
            }
        });
        t1.start();

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String name = "second";
                //so.method1(name, t1);
                //so.method2(name, t1);
                //so.notify();
            }
        });
        t2.start();
    }
}
