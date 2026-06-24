package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private void openDashboard() throws Exception {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/dashboard.fxml"));

        Scene scene =
                new Scene(loader.load(), 800, 700);

        Stage stage =
                (Stage) javafx.stage.Stage.getWindows().get(0);

        stage.setScene(scene);
    }

    @FXML
    private void openCapacityPlanner() {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/capacity.fxml"));
        Scene scene = new Scene(loader.load(), 800, 700);

        Stage stage = (Stage) javafx.stage.Stage.getWindows().get(0);
        stage.setScene(scene);
        stage.setTitle("Capacity Planner");

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void openReports() {
        System.out.println("Reports clicked.");
    }

    @FXML
    private void exitApplication() {
        System.exit(0);
    }
}