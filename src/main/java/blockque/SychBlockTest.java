package blockque;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;

public class SychBlockTest {
	static int j = 0;
	static CountDownLatch countDownLatch = new CountDownLatch(1);
	public static void main(String[] args) throws InterruptedException {
		SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
		
		for(int i = 0;i < 20; i++)
		{
			new Thread(()->{
				try {
					synchronousQueue.put(j++);
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		}
		
		for(int i = 0;i <2; i++)
		{
			new Thread(()->{					
					System.out.println(synchronousQueue.poll());
				
			}).start();
		}
		countDownLatch.await();
	}

}
