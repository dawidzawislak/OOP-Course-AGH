package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final List<Thread> threads;

    private final ExecutorService threadPool;

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        threads = new ArrayList<>();
        threadPool = Executors.newFixedThreadPool(4);
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            threads.add(new Thread(simulation));
            threads.get(threads.size()-1).start();
        }
        awaitSimulationsEnd();
    }

    public void awaitSimulationsEnd() {
        for (Thread thread : threads) {
            try {thread.join();}
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public void runAsyncInThreadPool() {
        for (Simulation simulation : simulations) {
            threadPool.submit(simulation);
        }
        awaitSimulationsEnd();
    }
}
