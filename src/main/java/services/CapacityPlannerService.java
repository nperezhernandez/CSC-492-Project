package services;

import models.CapacityReport;

public class CapacityPlannerService
{
    private AuditLoggerService auditLogger = new AuditLoggerService();

    public CapacityReport generateReport(double basePrediction, int trendOverride, int sections, int seatsPerSection)
    {
        double overrideMultiplier = 1.0 + (trendOverride / 100.0);
        double adjustedDemand = basePrediction * overrideMultiplier;

        auditLogger.logOverrideAction(basePrediction, trendOverride, adjustedDemand);

        int totalCapacity = sections * seatsPerSection;

        double netDifference = totalCapacity - adjustedDemand;

        String warningMessage;
        if (netDifference < 0)
        {
            warningMessage = "Warning: Demand exceeds capacity. Consider adding a section.";
        } else if (netDifference > 30)
        {
            warningMessage = "Warning: High excess capacity detected. Consider reducing sections.";
        } else
        {
            warningMessage = "Supply matches expected demand.";
        }
        return new CapacityReport(adjustedDemand, totalCapacity, netDifference, warningMessage);
    }
}