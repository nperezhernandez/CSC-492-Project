package services.predicators;

import models.Dataset;
import java.util.List;

public interface Predictor
{
    double predict(List<Dataset> data);
}
