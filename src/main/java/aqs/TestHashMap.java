package aqs;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
/**
 * 
 * @author littlesky
 *
 */
public class TestHashMap {
	public static HashMap map = new HashMap();
	public volatile static Integer index=0;
	public static void main(String[] args) throws InterruptedException {
		for(int i=0; i<=10;i++)
		{
			new Thread(()->{
				for(int j=0;j<10;j++)
				{

					try {
						Thread.sleep(index);
						map.put(index++, index);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}

			}).start();
		}
		new CountDownLatch(1).await();
	}

}
