package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class NumberPrinterService {

    private static final String USER_LOG_FILE = "user_numbers_log.txt";

    private final RedisService redisService;

    @Autowired
    public NumberPrinterService(RedisService redisService) {
        this.redisService = redisService;
    }

    public int printNumbers() throws IOException {
        int currentNumber = redisService.getCurrentNumber();
        System.out.println("Printing number: " + currentNumber);

        // Log to the developer log (Excel)
        saveToExcel(currentNumber, LocalDateTime.now(), redisService.getMemoryUsage());

        // Log to the user log (just numbers)
        saveToUserLog(currentNumber);

        redisService.incrementNumber();
        return currentNumber;
    }

    public void saveToExcel(int number, LocalDateTime timestamp, double memoryLeakPercentage) throws IOException {
        ExcelLogger.logData(number, timestamp, memoryLeakPercentage);
    }

    public void saveToUserLog(int number) throws IOException {
        try (FileWriter writer = new FileWriter(USER_LOG_FILE, true)) {
            writer.write(number + "\n");
        }
    }
}
