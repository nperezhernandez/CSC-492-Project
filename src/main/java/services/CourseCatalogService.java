package services;

import java.util.List;
import models.Course;

public class CourseCatalogService {

    public List<Course> getCourses() {

        return List.of(
             new Course(
                "CSC 101",
                "Introduction to Computer Education",
                "None",
                "Fall, Spring"
            ),
            new Course(
                "CSC 111",
                "Introduction to Computers and Basic Programming",
                "None",
                "Fall, Spring"
            ),
            new Course(
                "CSC 112",
                "Intro Computers And Basic",
                "None",
                "All terms"
            ),
            new Course(
                "CSC 115",
                " Introduction to Programming Concepts",
                "None",
                "Fall, Spring"
            ),
            new Course(
                "CSC 116",
                "Introduction to Computer Hardware and Tools",
                "None",
                "Fall, Spring"
            ),
            new Course(
                "CSC 121",
                "Introduction to Computer Science and Programming I",
                "None",
                "Fall, Spring"
            ),
            new Course(
                "CSC 123",
                "Introduction to Computer Science and Programming II",
                "CSC 121",
                "Fall, Spring"
            ),
            new Course(
                "CSC 195",
                "Selected Topics in Computer Science",
                "None",
                "Offered as Needed"
            ),
            new Course(
                "CSC 221",
                "Assembly Language and Introduction to Computer Organization",
                "CSC 121",
                "Fall, Spring"
            ),
             new Course(
                "CSC 251",
                "C Language Programming and Unix",
                "CSC 121",
                "Fall, Spring"
            ),
            new Course(
                "CSC 255",
                " Dynamic Web Programming",
                "None",
                "Fall, Spring"
            ),
            new Course(
                "CSC 281",
                "Discrete Structures",
                "CSC 121 and MAT 151 or MAT 153",
                "Fall, Spring"
            ),
            new Course(
                "CSC 295",
                "Sel. Topics in Computer Sci",
                "Consent of Instructor",
                "Offered as Needed"
            ),
            new Course(
                "CSC 300",
                "Software Development",
                "CSC 123",
                "Fall, Spring"
            ),
            new Course(
                "CSC 301",
                "Computers and Society",
                "CSC 101 or CSC 111 or CSC 115 or CSC 121 or CSC 272",
                "Fall, Spring"
            ),
            new Course(
                "CSC 311",
                "Data Structures",
                "CSC 221, CSC 281 or MAT 281",
                "Fall, Spring"
            ),
            new Course("CSC 321",
                "Programming Languages",
                "CSC 123",
                "Fall, Spring"
            ),
        new Course("CSC 331",
                "Computer Organization",
                "CSC 221 and MAT 281",
                "Fall, Spring"
            ),
 new Course("CSC 337",
                "Microcomputers",
                "CSC 221, CSC 331, and MAT 281",
                "Offered Infrequent"
            ),
        new Course("CSC 341",
                "Operating Systems",
                "CSC 311, CSC 331, and MAT 321",
                "Fall, Spring"
            ),

        new Course("CSC 353",
                "File Processing",
                "CSC 123 and CSC 251",
                "Offered Infrequent"
            ),

        new Course("CSC 401",
                "Software Engineering",
                "CSC 311",
                "Spring"),

        new Course("CSC 411",
                "Algorithms",
                "CSC 311",
                "Fall"),

        new Course("CSC 421",
                "Computer Graphics",
                "CSC 311",
                "Spring"),

        new Course("CSC 431",
                "Cybersecurity",
                "CSC 311",
                "Fall"),

        new Course("CSC 441",
                "Web Development",
                "CSC 311",
                "Spring"),

        new Course("CSC 451",
                "Mobile Application Development",
                "CSC 311",
                "Spring"),

        new Course("CSC 461",
                "Cloud Computing",
                "CSC 311",
                "Fall"),

        new Course("CSC 471",
                "Machine Learning",
                "CSC 311",
                "Spring"),

        new Course("CSC 481",
                "Artificial Intelligence",
                "CSC 311",
                "Spring"),

        new Course("CSC 492",
                "Senior Project",
                "Senior Standing",
                "Fall, Spring")
    );
    }
}
