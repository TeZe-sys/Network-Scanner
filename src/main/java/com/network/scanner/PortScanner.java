
package com.network.scanner;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortScanner {

    private static final int TIMEOUT = 500;
    private static final int THREAD_COUNT = 5;

    private final List<Integer> openPorts =
            Collections.synchronizedList(new ArrayList<>());

    private final List<Integer> closedPorts =
            Collections.synchronizedList(new ArrayList<>());

    public void scanPort(String ip, int port) {

        try (Socket socket = new Socket()) {

            socket.connect(
                new InetSocketAddress(ip, port),
                TIMEOUT
            );

            openPorts.add(port);
            System.out.println("[OPEN] " + port);

        } catch (ConnectException e) {

            closedPorts.add(port);
            System.out.println("[CLOSED] " + port);

        } catch (SocketTimeoutException e) {

            System.out.println("[NO RESPONSE / FILTERED] " + port);

        } catch (IOException e) {

            System.out.println("[ERROR] " + port);
        }
    }

    public void scanCommonPorts(String ip) {

        openPorts.clear();
        closedPorts.clear();

        int[] ports = {
            21, 22, 23, 25, 53,
            80, 110, 143, 443,
            445, 3306, 5432, 8080
        };

        System.out.println("\n================================");
        System.out.println("      MULTITHREADED PORT SCAN");
        System.out.println("================================");

        ExecutorService executor =
                Executors.newFixedThreadPool(THREAD_COUNT);

        for (int port : ports) {
            executor.submit(() -> scanPort(ip, port));
        }

        executor.shutdown();

        try {

            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }

        } catch (InterruptedException e) {

            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("================================");
        System.out.println("          SCAN COMPLETE");
        System.out.println("================================");
    }

    public List<Integer> getOpenPorts() {
        return new ArrayList<>(openPorts);
    }

    public List<Integer> getClosedPorts() {
        return new ArrayList<>(closedPorts);
    }
}
