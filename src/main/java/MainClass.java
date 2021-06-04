import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.CyclicBarrier;


public class MainClass {
    public static final int CARS_COUNT = 4;
    public static CountDownLatch start = new CountDownLatch(CARS_COUNT);
    public static CyclicBarrier road = new CyclicBarrier(CARS_COUNT);
    public static CountDownLatch finish = new CountDownLatch(CARS_COUNT);
    public static Semaphore tunnel = new Semaphore(CARS_COUNT / 2, true);
    // Сделал семафор с true и тогда машинка ждет еще одну и они заежают парами.
    // Хотя по идее лучше без тру делать и тогда просто тоннель вмещает две машинки без их одновременного заезда

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        while (finish.getCount() > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");


    }
}