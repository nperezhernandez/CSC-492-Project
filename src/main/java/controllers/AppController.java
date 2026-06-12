package controllers;

import models.CapacityReport;
import models.Dataset;
import services.DataService;
import services.PredictionService;
import services.CapacityPlannerService;
import java.util.*;

public class AppController
{
    private DataService dataService;
    private PredictionService predictionService;
    private CapacityPlannerService capacityService;

    public AppController()
    {
        this.dataService = new DataService();
        this.predictionService = new PredictionService();
        this.capacityService = new CapacityPlannerService();
    }

    public double getPredictionForCourse(String courseID, String filePath)
    {
        List<Dataset> allData = dataService.loadData(filePath);
        List<Dataset> filteredData = new ArrayList<>();

        for (Dataset row : allData)
        {
            if (row.getCourseID().equalsIgnoreCase(courseID))
            {
                filteredData.add(row);
            }
        }
        return predictionService.executionPrediction(filteredData);
    }

    public void handleCourseSelection(String courseID)
    {
        System.out.println("UI triggered course selection for: " + courseID);
    }

    public CapacityReport handleCapacitySimulation(double basePrediction, int override, int sections, int seats)
    {
        System.out.println("UI triggered capacity simulation.");
        return capacityService.generateReport(basePrediction, override, sections, seats);
    }
}
