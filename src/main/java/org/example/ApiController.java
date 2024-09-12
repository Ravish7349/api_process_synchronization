package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private NumberPrinterService numberPrinterService;

    @Autowired
    private MemoryMonitorService memoryMonitorService;

    @Autowired
    private ServerManager serverManager;

    @GetMapping("/start")
    public String startPrinting() throws IOException {
        while (true) {
            int number = numberPrinterService.printNumbers(); // Get the number
            double memoryLeakPercentage = memoryMonitorService.getUsedMemoryPercentage();

            System.out.println("Memory Leak Percentage: " + memoryLeakPercentage);

            if (memoryMonitorService.checkMemoryLeak()) {
                System.out.println("Memory leak detected. Switching server...");
                serverManager.switchServer();
                break;
            }
        }
        return "Printing paused due to memory leak. Server switched.";
    }
}
