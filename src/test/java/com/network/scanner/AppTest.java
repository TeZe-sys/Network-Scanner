
package com.network.scanner;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AppTest {

    @Test
    public void detectsOpenPort() throws IOException {

        try (ServerSocket server = new ServerSocket(0)) {

            int port = server.getLocalPort();

            PortScanner scanner = new PortScanner();

            scanner.scanPort("127.0.0.1", port);

            assertTrue(
                    "Port should be detected as open",
                    scanner.getOpenPorts().contains(port)
            );
        }
    }

    @Test
    public void detectsClosedPort() throws IOException {

        int port;

        try (ServerSocket server = new ServerSocket(0)) {
            port = server.getLocalPort();
        }

        PortScanner scanner = new PortScanner();

        scanner.scanPort("127.0.0.1", port);

        assertTrue(
                "Port should be detected as closed",
                scanner.getClosedPorts().contains(port)
        );
    }
}
