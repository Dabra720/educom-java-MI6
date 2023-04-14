package demo2;

class Runner implements Runnable {
    public void run() {
        System.out.println("Run");
        for(int i=0; i<10; i++){
            System.out.println("Hello " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());

        t1.start();
        t2.start();
    }
}
