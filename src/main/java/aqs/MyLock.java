package aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义独占锁
 * @author littlesky
 *
 */
public class MyLock implements Lock {

	private final Sync sync;
	
	
	public MyLock()
	{
		this.sync = new Sync();
	}
	
	static class Sync extends AbstractQueuedSynchronizer
	{
		//实现自定的独占锁，重写下三个方法

		@Override
		protected boolean tryAcquire(int arg) {
			//0状态，表示可以获取锁
			//1状态，表示不可获取锁
			if(compareAndSetState(0, 1))
			{
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(int arg) {
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}

		//表示是否可以持有锁
		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
		
		Condition newCondition()
		{
			return new ConditionObject();
		}
		
	}
	
	

	@Override
	public void lock() {
		//表示只有一个线程可以独占锁
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1,unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {		return sync.newCondition();
	}

}
