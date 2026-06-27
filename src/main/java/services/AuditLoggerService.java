package services;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLoggerService
{
    private static final String LOG_FILE = "audit_log.csv";

    public void logOverrideAction(double basePrediction, int trendOverride, double adjustedDemand)
    {
        if (trendOverride == 0)
        {
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true)))
        {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            writer.printf("%s, %.2f, %d%%, %.2f%n", timestamp, basePrediction, trendOverride, adjustedDemand);

        } catch (IOException e)
        {
            System.err.println("Failed to write to audit log: " + e.getMessage());
        }
    }
}
