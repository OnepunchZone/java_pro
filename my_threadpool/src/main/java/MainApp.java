public class MainApp {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(5);

        for (int i = 0; i < 50; i++) {
            int taskId = i;
            myThreadPool.execute(() -> {
                System.out.println("Задача №" + taskId + " запущена в потоке " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        myThreadPool.shutdown();
    }
}
