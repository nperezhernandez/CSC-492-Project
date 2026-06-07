package services.predicators;

import models.Dataset;
import java.util.List;

public class MovingAveragePredictor implements Predictor
{
    @Override
    public double predict(List<Dataset> data)
    {
        if (data == null || data.isEmpty()) return 0.0;

        double sum = 0;
        for (Dataset d : data)
        {
            sum += d.getEnrollmentCount();
        }

        return sum / data.size();
    }
}
