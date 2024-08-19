package se.verran.springbootdemowithtests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import se.verran.springbootdemowithtests.entities.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SchoolServiceTest {
    StudentService studentService = mock(StudentService.class);
    SchoolService schoolService = new SchoolService(studentService);

    @BeforeEach
    void setUp() {
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWithNumberOfGroupsLessThanTwoShouldReturnFailure() {
        String returnString = "There should be at least two groups";
        assertThat(schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(1)).isEqualTo(returnString);
    }


    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWithNumberOfGroupsLargerThanNumberOfStudentsShouldReturnFailure() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"),"Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setId(2);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"),"Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setId(3);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        when(studentService.getAllStudents()).thenReturn(students);
        int numberOfStudents = students.size();
        int numberOfGroups = 4;
        String returnString = String.format("Not able to divide %s students into %s groups", numberOfStudents, numberOfGroups);
        assertThat(schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(numberOfGroups)).
                isEqualTo(returnString);
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWithStudentsPerGroupLessThanTwoShouldReturnFailure() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"),"Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setId(2);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"),"Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setId(3);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        when(studentService.getAllStudents()).thenReturn(students);
        int numberOfStudents = students.size();
        int numberOfGroups = 3;
        String returnString = String.format("Not able to manage %s groups with %s students", numberOfGroups, numberOfStudents);
        assertThat(schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(numberOfGroups)).
                isEqualTo(returnString);
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWithZeroRemainderShouldReturnStringWithNoRemainder() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"),"Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setId(2);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"),"Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setId(3);
        Student student4 = new Student("Firstname4", "Lastname4", LocalDate.parse("2000-04-04"),"Firstname4.Lastname4.JAVA23@edu.edugrade.se");
        student1.setId(4);
        Student student5 = new Student("Firstname5", "Lastname5", LocalDate.parse("2000-05-05"),"Firstname5.Lastname5.java23@edu.edugrade.se");
        student2.setId(5);
        Student student6 = new Student("Firstname6", "Lastname6", LocalDate.parse("2000-06-06"),"Firstname6.Lastname6.JAVA23@edu.edugrade.se");
        student3.setId(6);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        when(studentService.getAllStudents()).thenReturn(students);
        int numberOfStudents = students.size();
        int numberOfGroups = 2;
        int studentsPerGroup = numberOfStudents / numberOfGroups;
        int remainder = numberOfStudents % numberOfGroups;
        String returnString = String.format("2 groups could be formed with 3 students per group",
                numberOfGroups,
                studentsPerGroup);
        assertThat(schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(numberOfGroups)).
                isEqualTo(returnString);
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWithRemainderOneShouldReturnStringWithStudent() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"),"Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setId(2);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"),"Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setId(3);
        Student student4 = new Student("Firstname4", "Lastname4", LocalDate.parse("2000-04-04"),"Firstname4.Lastname4.JAVA23@edu.edugrade.se");
        student1.setId(4);
        Student student5 = new Student("Firstname5", "Lastname5", LocalDate.parse("2000-05-05"),"Firstname5.Lastname5.java23@edu.edugrade.se");
        student2.setId(5);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        when(studentService.getAllStudents()).thenReturn(students);
        int numberOfStudents = students.size();
        int numberOfGroups = 2;
        int studentsPerGroup = numberOfStudents / numberOfGroups;
        int remainder = numberOfStudents % numberOfGroups;
        String returnString = String.format("2 groups could be formed with 2 students per group, but that would leave 1 student hanging",
                numberOfGroups,
                studentsPerGroup);
        assertThat(schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(numberOfGroups)).
                isEqualTo(returnString);
    }

    @Test
    void numberOfStudentsPerGroupWhenDivideIntoNumberOfGroupsWithRemainderTwoShouldReturnStringWithStudents() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"),"Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setId(2);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"),"Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setId(3);
        Student student4 = new Student("Firstname4", "Lastname4", LocalDate.parse("2000-04-04"),"Firstname4.Lastname4.JAVA23@edu.edugrade.se");
        student1.setId(4);
        Student student5 = new Student("Firstname5", "Lastname5", LocalDate.parse("2000-05-05"),"Firstname5.Lastname5.java23@edu.edugrade.se");
        student2.setId(5);
        Student student6 = new Student("Firstname6", "Lastname6", LocalDate.parse("2000-06-06"),"Firstname6.Lastname6.JAVA23@edu.edugrade.se");
        student3.setId(6);
        Student student7 = new Student("Firstname7", "Lastname7", LocalDate.parse("2000-07-07"),"Firstname7.Lastname7.java23@edu.edugrade.se");
        student2.setId(7);
        Student student8 = new Student("Firstname8", "Lastname8", LocalDate.parse("2000-08-08"),"Firstname8.Lastname8.JAVA23@edu.edugrade.se");
        student3.setId(8);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        students.add(student7);
        students.add(student8);
        when(studentService.getAllStudents()).thenReturn(students);
        int numberOfStudents = students.size();
        int numberOfGroups = 3;
        int studentsPerGroup = numberOfStudents / numberOfGroups;
        int remainder = numberOfStudents % numberOfGroups;
        String returnString = String.format("3 groups could be formed with 2 students per group, but that would leave 2 students hanging",
                numberOfGroups,
                studentsPerGroup);
        assertThat(schoolService.numberOfStudentsPerGroupWhenDivideIntoNumberOfGroups(numberOfGroups)).
                isEqualTo(returnString);
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWithStudentsPerGroupLessThanTwoShouldReturnFailure() {
            String returnString = "Size of group should be at least 2";
            assertThat(schoolService.numberOfGroupsWhenDividedIntoGroupsOf(1)).isEqualTo(returnString);
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWithNumberOfStudentsLessThanStudentsPerGroupShouldReturnFailure() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"), "Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"), "Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setId(2);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"), "Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setId(3);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        when(studentService.getAllStudents()).thenReturn(students);
        int numberOfStudents = students.size();
        int studentsPerGroup = 4;
        String returnString = String.format("Not able to manage groups of %s with only %s students", studentsPerGroup, numberOfStudents);
        assertThat(schoolService.numberOfGroupsWhenDividedIntoGroupsOf(studentsPerGroup)).
                isEqualTo(returnString);
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWithNumberOfStudentsDividedByStudentsPerGroupSLessThanTwoShouldReturnFailure() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"), "Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"), "Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setId(2);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"), "Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setId(3);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        when(studentService.getAllStudents()).thenReturn(students);
        int numberOfStudents = students.size();
        int studentsPerGroup = 3;
        String returnString = String.format("Not able to manage groups of %s with only %s students", studentsPerGroup, numberOfStudents);
        assertThat(schoolService.numberOfGroupsWhenDividedIntoGroupsOf(studentsPerGroup)).
                isEqualTo(returnString);
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWithNumberOfStudentsDividedByStudentsPerGroupHasRemainderZeroShouldReturnString() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"), "Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"), "Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setId(2);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"), "Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setId(3);
        Student student4 = new Student("Firstname4", "Lastname4", LocalDate.parse("2000-04-04"), "Firstname4.Lastname4.JAVA23@edu.edugrade.se");
        student4.setId(4);
        Student student5 = new Student("Firstname5", "Lastname5", LocalDate.parse("2000-05-05"), "Firstname5.Lastname5.java23@edu.edugrade.se");
        student5.setId(5);
        Student student6 = new Student("Firstname6", "Lastname6", LocalDate.parse("2000-06-06"), "Firstname6.Lastname6.JAVA23@edu.edugrade.se");
        student6.setId(6);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        when(studentService.getAllStudents()).thenReturn(students);
        int numberOfStudents = students.size();
        int studentsPerGroup = 3;
        int numberOfGroups = numberOfStudents / studentsPerGroup;
        String returnString = String.format("%s students per group is possible, there will be %s groups", studentsPerGroup, numberOfGroups);
        assertThat(schoolService.numberOfGroupsWhenDividedIntoGroupsOf(studentsPerGroup)).
                isEqualTo(returnString);
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWithNumberOfStudentsDividedByStudentsPerGroupHasRemainderOneShouldReturnString() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"), "Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"), "Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setId(2);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"), "Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setId(3);
        Student student4 = new Student("Firstname4", "Lastname4", LocalDate.parse("2000-04-04"), "Firstname4.Lastname4.JAVA23@edu.edugrade.se");
        student4.setId(4);
        Student student5 = new Student("Firstname5", "Lastname5", LocalDate.parse("2000-05-05"), "Firstname5.Lastname5.java23@edu.edugrade.se");
        student5.setId(5);
        Student student6 = new Student("Firstname6", "Lastname6", LocalDate.parse("2000-06-06"), "Firstname6.Lastname6.JAVA23@edu.edugrade.se");
        student6.setId(6);
        Student student7 = new Student("Firstname7", "Lastname7", LocalDate.parse("2000-07-07"), "Firstname7.Lastname7.JAVA23@edu.edugrade.se");
        student7.setId(7);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        students.add(student7);
        when(studentService.getAllStudents()).thenReturn(students);
        int numberOfStudents = students.size();
        int studentsPerGroup = 3;
        int numberOfGroups = numberOfStudents / studentsPerGroup;
        String returnString = String.format("%s students per group is possible, there will be %s groups, there will be 1 student hanging", studentsPerGroup, numberOfGroups);
        assertThat(schoolService.numberOfGroupsWhenDividedIntoGroupsOf(studentsPerGroup)).
                isEqualTo(returnString);
    }

    @Test
    void numberOfGroupsWhenDividedIntoGroupsOfWithNumberOfStudentsDividedByStudentsPerGroupHasRemainderLargerThanOneShouldReturnString() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"), "Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"), "Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setId(2);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"), "Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setId(3);
        Student student4 = new Student("Firstname4", "Lastname4", LocalDate.parse("2000-04-04"), "Firstname4.Lastname4.JAVA23@edu.edugrade.se");
        student4.setId(4);
        Student student5 = new Student("Firstname5", "Lastname5", LocalDate.parse("2000-05-05"), "Firstname5.Lastname5.java23@edu.edugrade.se");
        student5.setId(5);
        Student student6 = new Student("Firstname6", "Lastname6", LocalDate.parse("2000-06-06"), "Firstname6.Lastname6.JAVA23@edu.edugrade.se");
        student6.setId(6);
        Student student7 = new Student("Firstname7", "Lastname7", LocalDate.parse("2000-07-07"), "Firstname7.Lastname7.JAVA23@edu.edugrade.se");
        student7.setId(7);
        Student student8 = new Student("Firstname8", "Lastname8", LocalDate.parse("2000-08-08"), "Firstname8.Lastname8.JAVA23@edu.edugrade.se");
        student8.setId(8);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        students.add(student7);
        students.add(student8);
        when(studentService.getAllStudents()).thenReturn(students);
        int numberOfStudents = students.size();
        int studentsPerGroup = 3;
        int numberOfGroups = numberOfStudents / studentsPerGroup;
        String returnString = String.format("%s students per group is possible, there will be %s groups, there will be 2 students hanging", studentsPerGroup, numberOfGroups);
        assertThat(schoolService.numberOfGroupsWhenDividedIntoGroupsOf(studentsPerGroup)).
                isEqualTo(returnString);
    }

    @Test
    void calculateAverageGradeWithZeroStudentsShouldThrowException() {
        List<Student> students = new ArrayList<>();
        when(studentService.getAllStudents()).thenReturn(students);
        assertThatExceptionOfType(ResponseStatusException.class).
                isThrownBy(() ->schoolService.calculateAverageGrade()).
                withMessage("404 NOT_FOUND \"No students found\"");
    }


    @Test
    void calculateAverageGradeWithTwoStudentsShouldReturnStringWithAverage() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"), "Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setJavaProgrammingGrade(4.5);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"), "Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setJavaProgrammingGrade(3.5);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        when(studentService.getAllStudents()).thenReturn(students);
        assertThat(schoolService.calculateAverageGrade()).isEqualTo("Average grade is 4,0");
    }


    @Test
    void calculateAverageGradeWithThreeStudentsShouldReturnStringWithAverage() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"), "Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setJavaProgrammingGrade(4.5);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"), "Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setJavaProgrammingGrade(3.5);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"), "Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setJavaProgrammingGrade(2.0);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        when(studentService.getAllStudents()).thenReturn(students);
        assertThat(schoolService.calculateAverageGrade()).isEqualTo("Average grade is 3,3");
    }

    @Test
    void getTopScoringStudentsWithZeroStudentsShouldThrowException() {
        List<Student> students = new ArrayList<>();
        when(studentService.getAllStudents()).thenReturn(students);
        assertThatExceptionOfType(ResponseStatusException.class).
                isThrownBy(() ->schoolService.getTopScoringStudents()).
                withMessage("404 NOT_FOUND \"No students found\"");
    }

    @Test
    void getTopScoringStudentsWithOneStudentsShouldReturnListWithOneStudent() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"), "Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setJavaProgrammingGrade(4.5);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        when(studentService.getAllStudents()).thenReturn(students);
        assertThat(schoolService.getTopScoringStudents().size()).isEqualTo(1);
    }

    @Test
    void getTopScoringStudentsWithFiveStudentsShouldReturnListWithOneStudent() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"), "Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setJavaProgrammingGrade(4.5);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"), "Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setJavaProgrammingGrade(3.5);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"), "Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setJavaProgrammingGrade(2.0);
        Student student4 = new Student("Firstname4", "Lastname4", LocalDate.parse("2000-04-04"), "Firstname4.Lastname4.JAVA23@edu.edugrade.se");
        student4.setJavaProgrammingGrade(2.0);
        Student student5 = new Student("Firstname5", "Lastname5", LocalDate.parse("2000-05-05"), "Firstname5.Lastname5.JAVA23@edu.edugrade.se");
        student5.setJavaProgrammingGrade(2.0);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        when(studentService.getAllStudents()).thenReturn(students);
        assertThat(schoolService.getTopScoringStudents().size()).isEqualTo(1);
    }

    @Test
    void getTopScoringStudentsWithSixStudentsShouldReturnListWithTwoStudents() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"), "Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setJavaProgrammingGrade(4.5);
        Student student2 = new Student("Firstname2", "Lastname2", LocalDate.parse("2000-02-02"), "Firstname2.Lastname2.java23@edu.edugrade.se");
        student2.setJavaProgrammingGrade(3.5);
        Student student3 = new Student("Firstname3", "Lastname3", LocalDate.parse("2000-03-03"), "Firstname3.Lastname3.JAVA23@edu.edugrade.se");
        student3.setJavaProgrammingGrade(2.0);
        Student student4 = new Student("Firstname4", "Lastname4", LocalDate.parse("2000-04-04"), "Firstname4.Lastname4.JAVA23@edu.edugrade.se");
        student4.setJavaProgrammingGrade(2.0);
        Student student5 = new Student("Firstname5", "Lastname5", LocalDate.parse("2000-05-05"), "Firstname5.Lastname5.JAVA23@edu.edugrade.se");
        student5.setJavaProgrammingGrade(4.5);
        Student student6 = new Student("Firstname6", "Lastname6", LocalDate.parse("2000-06-06"), "Firstname6.Lastname6.java23@edu.edugrade.se");
        student6.setJavaProgrammingGrade(3.5);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        when(studentService.getAllStudents()).thenReturn(students);
        assertThat(schoolService.getTopScoringStudents().size()).isEqualTo(2);
    }
}