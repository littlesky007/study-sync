package delayque;

import java.util.List;
import java.util.concurrent.DelayQueue;

public class PutUserWork implements Runnable{
	private DelayQueue<CacheBean<User>> delayQueue;
	
	private List<CacheBean<User>> list;

	

	public PutUserWork(DelayQueue<CacheBean<User>> delayQueue, List<CacheBean<User>> list) {
		super();
		this.delayQueue = delayQueue;
		this.list = list;
	}



	@Override
	public void run() {
		list.forEach(cacheBean->{
			delayQueue.put(cacheBean);
			System.out.println("放入："+cacheBean.getData());
		});
	}
	
	
	
	
}
