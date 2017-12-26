package delayque;

import java.util.concurrent.DelayQueue;
/**
 * 取线程，不停的在取数据
 * @author littlesky
 *
 */
public class GetUserWork implements Runnable{

	private DelayQueue<CacheBean<User>> delayque;

	public GetUserWork(DelayQueue<CacheBean<User>> delayque) {
		super();
		this.delayque = delayque;
	}

	@Override
	public void run() {
		while(true)
		{
			try {
				CacheBean<User> element = delayque.take();
				System.out.println("get element:"+element+"  id:"+element.getId()+", name:"+element.getName()
				+" data:"+element.getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
