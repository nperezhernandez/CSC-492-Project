package services;

import models.Dataset;
import models.PredictionResult;
import services.predicators.LinearRegressionPredictor;
import services.predicators.MovingAveragePredictor;
import services.predicators.Predictor;

import java.util.List;
import java.util.stream.Collectors;

public class PredictionService {

    public PredictionResult executionPrediction(List<Dataset> data, int yearsToConsider)
    {
        long startTime = System.currentTimeMillis();

        List<Dataset> filteredData = data;

        if (data != null && !data.isEmpty() && yearsToConsider > 0)
        {
            int maxYear = data.stream().mapToInt(Dataset::getYear).max().orElse(0);

            int cutoffYear = maxYear - yearsToConsider;

            filteredData = data.stream()
                    .filter(d -> d.getYear() > cutoffYear)
                    .collect(Collectors.toList());
        }

        Predictor predictor;
        String algorithmName;
        String warningMessage = "";

        if (data == null || data.size() < 3)
        {
            predictor = new MovingAveragePredictor();
            algorithmName = "Moving Average";

            int dataSize = (data == null) ? 0 : data.size();
            warningMessage = "Warning: Low historical data (" + dataSize + " terms). Prediction may be unreliable.";
        } else {
            predictor = new LinearRegressionPredictor();
            algorithmName = "Linear Regression";
        }

        double prediction = predictor.predict(data);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        return new PredictionResult(prediction, algorithmName, executionTime, warningMessage);
    }
}