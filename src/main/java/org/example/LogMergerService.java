package org.example;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LogMergerService {

    private static final String APP1_LOG_FILE = "app1_user_numbers_log.txt";  // Log from application 1 (8080)
    private static final String APP2_LOG_FILE = "app2_user_numbers_log.txt";  // Log from application 2 (8081)
    private static final String MERGED_LOG_FILE = "merged_user_numbers_log.txt";  // Final merged log

    public void mergeLogs() throws IOException {
        // Read logs from both applications
        List<String> app1Numbers = readNumbersFromLog(APP1_LOG_FILE);
        List<String> app2Numbers = readNumbersFromLog(APP2_LOG_FILE);

        // Combine and sort numbers (optional)
        List<String> allNumbers = Stream.concat(app1Numbers.stream(), app2Numbers.stream())
                .sorted()  // Sort if required
                .collect(Collectors.toList());

        // Write merged log
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(MERGED_LOG_FILE))) {
            for (String number : allNumbers) {
                writer.write(number + "\n");
            }
        }
    }

    private List<String> readNumbersFromLog(String logFile) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(logFile))) {
            return stream.collect(Collectors.toList());
        }
    }
}
