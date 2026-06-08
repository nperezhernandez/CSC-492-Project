package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import models.CapacityReport;
import models.Dataset;
import services.DataService;
import services.PredictionService;
import services.CapacityPlannerService;

import java.util.List;

public class AppController {

    private DataService dataService;
    private PredictionService predictionService;
    private CapacityPlannerService capacityService;

    @FXML
    private ComboBox<String> courseDropdown;

    @FXML
    private Label resultLabel;

    public AppController() {
        this.dataService = new DataService();
        this.predictionService = new PredictionService();
        this.capacityService = new CapacityPlannerService();
    }

    @FXML
    public void initialize() {
        courseDropdown.getItems().addAll(
                "CSC 121",
                "CSC 221",
                "CSC 311",
                "CSC 492"
        );
    }

    @FXML
    private void handlePredict() {

        String selectedCourse = courseDropdown.getValue();

        if (selectedCourse == null) {
            resultLabel.setText("Please select a course.");
            return;
        }

        resultLabel.setText(
                "Predicted enrollment for "
                        + selectedCourse
                        + ": TBD"
        );
    }

    public double getPredictionForCourse(String courseID, String filePath) {
        List<Dataset> allData = dataService.loadData(filePath);
        return predictionService.executionPrediction(allData);
    }

    public void handleCourseSelection(String courseID) {
        System.out.println("UI triggered course selection for: " + courseID);
    }

    public CapacityReport handleCapacitySimulation(
            double basePrediction,
            int override,
            int sections,
            int seats) {

        System.out.println("UI triggered capacity simulation.");

        return capacityService.generateReport(
                basePrediction,
                override,
                sections,
                seats
        );
    }
}