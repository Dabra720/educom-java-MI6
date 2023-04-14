package demo1;

class Runner extends Thread{
    //    @Override
    public void run() {
//        super.run();
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
    // Define different components

    public static void main(String[] args) {
        Runner runner1 = new Runner();
        runner1.start();



    }




}

