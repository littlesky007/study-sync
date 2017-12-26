package delayque;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;

public class MainTest {
	public static void main(String[] args) throws InterruptedException {
		User u1 = new User("张三");
		CacheBean<User> c1 = new CacheBean<User>("1", "张三", u1, 5000L);
		
		User u2 = new User("李四");
		CacheBean<User> c2 = new CacheBean<User>("2", "李四", u2, 3000L);
		
		List<CacheBean<User>> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		
		DelayQueue<CacheBean<User>> delayQueue = new DelayQueue<>();
		
		new Thread(new PutUserWork(delayQueue,list)).start();
		
		new Thread(new GetUserWork(delayQueue)).start();
		
		
		CountDownLatch countDownLatch = new CountDownLatch(1);
		countDownLatch.await();
	}
}
