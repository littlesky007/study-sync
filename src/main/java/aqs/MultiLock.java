package aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject;

import aqs.MyLock.Sync;

public class MultiLock implements Lock{

	private final Sync sync;

	public MultiLock()
	{
		this.sync = new Sync(2);
	}


	static class Sync extends AbstractQueuedSynchronizer
	{
		public Sync(int count)
		{
			setState(count);
		}

		@Override
		protected int tryAcquireShared(int arg) {
			for(; ;)
			{
				int count = getState();
				int newCount = count - arg;
				if(newCount < 0 || compareAndSetState(count, newCount))
					return newCount;
			}
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			for(; ;)
			{
				int count = getState();
				int newCount = count + arg;
				if(compareAndSetState(count, newCount))
				{
					return true;
				}
			}
		}


		Condition newCondition()
		{
			return new ConditionObject();
		}

	}


	@Override
	public void lock() {
		sync.acquireShared(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

		sync.acquireSharedInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquireShared(1)>=0;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return sync.newCondition();
	}

}
