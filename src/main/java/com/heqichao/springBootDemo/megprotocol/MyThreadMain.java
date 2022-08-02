package com.heqichao.springBootDemo.megprotocol;

public class MyThreadMain {

	 
	public static void main(String[] args) {

		// TODO Auto-generated method stub

		MyThread1 myThread1 = new MyThread1();

		MyThread2 myThread2 = new MyThread2();

		Thread t1 = new Thread(myThread1);

		t1.setName("t1");

		t1.start();

		Thread t2 = new Thread(myThread2);

		t2.setName("t2");

		t2.start();

		}
}

class MyThread1 implements Runnable{
	public int i = 10;
	@Override
	public void run (){
		Thread currThread = Thread.currentThread ();
		synchronized (currThread){
			++i;
			System.out.println (this.getClass ().getName () + " i = " + i);
			currThread.notify ();
		}
	}
}
 class MyThread2 implements Runnable{
	@Override
	public void run (){
		Thread currThread = Thread.currentThread ();
		synchronized (currThread){
			while ("t1".equals (currThread.getName ())){
				try{
					currThread.wait (0);
				}catch (InterruptedException e){
					e.printStackTrace ();
				}
			}
			done ();
		}
	}
	public synchronized void done (){
		System.out.println ("更改完毕");
	}
}
