package ch.teko.pvs;

import java.util.ArrayList;
import java.util.Comparator;

/** System Simulation class. It represents the computer or device which runs one ore more CPUs with multiple processes.
 * 
*/
public class SystemSimulation {
    private ArrayList<ThreadRunnablePair> cpus;

    /** Generates the time line of the cpu with round robin scheduler. It uses 4 processes.
     * 
     * @return String of the CPU time line.
     */
    public static String generateRoundRobinExample(){
        SystemSimulation system = new SystemSimulation();
        final CircularProcessArray<ProcessSimulation> processes = new CircularProcessArray<ProcessSimulation>();
        processes.add(new ProcessSimulation(800));
        processes.add(new ProcessSimulation(100));
        processes.add(new ProcessSimulation(200));
        processes.add(new ProcessSimulation(400));
        final CPUSimulation cpu = new CPUSimulation();
        InterruptServiceRoutine routine = new InterruptServiceRoutine(){
            @Override
            public void doRoutine() {
                ProcessSimulation process = processes.getUnfinishedNext();
                if(process != null){
                    cpu.setProcess(process);
                }
            }
        };
        cpu.setInterruptServiceRoutine(routine);
        system.addCPU(cpu);
        system.startSystem();
        while(!processes.checkAllProcessesFinished()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        system.shutdownSystem();
        return cpu.toString();
    }

    /** Generates the time line of the cpu with first in first out scheduler. It uses 4 processes.
     * 
     * @return String of the CPU time line.
     */
    public static String generateFirstInFirstOutExample(){
        SystemSimulation system = new SystemSimulation();
        final CircularProcessArray<ProcessSimulation> processes = new CircularProcessArray<ProcessSimulation>();
        processes.add(new ProcessSimulation(800));
        processes.add(new ProcessSimulation(100));
        processes.add(new ProcessSimulation(200));
        processes.add(new ProcessSimulation(400));
        final CPUSimulation cpu = new CPUSimulation();
        InterruptServiceRoutine routine = new InterruptServiceRoutine(){
            @Override
            public void doRoutine() {
                ProcessSimulation process = processes.getFirstUnfinished();
                if(process != null){
                    cpu.setProcess(process);
                }
            }
        };
        cpu.setInterruptServiceRoutine(routine);
        system.addCPU(cpu);
        system.startSystem();
        while(!processes.checkAllProcessesFinished()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        system.shutdownSystem();
        return cpu.toString();
    }

    /** Generates the time line of the cpu with shortest job next scheduler. It uses 4 processes.
     * 
     * @return String of the CPU time line.
     */
    public static String generateShortestJobNextExample(){
        SystemSimulation system = new SystemSimulation();
        final CircularProcessArray<ProcessSimulation> processes = new CircularProcessArray<ProcessSimulation>();
        processes.add(new ProcessSimulation(800));
        processes.add(new ProcessSimulation(100));
        processes.add(new ProcessSimulation(200));
        processes.add(new ProcessSimulation(400));
        final CPUSimulation cpu = new CPUSimulation();
        final Comparator<ProcessSimulation> instruction_comparator = new Comparator<ProcessSimulation>() {
            @Override
            public int compare(ProcessSimulation o1, ProcessSimulation o2) {
                int instructions_1 = o1.getInstructionCount() - o1.getCurrentInstructionCount();
                int instructions_2 = o2.getInstructionCount() - o2.getCurrentInstructionCount();
                return instructions_1 - instructions_2;
            }
        }; 
        processes.sort(instruction_comparator);
        InterruptServiceRoutine routine = new InterruptServiceRoutine(){
            @Override
            public void doRoutine() {
                ProcessSimulation current_process = null;
                
                for(ProcessSimulation process : processes){
                    if(!process.isFinished()){
                        current_process = process;
                        break;
                    }
                }
                if(current_process != null){
                    cpu.setProcess(current_process);
                }
            }
        };
        cpu.setInterruptServiceRoutine(routine);
        system.addCPU(cpu);
        system.startSystem();
        while(!processes.checkAllProcessesFinished()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        system.shutdownSystem();
        return cpu.toString();
    }
    
    /** Generates the time line of the cpu with earliest deadline first scheduler. It uses 3 processes. See https://en.wikipedia.org/wiki/Earliest_deadline_first_scheduling
     * 
     * @return String of the CPU time line.
     */
    public static String generateEarliestDeadlineFirstExample(){
        SystemSimulation system = new SystemSimulation();
        final CircularProcessArray<ProcessTimingPair> processes = new CircularProcessArray<ProcessTimingPair>();
        processes.add(new ProcessTimingPair(new ProcessSimulation(1000, 8, 1)));
        processes.add(new ProcessTimingPair(new ProcessSimulation(1000, 5, 2)));
        processes.add(new ProcessTimingPair(new ProcessSimulation(1000, 10, 4)));
        final CPUSimulation cpu = new CPUSimulation();
        final Comparator<ProcessTimingPair> deadline_comparator = new Comparator<ProcessTimingPair>() {
            @Override
            public int compare(ProcessTimingPair o1, ProcessTimingPair o2) {
                return o1.next_dead_line - o2.next_dead_line;
            }
        }; 
        
        processes.sort(deadline_comparator);

        InterruptServiceRoutine routine = new InterruptServiceRoutine(){
            private int current_time = 0;
            private ProcessTimingPair current_pair;
            @Override
            public void doRoutine() {
                if(current_pair == null){
                    for(ProcessTimingPair pair : processes){
                        if(!pair.process.isFinished() && pair.isNextDeadLine(current_time)){
                            current_pair = pair;
                            break;
                        }
                    }
                }

                if(current_pair != null){
                    cpu.setProcess(current_pair.process);
                    current_pair.current_slice_count++;
    
                    if(current_pair.isFullExecuted()){
                        current_pair.updateValuesForFinishedExecution();
                        current_pair = null;
                        processes.sort(deadline_comparator);
                    }
                }

                current_time++;
            }
        };

        cpu.setInterruptServiceRoutine(routine);
        system.addCPU(cpu);
        system.startSystem();
        while(!processes.checkAllProcessesTimingPairFinished()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        system.shutdownSystem();
        return cpu.toString();
    }

    public SystemSimulation() {
        cpus = new ArrayList<ThreadRunnablePair>();
    }

    /** Adds a CPU to the system.
     * 
     * @param cpu
     */
    public void addCPU(CPUSimulation cpu) {
        cpus.add(new ThreadRunnablePair(cpu));
    }

    /** Starts the system (like pressing the power button on the computer). All the CPUs starts to run.
     * 
     */
    public void startSystem() {
        for (ThreadRunnablePair pair : cpus) {
            pair.getThread().start();
        }
    }

    /** Shuts down the system.
     * 
     */
    public void shutdownSystem() {
        for (ThreadRunnablePair pair : cpus) {
            ((CPUSimulation) pair.getRunnable()).stop();

            try {
                pair.getThread().join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static class ThreadRunnablePair{
        private Thread thread;
        private Runnable runnable;

        public ThreadRunnablePair(Runnable runnable){
            this.runnable = runnable;
            thread = new Thread(runnable);
        }

        public Thread getThread(){
            return thread;
        }

        public Runnable getRunnable(){
            return runnable;
        }
    }

    private static class CircularProcessArray<E> extends ArrayList<E>{
        private int circle_index = 0;
        
        @Override
        public E get(int index) {
            if(index < 0){
                index = index + size();
            } else if(index > size()){
                index = index % size();
            }
            return super.get(index);
        }

        public E getNext(){
            E element = get(circle_index);
            circle_index = (circle_index + 1) % size();
            return element;
        }

        public void reset(){
            circle_index = 0;
        }

        public ProcessSimulation getUnfinishedNext(){
            for(int i = 0; i < size(); i++){
                E element = getNext();
                if(element instanceof ProcessSimulation){
                    ProcessSimulation process = (ProcessSimulation) element;
                    if(!process.isFinished()){
                        return process;
                    }
                }
            }

            return null;
        }

        public boolean checkAllProcessesFinished(){
            for(E element : this){
                if(element instanceof ProcessSimulation){
                    ProcessSimulation process = (ProcessSimulation) element;
                    if(!process.isFinished()){
                        return false;
                    }
                }
            }

            return true; 
        }

        public boolean checkAllProcessesTimingPairFinished(){
            for(E element : this){
                if(element instanceof ProcessTimingPair){
                    ProcessTimingPair pair = (ProcessTimingPair) element;
                    if(!pair.process.isFinished()){
                        return false;
                    }
                }
            }

            return true; 
        }

        public ProcessSimulation getFirstUnfinished(){
            for(E element : this){
                if(element instanceof ProcessSimulation){
                    ProcessSimulation process = (ProcessSimulation) element;
                    if(!process.isFinished()){
                        return process;
                    }
                }
            }

            return null;
        }
    }

    private static class ProcessTimingPair{
        public ProcessSimulation process;
        public int next_dead_line = 0;
        public int last_finished_dead_line = 0;
        public int current_slice_count = 0;

        public ProcessTimingPair(ProcessSimulation process){
            this.next_dead_line = process.getDeadline();
            this.process = process;
        }

        public boolean isFullExecuted(){
            return current_slice_count >= process.getExecutionTime();
        }

        public boolean isNextDeadLine(int current_slice_count){
            return last_finished_dead_line <= current_slice_count;
        }

        public void updateValuesForFinishedExecution(){
            last_finished_dead_line += process.getDeadline();
            next_dead_line += process.getDeadline();
            current_slice_count = 0;
        }
    }
}
