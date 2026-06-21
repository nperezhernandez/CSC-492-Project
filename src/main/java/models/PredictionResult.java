package models;

public class PredictionResult
{
    private double predictedEnrollment;
    private String algorithmUsed;
    private long executionTimeMs;

    public PredictionResult(double predictedEnrollment, String algorithmUsed, long executionTimeMs)
    {
        this.predictedEnrollment = predictedEnrollment;
        this.algorithmUsed = algorithmUsed;
        this.executionTimeMs = executionTimeMs;
    }

    public double getPredictedEnrollment() { return predictedEnrollment; }
    public String getAlgorithmUsed() { return algorithmUsed; }
    public long getExecutionTimeMs() { return executionTimeMs; }
}
