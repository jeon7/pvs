package _new.synchronisationsmechanismen.praktikum.solution.semaphore;

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
                e.printStackTrace();
            }
        }

        value--;
    }

    public synchronized void v(){
        value++;
        notify();
    }
}
