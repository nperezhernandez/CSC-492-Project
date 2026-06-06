package services;

import models.Dataset;
import services.predicators.Predictor;
import services.predicators.MovingAveragePredictor;
import java.util.*;

public class PredictionService
{
    private Predictor currentPredictor;

   public PredictionService()
   {
       this.currentPredictor = new MovingAveragePredictor();
   }

   public void determineAlgorithm(List<Dataset> data)
   {
       if (data.size() < 4)
       {
           this.currentPredictor = new MovingAveragePredictor();
       } else {
           // add liner regression after made
           this.currentPredictor = new MovingAveragePredictor();
       }
   }

   public double executionPrediction(List<Dataset> data)
   {
       return currentPredictor.predict(data);
   }
}
