package services;

import models.Dataset;
import models.PredictionResult;
import services.predicators.LinearRegressionPredictor;
import services.predicators.MovingAveragePredictor;
import services.predicators.Predictor;

import java.util.List;

public class PredictionService {

    public PredictionResult executionPrediction(List<Dataset> data, int yearsToConsider) {
        long startTime = System.currentTimeMillis();

        List<Dataset> filteredData = data;

        if (yearsToConsider > 0 && data != null && !data.isEmpty()) {
            int latestYear = data.stream()
                    .mapToInt(Dataset::getYear)
                    .max()
                    .orElse(0);

            int cutoffYear = latestYear - yearsToConsider;

            filteredData = data.stream()
                    .filter(row -> row.getYear() >= cutoffYear)
                    .toList();
        }
        long uniqueYears = filteredData.stream()
                .map(Dataset::getYear)
                .distinct()
                .count();

        Predictor predictor;
        String algorithmName;
        String warningMessage = "";

        if (uniqueYears < 5) {
            predictor = new MovingAveragePredictor();
            algorithmName = "Moving Average";

            warningMessage = "Limited historical data available. Using Moving Average.";
        } else {
            predictor = new LinearRegressionPredictor();
            algorithmName = "Linear Regression";
        }

        double prediction = predictor.predict(filteredData);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        return new PredictionResult(prediction, algorithmName, executionTime, warningMessage);
    }
}