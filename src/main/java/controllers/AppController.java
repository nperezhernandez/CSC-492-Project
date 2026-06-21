package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import models.CapacityReport;
import models.Dataset;
import models.PredictionResult;
import services.DataService;
import services.PredictionService;
import services.CapacityPlannerService;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    private static final String DATA_FILE = "mock_enrollment_data.csv";

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
        List<String> uniqueCourses = dataService.getUniqueCourseIDs(DATA_FILE);
        courseDropdown.getItems().addAll(uniqueCourses);
    }

    @FXML
    private void handlePredict() {
        String selectedCourse = courseDropdown.getValue();

        if (selectedCourse == null) {
            resultLabel.setText("Please select a course.");
            return;
        }

        PredictionResult result = getPredictionForCourse(selectedCourse, DATA_FILE);

        resultLabel.setText(
                "Predicted enrollment: " + String.format("%.0f", result.getPredictedEnrollment()) +
                        "\nAlgorithm Used: " + result.getAlgorithmUsed() +
                        "\nExecution Time: " + result.getExecutionTimeMs() + "ms"
        );
    }

    public PredictionResult getPredictionForCourse(String courseID, String filePath) {
        List<Dataset> allData = dataService.loadData(filePath);
        List<Dataset> filteredData = new ArrayList<>();

        for (Dataset row : allData) {
            if (row.getCourseID().equalsIgnoreCase(courseID)) {
                filteredData.add(row);
            }
        }
        return predictionService.executionPrediction(filteredData);
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
