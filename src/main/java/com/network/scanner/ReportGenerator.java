
package com.network.scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportGenerator {

    public void generateReport(
            String ip,
            boolean reachable,
            List<Integer> openPorts,
            List<Integer> closedPorts,
            List<Integer> filteredPorts,
            List<Integer> errorPorts) {

        String directory = "reports";

        String date = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        );

        try {

            Files.createDirectories(Paths.get(directory));

            // Generate TXT report
            try (FileWriter writer =
                         new FileWriter(directory + "/scan-report.txt")) {

                writer.write("====================================\n");
                writer.write("          NETWORK SCAN REPORT\n");
                writer.write("====================================\n\n");

                writer.write("Target IP: " + ip + "\n");
                writer.write("Scan Date: " + date + "\n\n");

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

                writer.write("\nFILTERED / NO RESPONSE PORTS:\n");
                for (int port : filteredPorts) {
                    writer.write("[NO RESPONSE / FILTERED] " + port + "\n");
                }

                writer.write("\nERROR PORTS:\n");
                for (int port : errorPorts) {
                    writer.write("[ERROR] " + port + "\n");
                }

                writer.write("\n====================================\n");
                writer.write("             END OF REPORT\n");
                writer.write("====================================\n");
            }

            // Generate CSV report
            try (FileWriter writer =
                         new FileWriter(directory + "/scan-report.csv")) {

                writer.write(
                        "Target IP,Scan Date,Reachability,Port,Status\n"
                );

                writeCsvRows(writer, ip, date, reachable, openPorts, "OPEN");
                writeCsvRows(writer, ip, date, reachable, closedPorts, "CLOSED");
                writeCsvRows(writer, ip, date, reachable, filteredPorts,
                        "NO RESPONSE / FILTERED");
                writeCsvRows(writer, ip, date, reachable, errorPorts, "ERROR");
            }

            System.out.println("\n[+] TXT report saved: reports/scan-report.txt");
            System.out.println("[+] CSV report saved: reports/scan-report.csv");

        } catch (IOException e) {

            System.out.println("[!] Could not generate reports: " +
                    e.getMessage());
        }
    }

    private void writeCsvRows(
            FileWriter writer,
            String ip,
            String date,
            boolean reachable,
            List<Integer> ports,
            String status) throws IOException {

        for (int port : ports) {

            writer.write(
                    ip + "," + date + "," +
                    (reachable ? "Reachable" : "No response") +
                    "," + port + "," + status + "\n"
            );
        }
    }
}
