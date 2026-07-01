package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.CapacityReport;
import services.CapacityPlannerService;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CapacityController {

    private CapacityPlannerService capacityService = new CapacityPlannerService();

    @FXML
    private TextField basePredictionField;

    @FXML
    private TextField overrideField;

    @FXML
    private TextField sectionsField;

    @FXML
    private TextField seatsField;

    @FXML
    private Label reportLabel;

    @FXML
    private Button backButton;

    @FXML
    private Circle statusCircle;

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

        Stage stage = (Stage) basePredictionField.getScene().getWindow(); 
        stage.setScene(scene);
        stage.setTitle(title);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    private void handleGenerateReport() {
        try {
            double basePrediction = Double.parseDouble(basePredictionField.getText());
            int override = Integer.parseInt(overrideField.getText());
            int sections = Integer.parseInt(sectionsField.getText());
            int seats = Integer.parseInt(seatsField.getText());

            CapacityReport report = capacityService.generateReport(
                    basePrediction,
                    override,
                    sections,
                    seats
            );

            reportLabel.setText(
                    "Adjusted Demand: " + String.format("%.0f", report.getAdjustedDemand()) +
                    "\nTotal Capacity: " + report.getTotalCapacity() +
                    "\nDifference: " + String.format("%.0f", report.getNetDifference()) +
                    "\n" + report.getWarningMessage()
            );

            double difference = report.getNetDifference();

            if (difference < 0) {
                statusCircle.setFill(Color.RED);
            } else if (difference > 30) {
                statusCircle.setFill(Color.YELLOW);
            } else {
                statusCircle.setFill(Color.GREEN);
            }

        } catch (NumberFormatException e) {
            reportLabel.setText("Please enter valid numbers in all fields.");
        }
    }

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