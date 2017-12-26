package myTest;

import java.io.IOException;

public interface Myinter1<T> {
	
	public T getObject(T obj);
	
	void close()throws IOException;

}
