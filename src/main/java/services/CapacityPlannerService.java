package services;

import models.CapacityReport;

public class CapacityPlannerService
{
    public CapacityReport generateReport(double basePrediction, int trendOverride, int sections, int seatsPerSection)
    {
        double overrideMultiplier = 1.0 + (trendOverride / 100.0);
        double adjustedDemand = basePrediction * overrideMultiplier;

        int totalCapacity = sections * seatsPerSection;

        double netDifference = totalCapacity - adjustedDemand;

        String warningMessage;
        if (netDifference < 0)
        {
            warningMessage = "Warning: Demand exceeds capacity. Consider adding a section.";
        } else if (netDifference > 30)
        {
            warningMessage = "Note: High excess capacity detected. Consider reducing sections.";
        } else
        {
            warningMessage = "Supply matches expected demand.";
        }
        return new CapacityReport(adjustedDemand, totalCapacity, netDifference, warningMessage);
    }
}