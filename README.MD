# Network Scanner

A multithreaded TCP network scanner built using Java and Maven. It checks whether a target host is reachable, scans 13 commonly used TCP ports, and generates TXT and CSV scan reports.

## Features

- **Host Reachability:** Checks whether the target host responds.
- **TCP Port Scanning:** Scans 13 commonly used ports.
- **Multithreading:** Uses Java ExecutorService with a fixed thread pool of 5 threads.
- **Port Status Detection:** Identifies open, closed, timeout/no-response, and error outcomes.
- **Report Generation:** Automatically generates TXT and CSV reports.
- **Unit Testing:** Tests open-port and closed-port detection.

## Technologies Used

- Java 25
- Maven
- Java Networking API
- ExecutorService
- JUnit 4

## Ports Scanned

| Port | Common Service |
|---|---|
| 21 | FTP |
| 22 | SSH |
| 23 | Telnet |
| 25 | SMTP |
| 53 | DNS |
| 80 | HTTP |
| 110 | POP3 |
| 143 | IMAP |
| 443 | HTTPS |
| 445 | SMB |
| 3306 | MySQL |
| 5432 | PostgreSQL |
| 8080 | HTTP Alternate |

## Project Structure

```text
network-scanner/
├── src/
│   ├── main/java/com/network/scanner/
│   │   ├── App.java
│   │   ├── PortScanner.java
│   │   └── ReportGenerator.java
│   └── test/java/com/network/scanner/
│       └── AppTest.java
├── reports/
│   ├── scan-report.txt
│   └── scan-report.csv
├── pom.xml
└── README.MD
```

## Requirements

- Java JDK 25 or compatible JDK supporting Java 25.
- Apache Maven.
- Git (optional, for cloning the repository).

## How to Run

**1. Clone the repository**

```bash
git clone https://github.com/TeZe-sys/Network-Scanner.git
```

**2. Open the project directory**

```bash
cd Network-Scanner
```

**3. Build and test**

```bash
mvn clean test
```

**4. Run the scanner**

```bash
java -cp target/classes com.network.scanner.App
```

**5. Enter a target IP address**

For a local test, enter:

```text
127.0.0.1
```

## Sample Output

```text
================================
         NETWORK SCANNER
================================
Enter target IP address: 127.0.0.1

Scanning target: 127.0.0.1
[+] Device is reachable

[OPEN] 445
[CLOSED] 21
[CLOSED] 22

================================
          SCAN COMPLETE
================================

[+] TXT report saved: reports/scan-report.txt
[+] CSV report saved: reports/scan-report.csv
```

*Actual results depend on the target system and its running services.*

## Generated Reports

The scanner automatically creates a `reports` directory containing:

- `scan-report.txt` — Human-readable scan results.
- `scan-report.csv` — Structured scan results for spreadsheet analysis.

## Testing

Run the unit tests using:

```bash
mvn clean test
```

Current tests verify:
- Detection of an open TCP port.
- Detection of a closed TCP port.

## Limitations

- Scans only the 13 predefined TCP ports.
- Does not perform UDP scanning.
- A timeout/no-response result does not conclusively prove that a port is filtered.
- Does not perform vulnerability detection or exploitation.

## Security and Responsible Use

Use this tool only on your own devices, systems, or networks for which you have explicit authorization. Unauthorized scanning may violate applicable laws or network policies.

## Author

**TeZe**

GitHub: https://github.com/TeZe-sys

LinkedIn: https://www.linkedin.com/in/sparsh-yadav-5098743a8/
