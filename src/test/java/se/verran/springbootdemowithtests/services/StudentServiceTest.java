package se.verran.springbootdemowithtests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se.verran.springbootdemowithtests.entities.Student;
import se.verran.springbootdemowithtests.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    StudentRepository studentRepository = mock(StudentRepository.class);
    StudentService studentService = new StudentService(studentRepository);

    @Test
    void addStudentWithExistingEmailShouldThrowResponseStatusException() {
        Student student = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        when(studentRepository.existsStudentByEmail("Firstname1.Lastname1.JAVA23@edu.edugrade.se")).thenReturn(true);
        assertThatExceptionOfType(ResponseStatusException.class).
                isThrownBy(() -> studentService.addStudent(student)).
                withMessage("409 CONFLICT \"Email Firstname1.Lastname1.JAVA23@edu.edugrade.se already exists\"");
        verify(studentRepository).existsStudentByEmail("Firstname1.Lastname1.JAVA23@edu.edugrade.se");
    }

    @Test
    void addStudentWithNonExistingEmailShouldNotThrowResponseStatusException() {
        Student student = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        assertThatNoException().isThrownBy(() -> studentService.addStudent(student));
        verify(studentRepository).existsStudentByEmail("Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        verify(studentRepository).save(student);
    }

    @Test
    void addStudentWithNonExistingEmailShouldSaveAndReturnStudent() {
        Student student = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        when(studentRepository.save(student)).thenReturn(student);
        assertThat(studentService.addStudent(student)).isEqualTo(student);
        verify(studentRepository).existsStudentByEmail("Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        verify(studentRepository).save(student);
    }

    @Test
    void getAllStudentsShouldCallFindAllAndReturnListOfStudents() {
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
        when(studentRepository.findAll()).thenReturn(students);
        assertThat(studentService.getAllStudents().size()).isEqualTo(3);
        verify(studentRepository).findAll();
    }

    @Test
    void deleteStudentWithNonExistingIdShouldThrowException() {
        when(studentRepository.existsById(2)).thenReturn(false);
        assertThatExceptionOfType(ResponseStatusException.class).
                isThrownBy(() -> studentService.deleteStudent(2)).
                withMessage("404 NOT_FOUND \"Could not find and delete student by id 2\"");
        verify(studentRepository).existsById(2);
    }

    @Test
    void deleteStudentWithExistingIdShouldNotThrowExceptionAndCallDeleteById() {
        when(studentRepository.existsById(2)).thenReturn(true);
        assertThatNoException().isThrownBy(() -> studentService.deleteStudent(2));
        verify(studentRepository).deleteById(2);
    }

    @Test
    void updateStudentWithNonExistingIdShouldThrowException() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        when(studentRepository.existsById(1)).thenReturn(false);
        assertThatExceptionOfType(ResponseStatusException.class).
                isThrownBy(() -> studentService.updateStudent(student1)).
                withMessage("404 NOT_FOUND \"Could not find and update student by id 1\"");
        verify(studentRepository).existsById(student1.getId());
    }

    @Test
    void updateStudentWithExistingIdShouldNotThrowException() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        when(studentRepository.existsById(1)).thenReturn(true);
        when(studentRepository.save(student1)).thenReturn(student1);
        assertThatNoException().isThrownBy(() -> studentService.updateStudent(student1));
    }

    @Test
    void updateStudentWithExistingIdShouldCallSaveAndReturnStudent() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        when(studentRepository.existsById(1)).thenReturn(true);
        when(studentRepository.save(student1)).thenReturn(student1);
        assertThat(student1).isEqualTo(studentService.updateStudent(student1));
        verify(studentRepository).save(student1);
    }

    @Test
    void getStudentByIdWithNonExistingIdShouldThrowException() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());
        assertThatExceptionOfType(ResponseStatusException.class).
                isThrownBy(() -> studentService.getStudentById(1)).
                withMessage("404 NOT_FOUND \"Could not find student by id 1\"");
        verify(studentRepository).findById(1);
    }

    @Test
    void getStudentByIdWithExistingIdShouldNotThrowException() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Optional<Student> optionalStudent = Optional.of(student1);
        when(studentRepository.findById(1)).thenReturn(optionalStudent);
        assertThatNoException().isThrownBy(() -> studentService.getStudentById(1));
        verify(studentRepository).findById(1);
    }

    @Test
    void getStudentByIdShouldReturnStudent() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setId(1);
        Optional<Student> optionalStudent = Optional.of(student1);
        when(studentRepository.findById(1)).thenReturn(optionalStudent);
        assertThat(studentService.getStudentById(1)).isEqualTo(student1);
        verify(studentRepository).findById(1);
    }

    @Test
    void setGradeForStudentByIdWithGradeNotParsableAsDoubleShouldThrowResponseStatusException() {
        String gradeAsString = "Test";
        int studentId = 1;
        assertThatExceptionOfType(ResponseStatusException.class).
                isThrownBy(() -> studentService.setGradeForStudentById(studentId, gradeAsString)).
                withMessage("406 NOT_ACCEPTABLE \"Valid grades are 0.0 - 5.0\"");
    }

    @Test
    void setGradeForStudentByIdWithGradeLessThanZeroShouldThrowResponseStatusException() {
        String gradeAsString = "-1.0";
        int studentId = 1;
        assertThatExceptionOfType(ResponseStatusException.class).
                isThrownBy(() -> studentService.setGradeForStudentById(studentId, gradeAsString)).
                withMessage("406 NOT_ACCEPTABLE \"Valid grades are 0.0 - 5.0\"");
    }

    @Test
    void setGradeForStudentByIdWithGradeLargerThanFiveShouldThrowResponseStatusException() {
        String gradeAsString = "6.0";
        int studentId = 1;
        assertThatExceptionOfType(ResponseStatusException.class).
                isThrownBy(() -> studentService.setGradeForStudentById(studentId, gradeAsString)).
                withMessage("406 NOT_ACCEPTABLE \"Valid grades are 0.0 - 5.0\"");
    }

    @Test
    void setGradeForStudentByIdWithNonExistingIdShouldThrowResponseStatusException() {
        String gradeAsString = "3.0";
        int studentId = 1;
        assertThatExceptionOfType(ResponseStatusException.class).
                isThrownBy(() -> studentService.setGradeForStudentById(studentId, gradeAsString)).
                withMessage(String.format("404 NOT_FOUND \"Could not find and update grades for student by id %d\"", studentId));
        verify(studentRepository).findById(studentId);
    }

    @Test
    void setGradeForStudentByIdWithExistingIdShouldUpdateGradeSaveAndReturnStudent() {
        String gradeAsString = "3.0";
        int studentId = 1;
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        student1.setJavaProgrammingGrade(Double.parseDouble(gradeAsString));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student1));
        when(studentRepository.save(student1)).thenReturn(student1);
        assertThat(studentService.setGradeForStudentById(studentId, gradeAsString)).isEqualTo(student1);
        verify(studentRepository).findById(studentId);
        verify(studentRepository).save(student1);
    }
}