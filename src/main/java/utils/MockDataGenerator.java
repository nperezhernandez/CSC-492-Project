package utils;

import java.io.*;
import java.util.*;

public class MockDataGenerator
{
    public static void main(String[] args)
    {
        String fileName = "mock_enrollment_data.csv";

        String[] courses = {"CSC 301", "CSC 311", "CSC 481", "CSC 492"};
        String[] semesters = {"Spring", "Summer", "Fall", "Winter"};
        int startYear = 2021;
        int endYear = 2026;
        Random random = new Random();

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("course_id, year, semester, enrollment_count\n");

            for (String course : courses) {
                int baseEnrollment = getBaseEnrollment(course);

                for (int year = startYear; year <= endYear; year++) {
                    for (String semester : semesters) {
                        int fluctuation = random.nextInt(31) - 15;
                        int finalEnrollment = baseEnrollment + fluctuation;

                        writer.append(course).append(",")
                                .append(String.valueOf(year)).append(",")
                                .append(semester).append(",")
                                .append(String.valueOf(finalEnrollment)).append("\n");
                    }
                }
            }
            System.out.println("Success! Generated data file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error generating file: " + e.getMessage());
        }
    }

    private static int getBaseEnrollment(String course)
    {
        switch (course)
        {
            case "CSC 301": return 120;
            case "CSC 311": return 100;
            case "CSC 481": return 75;
            case "CSC 491": return 40;
            default: return 0;
        }
    }
}
