package models;

public class Course {
    private String code;
    private String name;
    private String prerequisites;
    private String semesters;
    private double predictedEnrollment;

    public Course(String code, String name, String prerequisites, String semesters) {
        this.code = code;
        this.name = name;
        this.prerequisites = prerequisites;
        this.semesters = semesters;
    }

    public double getPredictedEnrollment() {
        return predictedEnrollment;
    }

    public void setPredictedEnrollment(double predictedEnrollment) {
        this.predictedEnrollment = predictedEnrollment;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public String getSemesters() {
        return semesters;
    }
}