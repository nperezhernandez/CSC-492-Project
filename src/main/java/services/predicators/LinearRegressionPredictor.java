package services.predicators;

import models.Dataset;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.util.List;

public class LinearRegressionPredictor implements Predictor
{
    @Override
    public double predict(List<Dataset> data)
    {
        if (data == null || data.isEmpty())
        {
            return 0.0;
        }

        SimpleRegression regression = new SimpleRegression();

        int timeIndex = 1;
        for (Dataset record : data)
        {
            regression.addData(timeIndex, record.getEnrollmentCount());
            timeIndex++;
        }

        double prediction = regression.predict(timeIndex);

        return Math.max(0, prediction);
    }

}
