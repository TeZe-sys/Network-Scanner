
package com.network.scanner;

import java.net.InetAddress;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("         NETWORK SCANNER");
        System.out.println("================================");

        System.out.print("Enter target IP address: ");

        String ip = input.nextLine().trim();

        try {

            InetAddress address = InetAddress.getByName(ip);

            ip = address.getHostAddress();

            System.out.println("\nScanning target: " + ip);

            boolean reachable = address.isReachable(2000);

            if (reachable) {
                System.out.println("[+] Device is reachable");
            } else {
                System.out.println("[-] No reachability response received");
            }

            PortScanner portScanner = new PortScanner();

            portScanner.scanCommonPorts(ip);

            ReportGenerator reportGenerator = new ReportGenerator();

            reportGenerator.generateReport(
                    ip,
                    reachable,
                    portScanner.getOpenPorts(),
                    portScanner.getClosedPorts(),
                    portScanner.getFilteredPorts(),
                    portScanner.getErrorPorts()
            );

        } catch (Exception e) {

            System.out.println("[!] Error: " + e.getMessage());

        }

        input.close();
    }
}
