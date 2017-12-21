package aqs;

import java.util.concurrent.CountDownLatch;

public class MyLockTest {

	static volatile Integer count=0;

	//锁里面只允许有两个线程
	static class MyMulJob implements Runnable
	{
		MultiLock myLock = new MultiLock();
		//MyLock myLock = new MyLock();
		public MyLockTest test;

		public MyMulJob(MyLockTest param) {
			this.test = param;
		}

		public void run()
		{
			myLock.lock();
			try
			{
				while(test.count<100)
				{
					test.count++;
					try {
						Thread.currentThread().sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+":"+test.count);
				}

			}
			finally {
				myLock.lock();
			}
		}
	}

	static class MyJob implements Runnable
	{
		public CountDownLatch countDownLatch = new CountDownLatch(100);


		MyLock myLock = new MyLock();
		//MultiLock myLock = new MultiLock();
		public MyLockTest test;

		public MyJob(MyLockTest param) {
			this.test = param;
		}

		@Override
		public  void run() {

			while(test.count<100)
			{
				myLock.lock();
				try{
					if(test.count<100)
					{
						test.count++;
						try {
							Thread.currentThread().sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+":"+test.count);
					}
				}
				finally
				{
					myLock.unlock();
				}
			}
		}

	}
	public static void main(String[] args) throws InterruptedException {
		MyJob myjob = new MyJob(new MyLockTest());
		MyMulJob myMulJob = new MyMulJob(new MyLockTest());
		for(int j = 0; j<=30;  j++)
		{
			new Thread(myMulJob).start();
		}

		myjob.countDownLatch.await();
	}

}
