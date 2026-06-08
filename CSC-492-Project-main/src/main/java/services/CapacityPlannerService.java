package services;

import models.CapacityReport;

public class CapacityPlannerService
{
    public double calculateAdjustedDemand(double basePrediction, int overrideAmount)
    {
        //  add math logic later
        return basePrediction + overrideAmount;

    }

    public int calculateTotalCapacity(int sections, int seats)
    {
        // add math logic later
        return sections * seats;
    }

    public CapacityReport generateReport(double prediction, int override, int sections, int seats)
    {
        // add heavy logic later
        return new CapacityReport(0.0, 0, 0.0, "Report Generated");
    }
}

