package zl.com.test.api.task;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuhaifeng
 * <p>
 * copy from java.util.concurrent.Executors.java
 * add priority controll
 */
public class DefaultThreadFactory implements ThreadFactory {
    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
    public static final int HIGH_PRIORITY = 7;
    public static final int LOW_PRIORITY = 4;
    public static final int MIDDLE_PRIOPITY=5;
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private final int priority;

    public DefaultThreadFactory(int priority) {
        if (priority < Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException("priority: " + priority + " (expected: Thread.MIN_PRIORITY <= priority <= Thread.MAX_PRIORITY)");
        }
        this.priority = priority;
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = "priority-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        t.setPriority(priority);
        return t;
    }

    public static void main(String[] args) {
        AtomicInteger threadNumber = new AtomicInteger(1);
        for (int i = 0; i < 10; i++) {
            String namePrefix = "priority-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-" + threadNumber.getAndIncrement();
            System.out.println(namePrefix);
        }

    }
}
