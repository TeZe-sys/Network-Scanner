
package com.network.scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportGenerator {

    public void generateReport(
            String ip,
            boolean reachable,
            List<Integer> openPorts,
            List<Integer> closedPorts) {

        String filePath = "reports/scan-report.txt";

        try (FileWriter writer = new FileWriter(filePath)) {

            writer.write("====================================\n");
            writer.write("          NETWORK SCAN REPORT\n");
            writer.write("====================================\n\n");

            writer.write("Target IP: " + ip + "\n");

            writer.write("Scan Date: " +
                    LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                    ) + "\n\n");

            writer.write("Reachability: " +
                    (reachable ? "Reachable" : "No response") + "\n\n");

            writer.write("OPEN PORTS:\n");

            for (int port : openPorts) {
                writer.write("[OPEN] " + port + "\n");
            }

            writer.write("\nCLOSED PORTS:\n");

            for (int port : closedPorts) {
                writer.write("[CLOSED] " + port + "\n");
            }

            writer.write("\n====================================\n");
            writer.write("             END OF REPORT\n");
            writer.write("====================================\n");

            System.out.println("\n[+] Report saved: " + filePath);

        } catch (IOException e) {

            System.out.println("[!] Could not generate report: " +
                    e.getMessage());
        }
    }
}
