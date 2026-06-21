package utils;

import java.io.*;
import java.util.*;

public class MockDataGenerator
{
    public static void main(String[] args)
    {
        String fileName = "mock_enrollment_data.csv";

        // The complete, official CSUDH Computer Science Course Catalog
        String[] courses = {
                // 100-Level
                "CSC 101", "CSC 111", "CSC 112", "CSC 115", "CSC 116", "CSC 121", "CSC 123", "CSC 195",
                // 200-Level
                "CSC 221", "CSC 251", "CSC 255", "CSC 281", "CSC 295",
                // 300-Level
                "CSC 300", "CSC 301", "CSC 311", "CSC 321", "CSC 331", "CSC 337", "CSC 341", "CSC 353", "CSC 361", "CSC 371", "CSC 395",
                // 400-Level
                "CSC 401", "CSC 411", "CSC 421", "CSC 431", "CSC 441", "CSC 451", "CSC 453", "CSC 455", "CSC 459", "CSC 461", "CSC 463", "CSC 471", "CSC 481", "CSC 490", "CSC 492", "CSC 495", "CSC 497", "CSC 498",
                // 500-Level (Graduate)
                "CSC 500", "CSC 501", "CSC 511", "CSC 512", "CSC 513", "CSC 514", "CSC 521", "CSC 531", "CSC 541", "CSC 546", "CSC 551", "CSC 552", "CSC 553", "CSC 555", "CSC 561", "CSC 564", "CSC 565", "CSC 581", "CSC 582", "CSC 583", "CSC 584", "CSC 585", "CSC 590", "CSC 594", "CSC 595", "CSC 597", "CSC 599",
                // 600-Level
                "CSC 600"
        };

        String[] semesters = {"Spring", "Summer", "Fall", "Winter"};
        int startYear = 2021;
        int endYear = 2026;
        Random random = new Random();

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("course_id,year,semester,enrollment_count\n");

            for (String course : courses) {
                int baseEnrollment = getBaseEnrollment(course);

                // If a course ends in "95" , treat it as a new class
                boolean isNewCourse = course.endsWith("95");
                int currentStartYear = isNewCourse ? 2026 : startYear;

                for (int year = currentStartYear; year <= endYear; year++) {
                    for (String semester : semesters) {

                        // If it's a new course, only generate Spring and Fall of 2026 (Exactly 2 records total)
                        if (isNewCourse && (semester.equals("Summer") || semester.equals("Winter"))) {
                            continue;
                        }

                        // Fluctuate the data up or down by 15 students
                        int fluctuation = random.nextInt(31) - 15;

                        // Math.max prevents the enrollment from ever dropping below 0
                        int finalEnrollment = Math.max(0, baseEnrollment + fluctuation);

                        writer.append(course).append(",")
                                .append(String.valueOf(year)).append(",")
                                .append(semester).append(",")
                                .append(String.valueOf(finalEnrollment)).append("\n");
                    }
                }
            }
            System.out.println("Success! Generated data file: " + fileName + " with dynamic data volumes.");
        } catch (IOException e) {
            System.out.println("Error generating file: " + e.getMessage());
        }
    }

    private static int getBaseEnrollment(String course)
    {
        String[] parts = course.split(" ");
        if (parts.length > 1) {
            try {
                int courseNumber = Integer.parseInt(parts[1]);

                if (courseNumber < 200) return 150;      // 100-level (Introductory)
                else if (courseNumber < 300) return 120; // 200-level (Early Core)
                else if (courseNumber < 400) return 85;  // 300-level (Upper Division Core)
                else if (courseNumber < 500) return 45;  // 400-level (Senior / Electives)
                else if (courseNumber < 600) return 25;  // 500-level (Graduate Courses)
                else return 10;                          // 600-level (Graduate Continuation)

            } catch (NumberFormatException e) {
                return 50;
            }
        }
        return 50;
    }
}