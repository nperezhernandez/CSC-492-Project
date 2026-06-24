package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

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

    @FXML
    private LineChart<String, Number> enrollmentChart;

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
                        "\nExecution Time: " + result.getExecutionTimeMs() + "ms");

        enrollmentChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(selectedCourse);

        List<Dataset> allData = dataService.loadData(DATA_FILE);

        for (Dataset row : allData) {
            if (row.getCourseID().equalsIgnoreCase(selectedCourse)) {

                String semesterLabel = row.getSemester() + "\n" + row.getYear();

                series.getData().add(
                        new XYChart.Data<>(
                                semesterLabel,
                                row.getEnrollmentCount()));
            }
        }

        enrollmentChart.getData().add(series);

        XYChart.Series<String, Number> predictionSeries = new XYChart.Series<>();

        predictionSeries.setName("Prediction");

        predictionSeries.getData().add(
                new XYChart.Data<>(
                        "Next Semester",
                        result.getPredictedEnrollment()));

        enrollmentChart.getData().add(predictionSeries);
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
                seats);
    }

    @FXML
    private Button backButton;

    @FXML
    public void goBackToWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/welcome.fxml"));
            Scene scene = new Scene(loader.load(), 800, 700);

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Welcome");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
