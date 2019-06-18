package _new.synchronisationsmechanismen.praktikum;

public class SemaphoreExample {

	public static void main(String[] args) {
		Semaphore semaphore[] = new Semaphore[4];
		for (int i = 0 ; i < semaphore.length ; i++) {
			semaphore[i] = new Semaphore(0);
		}
		
		MyThread[] myThread = new MyThread[5];
		myThread[0] = new Thread1(semaphore);
		myThread[1] = new Thread2(semaphore);
		myThread[2] = new Thread3(semaphore);
		myThread[3] = new Thread4(semaphore);
		myThread[4] = new Thread5(semaphore);
		
		for (int j = 0; j < myThread.length; j++) {
			myThread[j].start();
		}
		
	}
}

