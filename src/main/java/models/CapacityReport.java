package models;

public class CapacityReport
{
    private double adjustedDemand;
    private int totalCapacity;
    private double netDifference;
    private String warningMessage;

    public CapacityReport(double adjustedDemand, int totalCapacity, double netDifference, String warningMessage)
    {
        this.adjustedDemand = adjustedDemand;
        this.totalCapacity = totalCapacity;
        this.netDifference = netDifference;
        this.warningMessage = warningMessage;
    }

    public double getAdjustedDemand() {return adjustedDemand;}
    public int getTotalCapacity() {return totalCapacity;}
    public double getNetDifference() {return netDifference;}
    public String getWarningMessage() {return warningMessage;}
}
