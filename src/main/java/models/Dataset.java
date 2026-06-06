package models;

public class Dataset
{
    private String courseID;
    private int year;
    private String semester;
    private int enrollmentCount;

    public Dataset(String courseID, int year, String semester, int enrollmentCount)
    {
        this.courseID = courseID;
        this.year = year;
        this.semester = semester;
        this.enrollmentCount = enrollmentCount;
    }

    public String getCourseID() {return courseID;}
    public int getYear() {return year;}
    public String getSemester() {return semester;}
    public int getEnrollmentCount() {return enrollmentCount;}
}
