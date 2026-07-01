package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
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

//import java.io.IOException;
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

    @FXML
    private ComboBox<Integer> yearRangeDropdown;


@FXML
public void openDashboard() {
    switchScene("/dashboard.fxml", "Enrollment Dashboard");
}

@FXML
public void openCapacityPlanner() {
    switchScene("/capacity.fxml", "Capacity Planner");
}

@FXML
public void openCourseInformation() {
    switchScene("/courses.fxml", "Course Information");
}

@FXML
public void logout() {
    switchScene("/login.fxml", "Login");
}

private void switchScene(String fxmlFile, String title) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Scene scene = new Scene(loader.load(), 800, 700);

        Stage stage = (Stage) courseDropdown.getScene().getWindow(); // change this control per controller
        stage.setScene(scene);
        stage.setTitle(title);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private int semesterOrder(String semester) {
        switch (semester.toLowerCase()) {
            case "spring":
                return 1;
            case "summer":
                return 2;
            case "fall":
                return 3;
            case "winter":
                return 4;
            default:
                return 5;
        }
    }

   

    public AppController() {
        this.dataService = new DataService();
        this.predictionService = new PredictionService();
        this.capacityService = new CapacityPlannerService();
    }

    @FXML
    public void initialize() {
        List<String> uniqueCourses = dataService.getUniqueCourseIDs(DATA_FILE);
        courseDropdown.getItems().addAll(uniqueCourses);
        yearRangeDropdown.getItems().addAll(0, 3, 5);
        yearRangeDropdown.setValue(0);
    }

    private void updateEnrollmentChart(String selectedCourse, int yearsToConsider) {
        enrollmentChart.getData().clear();

        List<Dataset> allData = dataService.loadData(DATA_FILE);
        List<Dataset> courseData = new ArrayList<>();

        for (Dataset row : allData) {
            if (row.getCourseID().equalsIgnoreCase(selectedCourse)) {
                courseData.add(row);
            }
        }

        if (yearsToConsider > 0 && !courseData.isEmpty()) {
            int latestYear = courseData.stream()
                    .mapToInt(Dataset::getYear)
                    .max()
                    .orElse(0);

            int cutoffYear = latestYear - yearsToConsider;

            courseData = courseData.stream()
                    .filter(row -> row.getYear() >= cutoffYear)
                    .toList();
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(selectedCourse + " Enrollment");

        courseData = courseData.stream()
                .sorted((a, b) -> {
                    int yearCompare = Integer.compare(a.getYear(), b.getYear());

                    if (yearCompare != 0) {
                        return yearCompare;
                    }

                    return Integer.compare(
                            semesterOrder(a.getSemester()),
                            semesterOrder(b.getSemester()));
                })
                .toList();
        for (Dataset row : courseData) {
            String semesterLabel = row.getSemester() + "\n" + row.getYear();

            series.getData().add(
                    new XYChart.Data<>(
                            semesterLabel,
                            row.getEnrollmentCount()));
        }

        enrollmentChart.getData().add(series);
    }

    @FXML
    private void handlePredict() {
        String selectedCourse = courseDropdown.getValue();

        if (selectedCourse == null) {
            resultLabel.setText("Please select a course.");
            return;
        }

        int yearsToConsider = yearRangeDropdown.getValue();

        PredictionResult result = getPredictionForCourse(
                selectedCourse,
                DATA_FILE,
                yearsToConsider);

        resultLabel.setText(
                "Predicted enrollment: " + String.format("%.0f", result.getPredictedEnrollment()) +
                        "\nAlgorithm Used: " + result.getAlgorithmUsed() +
                        "\nExecution Time: " + result.getExecutionTimeMs() + "ms");

        updateEnrollmentChart(selectedCourse, yearsToConsider);

        XYChart.Series<String, Number> predictionSeries = new XYChart.Series<>();

        predictionSeries.setName("Prediction");

        predictionSeries.getData().add(
                new XYChart.Data<>(
                        "Next Semester",
                        result.getPredictedEnrollment()));

        enrollmentChart.getData().add(predictionSeries);
    }

    public PredictionResult getPredictionForCourse(String courseID, String filePath, int yearsToConsider) {
        List<Dataset> allData = dataService.loadData(filePath);
        List<Dataset> filteredData = new ArrayList<>();

        for (Dataset row : allData) {
            if (row.getCourseID().equalsIgnoreCase(courseID)) {
                filteredData.add(row);
            }
        }
        return predictionService.executionPrediction(filteredData, yearsToConsider);
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
