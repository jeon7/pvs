package _new.synchronisationsmechanismen.praktikum;

public class Semaphore {
	
	private int value;
	
	public Semaphore(int value) {
		this.value = value;
	}
	
	public synchronized void p() {
		while (value <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		value--;
	}
	
	public synchronized void v() {
		value++;
		notify();
	}

}
