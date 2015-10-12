package example.xfsp.miappstore.manager;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程管理者
 */
public class ThreadManager {
    private ThreadManager() {

    }

    private static ThreadManager instance = new ThreadManager();
    private ThreadPoolProxy longPool;
    private ThreadPoolProxy shortPool;
    private static Object o = new Object();
    private static ThreadPoolProxy mDownloadPool = null;

    public static ThreadManager getInstance() {
        return instance;
    }

    // 联网比较耗时
    // cpu的核数*2+1
    public synchronized ThreadPoolProxy createLongPool() {
        if (longPool == null) {
            longPool = new ThreadPoolProxy(5, 5, 5000L);
        }
        return longPool;
    }

    // 操作本地文件
    public synchronized ThreadPoolProxy createShortPool() {
        if (shortPool == null) {
            shortPool = new ThreadPoolProxy(3, 3, 5000L);
        }
        return shortPool;
    }

    /**
     * 获取下载线程
     */
    public synchronized ThreadPoolProxy createDownloadPool() {
        if (mDownloadPool == null) {
            mDownloadPool = new ThreadPoolProxy(3, 3, 5L);
        }
        return mDownloadPool;
    }

    public static class ThreadPoolProxy {
        private ThreadPoolExecutor pool;
        private int corePoolSize;
        private int maximumPoolSize;
        private long time;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long time) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.time = time;

        }

        /**
         * 执行任务
         *
         * @param runnable
         */
        public void execute(Runnable runnable) {
            if (runnable!=null){
                if (pool == null) {
                    // 创建线程池
                /*
				 * 1. 线程池里面管理多少个线程2. 如果排队满了, 额外的开的线程数3. 如果线程池没有要执行的任务 存活多久4.
				 * 时间的单位 5 如果 线程池里管理的线程都已经用了,剩下的任务 临时存到LinkedBlockingQueue对象中 排队
				 */
                    pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                            time, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>(100));
                }
                pool.execute(runnable); // 调用线程池 执行异步任务
            }
        }

        /**
         * 取消任务
         *
         * @param runnable
         */
        public void cancel(Runnable runnable) {
            if (pool != null && !pool.isShutdown() && !pool.isTerminated()) {
                pool.remove(runnable); // 取消异步任务
            }
        }
    }
}

