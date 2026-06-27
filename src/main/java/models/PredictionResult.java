package models;

public class PredictionResult
{
    private double predictedEnrollment;
    private String algorithmUsed;
    private long executionTimeMs;
    private String dataWarning;

    public PredictionResult(double predictedEnrollment, String algorithmUsed, long executionTimeMs,  String dataWarning)
    {
        this.predictedEnrollment = predictedEnrollment;
        this.algorithmUsed = algorithmUsed;
        this.executionTimeMs = executionTimeMs;
        this.dataWarning = dataWarning;
    }

    public double getPredictedEnrollment() { return predictedEnrollment; }
    public String getAlgorithmUsed() { return algorithmUsed; }
    public long getExecutionTimeMs() { return executionTimeMs; }
    public String getDataWarning() { return dataWarning; }
}
