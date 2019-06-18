package _new.synchronisationsmechanismen.aufgabe0;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
	public static int numberOfThread = 4;
	public static int numberOfDurchlauf = 200;
	public static MyThread[] thread = new MyThread[numberOfThread];
	public static AtomicInteger durchLauf = new AtomicInteger(-1);
	
	
	@Override
	public void run() {
		
		while (true) {
			addPrint();
			if(durchLauf.get() >= numberOfDurchlauf)
				break;
		}
	}
	
	public static synchronized void addPrint() {
		if(durchLauf.get() >= numberOfDurchlauf)
			return;
		System.out.println(currentThread().getName() + " [(" + durchLauf.addAndGet(1) + ")]");

	}

	public static void main(String[] args) {
		for (int i = 0 ; i < numberOfThread ; i++ ) {
			thread[i] = new MyThread();
			thread[i].setName("Thread[" + (i+1) + "]");
		}
		
		for (int i = 0 ; i < numberOfThread ; i++ ) {
			thread[i].start();
		}
		
	}
	
}
