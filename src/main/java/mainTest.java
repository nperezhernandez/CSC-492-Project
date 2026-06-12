import controllers.AppController;
import models.CapacityReport;

public class mainTest
{
    public static void main(String[] args)
    {
        AppController controller = new AppController();

        String filePath = "mock_enrollment_data.csv";

        double prediction = controller.getPredictionForCourse("CSC 301", filePath);

        System.out.println("Prediction: " + prediction);

        CapacityReport report = controller.handleCapacitySimulation(prediction, 10, 4, 35);

        System.out.println("Capacity Simulation Results:");
        System.out.println("Adjusted Demand: " + report.getAdjustedDemand());
        System.out.println("Total Capacity: " + report.getTotalCapacity());
        System.out.println("Status: " + report.getWarningMessage());
    }
}
