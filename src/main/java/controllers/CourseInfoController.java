package controllers;

import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Course;
import models.Dataset;
import models.PredictionResult;
import services.CourseCatalogService;
import services.DataService;
import services.PredictionService;

public class CourseInfoController {

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> codeColumn;

    @FXML
    private TableColumn<Course, String> nameColumn;

    @FXML
    private TableColumn<Course, String> prereqColumn;

    @FXML
    private TableColumn<Course, String> semesterColumn;

    @FXML
    private TableColumn<Course, Number> predictionColumn;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        prereqColumn.setCellValueFactory(new PropertyValueFactory<>("prerequisites"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semesters"));
        predictionColumn.setCellValueFactory(new PropertyValueFactory<>("predictedEnrollment"));

        CourseCatalogService service = new CourseCatalogService();
        DataService dataService = new DataService();
        PredictionService predictionService = new PredictionService();

        List<Course> courses = service.getCourses();

        List<Dataset> allData = dataService.loadData("mock_enrollment_data.csv");

        for (Course course : courses) {

            List<Dataset> courseData = allData.stream()
                    .filter(row -> row.getCourseID().equalsIgnoreCase(course.getCode()))
                    .toList();

            if (!courseData.isEmpty()) {
                PredictionResult result = predictionService.executionPrediction(courseData, 0);

                course.setPredictedEnrollment(
                        result.getPredictedEnrollment());
            }
        }

        courseTable.getItems().addAll(courses);
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