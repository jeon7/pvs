package _new.synchronisationsmechanismen.praktikum.solution.semaphore;

public class Aufgabe3 {
    public static void main(String[] args) {
        Semaphore sems[] = new Semaphore[4];
        for (int i = 0; i < sems.length; i++) {
            sems[i] = new Semaphore(0);
        }

        // Erstelle und starte Thread 1
        new ControlledThread(sems) {
            @Override
            public void action() {
                System.out.println("Aktion 1 ausgeführt");
            }

            @Override
            public void run() {
                action();
                sems[0].v();
            }
        }.start();

        // Erstelle und starte Thread 2
        new ControlledThread(sems) {
            @Override
            public void action() {
                System.out.println("Aktion 2 ausgeführt");
            }

            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                action();
                sems[1].v();
            }
        }.start();

        // Erstelle und starte Thread 3
        new ControlledThread(sems){
            @Override
            public void action() {
                System.out.println("Aktion 3 ausgeführt");
            }

            @Override
            public void run() {
                sems[0].p();
                action();
                sems[2].v();
            }
        }.start();

        // Erstelle und starte Thread 4
        new ControlledThread(sems){
            @Override
            public void action() {
                System.out.println("Aktion 4 ausgeführt");
            }

            @Override
            public void run() {
                sems[1].p();
                action();
                sems[3].v();
            }
        }.start();

        // Erstelle und starte Thread 5
        new ControlledThread(sems){
            @Override
            public void action() {
                System.out.println("Aktion 5 ausgeführt");
            }

            @Override
            public void run() {
                sems[2].p();
                sems[3].p();
                action();
            }
        }.start();
    }
}