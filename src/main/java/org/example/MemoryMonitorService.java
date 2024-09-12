package org.example;

import org.springframework.stereotype.Service;

@Service
public class MemoryMonitorService {

    private static final double THRESHOLD = 80.0; // 80% memory usage threshold

    public boolean checkMemoryLeak() {
        double usedMemory = getUsedMemoryPercentage();
        return usedMemory > THRESHOLD;
    }

    public double getUsedMemoryPercentage() {
        Runtime runtime = Runtime.getRuntime();
        double totalMemory = runtime.totalMemory();
        double freeMemory = runtime.freeMemory();
        double usedMemory = totalMemory - freeMemory;
        return (usedMemory / totalMemory) * 100;
    }
}
