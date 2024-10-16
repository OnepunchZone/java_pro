import java.util.LinkedList;
import java.util.List;

public class MyThreadPool {
    private final List<ThreadInWork> inWorkLst;
    private final LinkedList<Runnable> queue;
    private volatile boolean isShutdown;

    public MyThreadPool(int count) {
        queue = new LinkedList<>();
        inWorkLst = new LinkedList<>();
        isShutdown = false;

        createWorkersThreads(count);
    }

    private void createWorkersThreads(int count) {
        for (int i = 0; i < count; i++) {
            ThreadInWork threadInWork = new ThreadInWork();
            inWorkLst.add(threadInWork);
            threadInWork.start();
        }
    }

    private class ThreadInWork extends Thread {

        @Override
        public void run() {
            while (true) {
                Runnable task;
                synchronized (queue) {
                    while (queue.isEmpty() && !isShutdown) {

                        try {
                            System.out.println(Thread.currentThread().getName() + " не получил задачу.");
                            queue.wait();
                            System.out.println(Thread.currentThread().getName() + " проснулся.");
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }

                    }

                    if (isShutdown && queue.isEmpty()) {
                        return;
                    }

                    task = queue.poll();
                }

                if (task != null) {
                    try {
                        task.run();
                    } catch (RuntimeException e) {
                        System.out.println("Задача не выполнена: " + e.getMessage());
                    }
                }
            }
        }
    }

    public synchronized void execute(Runnable r) {
        if (isShutdown) {
            throw new IllegalStateException("Пул потоков начал завершать работу.");
        }
        synchronized (queue) {
            queue.add(r);
            System.out.println("Пришла новая задача.");
            queue.notify();
        }
    }

    public synchronized void shutdown() {
        isShutdown = true;

        synchronized (queue) {
            queue.notifyAll();
            System.out.println("Пул потоков завершает работу.");
        }

        for (ThreadInWork workThread : inWorkLst) {
            try {
                workThread.join();
                System.out.println("Поток " + workThread.getName() + " завершил работу.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
