package myTest;

import java.io.IOException;

public interface MyInter2<T> extends Myinter1{
	public  Object getObject(Object obj);
	public int getA(int a);
	void close()throws IOException;
}
