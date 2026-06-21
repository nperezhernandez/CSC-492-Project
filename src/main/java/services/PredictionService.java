package services;

import models.Dataset;
import models.PredictionResult;
import services.predicators.LinearRegressionPredictor;
import services.predicators.MovingAveragePredictor;
import services.predicators.Predictor;
import java.util.List;

public class PredictionService {

    private Predictor movingAverage = new MovingAveragePredictor();
    private Predictor linearRegression = new LinearRegressionPredictor();

    public PredictionResult executionPrediction(List<Dataset> data)
    {
        if (data == null || data.isEmpty())
        {
            return new PredictionResult(0.0, "Insufficient Data", 0);
        }

        double prediction;
        String algorithmName;

        long startTime = System.nanoTime();

        if (data.size() < 3)
        {
            algorithmName = "Moving Average";
            prediction = movingAverage.predict(data);
        } else {
            algorithmName = "Linear Regression (Apache Math)";
            prediction = linearRegression.predict(data);
        }

        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1000000;

        return new PredictionResult(prediction, algorithmName, durationMs);
    }
}