public class Main {
    static final int NUMBER_OF_CARS = 10;
    static final int CAR_PRODUCTION_TIME = 2_000;
    static final int WAITING_TIME = 1_300;

    public static void main(String[] args) {
        ThreadGroup consumerGroup = new ThreadGroup("consumerGroup");
        final Shop shop = new Shop();

        Thread producerThread = new Thread(new Manufacturer(shop), "Производитель Toyota");
        producerThread.start();

        new Thread(consumerGroup, new Consumer(shop), "Покупатель 1").start();
        new Thread(consumerGroup, new Consumer(shop), "Покупатель 2").start();
        new Thread(consumerGroup, new Consumer(shop), "Покупатель 3").start();

        try {
            producerThread.join();
        } catch (InterruptedException ignored) {
        }

        consumerGroup.interrupt();
    }
}
