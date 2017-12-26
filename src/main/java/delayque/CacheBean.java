package delayque;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author littlesky
 * @param <T>
 */
public class CacheBean<T> implements Delayed {

	private String id;
	
	private String name;
	
	private T data;
	
	
	//数据的到期时间
	private Long activeTime;
	
	
	//要求传入的activeTime为毫秒，在构造函数中会自动转换成纳秒
	public CacheBean(String id, String name, T data, Long activeTime) {
		super();
		this.id = id;
		this.name = name;
		this.data = data;
		this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime, TimeUnit.MILLISECONDS)+System.nanoTime();
	}
	
	
	

	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public T getData() {
		return data;
	}




	public void setData(T data) {
		this.data = data;
	}



	

	public Long getActiveTime() {
		return activeTime;
	}




	public void setActiveTime(Long activeTime) {
		this.activeTime = activeTime;
	}




	@Override
	public int compareTo(Delayed o) {
		long d = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		return (d == 0) ? 0 : (d >0 ? 1 : -1);
	}

	//返回还有多少纳秒的剩余时间
	@Override
	public long getDelay(TimeUnit unit) {

		return unit.convert(this.activeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

}
