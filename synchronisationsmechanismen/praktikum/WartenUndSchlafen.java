package _new.synchronisationsmechanismen.praktikum;

public class WartenUndSchlafen extends Thread {
	WartenUndSchlafen toJoin;
	long sleepTime;
	
	public WartenUndSchlafen(String threadName, WartenUndSchlafen toJoin, long sleepTime) {
		this.setName(threadName);
		this.toJoin = toJoin;
		this.sleepTime = sleepTime;
		
		System.out.println(this + " created");
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread() + " started");
		
		try {
			if(toJoin!= null) {
				while(!toJoin.isAlive());
				System.out.println(this.currentThread() + " join to " + toJoin.getName());
				toJoin.join();
			}
			
		} catch (InterruptedException e) {
		}
		
		try {
			System.out.println(this.currentThread() + " sleep for " + sleepTime + "ms");
			Thread.sleep(sleepTime);
			System.out.println(this.currentThread() + " ends ");
		} catch (InterruptedException e) {
		}
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		WartenUndSchlafen thread3 = new WartenUndSchlafen("thread3", null, 400);
		WartenUndSchlafen thread2 = new WartenUndSchlafen("thread2", thread3, 300);
		WartenUndSchlafen thread1 = new WartenUndSchlafen("thread1", thread2, 200);
		
		thread1.start();
		Thread.sleep(10);
		thread2.start();
		Thread.sleep(20);
		thread3.start();
	}

}
