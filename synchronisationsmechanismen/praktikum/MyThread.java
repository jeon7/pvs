package _new.synchronisationsmechanismen.praktikum;

abstract class MyThread extends Thread{
	Semaphore[] semaphore;
	
	public MyThread(Semaphore[] semaphore) {
		this.semaphore = semaphore;
	}
	
	public abstract void action();
	
}

class Thread1 extends MyThread {
	
	public Thread1(Semaphore[] semaphore) {
		super(semaphore);
	}
	
	@Override
	public void action() {
		System.out.println("Thread1 running");
	}
	
	@Override
	public void run() {
		action();
		semaphore[0].v();
	}
}

class Thread2 extends MyThread {
	
	public Thread2(Semaphore[] semaphore) {
		super(semaphore);
	}
	
	@Override
	public void action() {
		System.out.println("Thread2 running");
	}
	
	@Override
	public void run() {
		action();
		semaphore[1].v();
	}
}

class Thread3 extends MyThread {
	
	public Thread3(Semaphore[] semaphore) {
		super(semaphore);
	}
	
	@Override
	public void action() {
		System.out.println("Thread3 running");
	}
	
	@Override
	public void run() {
		semaphore[0].p();
		action();
		semaphore[2].v();
	}
}

class Thread4 extends MyThread {
	
	public Thread4(Semaphore[] semaphore) {
		super(semaphore);
	}
	
	@Override
	public void action() {
		System.out.println("Thread4 running");
	}
	
	@Override
	public void run() {
		semaphore[1].p();
		action();
		semaphore[3].v();
		}
}

class Thread5 extends MyThread {
	
	public Thread5(Semaphore[] semaphore) {
		super(semaphore);
	}
	
	@Override
	public void action() {
		System.out.println("Thread5 running");
	}
	
	@Override
	public void run() {
		semaphore[2].p();
		semaphore[3].p();
		action();
	}
}
