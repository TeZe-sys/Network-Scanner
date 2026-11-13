
package com.network.scanner;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class PortScanner {

    private static final int TIMEOUT = 500;

    public void scanPort(String ip, int port) {

        try (Socket socket = new Socket()) {

            socket.connect(
                new InetSocketAddress(ip, port),
                TIMEOUT
            );

            System.out.println("[OPEN] " + port);

        } catch (ConnectException e) {

            System.out.println("[CLOSED] " + port);

        } catch (SocketTimeoutException e) {

            System.out.println("[NO RESPONSE / FILTERED] " + port);

        } catch (IOException e) {

            System.out.println("[ERROR] " + port + " - " + e.getMessage());

        }
    }

    public void scanCommonPorts(String ip) {

        int[] ports = {
            21, 22, 23, 25, 53,
            80, 110, 143, 443,
            445, 3306, 5432, 8080
        };

        System.out.println("\n================================");
        System.out.println("          TCP PORT SCAN");
        System.out.println("================================");

        for (int port : ports) {

            scanPort(ip, port);

        }

        System.out.println("================================");
        System.out.println("          SCAN COMPLETE");
        System.out.println("================================");
    }
}
