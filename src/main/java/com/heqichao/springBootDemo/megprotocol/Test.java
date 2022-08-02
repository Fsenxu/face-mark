package com.heqichao.springBootDemo.megprotocol;

public class Test {
	Object syncObj = new Object();
	public static void main(String args[]){
//		new Test();
		SubAlarmAutoRunnable mi = new SubAlarmAutoRunnable();
        mi.setUrl("123456");
        Thread t = new Thread(mi); 
        t.start();
	}

	public Test(){
		startBackgroundThread();
		System.out.println("Main thread waiting...");
		try{
			synchronized(syncObj){
				syncObj.wait();
			}
		}catch(InterruptedException ie) {
			
		}
		System.out.println("Main thread exiting...");
	}

	public void startBackgroundThread(){
		(new Thread(new Runnable(){
			@Override
			public void run(){
				//Do something big...
				System.out.println("Background Thread doing something big...");
				//THEN HOW TO NOTIFY MAIN THREAD?
				synchronized(syncObj){
					System.out.println("Background Thread notifing...");
					syncObj.notify();
				}
				System.out.println("Background Thread exiting...");
		}})).start();
	}
}
