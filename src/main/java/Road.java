import java.util.concurrent.BrokenBarrierException;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000L);
            MainClass.road.await();
            System.out.println(c.getName() + " закончил этап: " + description);
            if (this.length == 40) {
                MainClass.finish.countDown();
                if (MainClass.finish.getCount() == 3) {
                    System.out.println(c.getName() + " - WIN");
                }
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}