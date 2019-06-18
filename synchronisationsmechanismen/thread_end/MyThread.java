package _new.synchronisationsmechanismen.thread_end;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyThread extends Thread {

	public AtomicBoolean stop = new AtomicBoolean(false);
	
	@Override
	public void run() {
		while (!stop.get()) {
			System.out.println("Hello World!");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		MyThread t = new MyThread();
		t.start();

//		t.stop.set(true);
		
		t.interrupt();
		
	}

}
