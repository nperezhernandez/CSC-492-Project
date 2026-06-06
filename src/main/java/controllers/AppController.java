package controllers;

import models.CapacityReport;
import services.CapacityPlannerService;

public class AppController
{
    private CapacityPlannerService capacityService;

    // private DataService dataService;
    // private PredictionService predictionService;

    public AppController()
    {
        this.capacityService = new CapacityPlannerService();
    }

    public void handleCourseSelection(String courseID)
    {
        System.out.println("UI triggered course selection for: " + courseID);
    }

    public CapacityReport handleCapacitySimulation(double basePrediction, int override, int sections, int seats)
    {
        System.out.println("UI triggered capacity simulation");
        return capacityService.generateReport(basePrediction, override, sections, seats);
    }
}
