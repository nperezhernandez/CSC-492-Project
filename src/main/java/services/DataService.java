package services;

import models.Dataset;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataService
{
    public List<Dataset> loadData(String filePath)
    {
        List<Dataset> courseDataList = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath)))
        {
            buffer.readLine();

            while ((line = buffer.readLine()) != null)
            {
                String[] data = line.split(cvsSplitBy);

                if (data.length == 4)
                {
                    try
                    {
                        String courseID = data[0].trim();
                        int year = Integer.parseInt(data[1].trim());
                        String semester = data[2].trim();
                        int enrollmentCount = Integer.parseInt(data[3].trim());

                        Dataset record = new Dataset(courseID, year, semester, enrollmentCount);
                        courseDataList.add(record);
                    } catch (NumberFormatException e)
                    {
                        System.out.println("Warning: Skipping bad row (invalid number format): " + line);
                    }
                }
            }
        } catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return courseDataList;
    }

    public List<String> getUniqueCourseIDs(String filePath)
    {
        List<Dataset> allData = loadData(filePath);

        return allData.stream()
                .map(Dataset::getCourseID)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
