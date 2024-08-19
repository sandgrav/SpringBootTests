package se.verran.springbootdemowithtests.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import se.verran.springbootdemowithtests.entities.Student;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        Student student1 = new Student("Firstname1", "Lastname1", LocalDate.parse("2000-01-01"),"Firstname1.Lastname1.JAVA23@edu.edugrade.se");
        studentRepository.save(student1);
    }

    @Test
    void existsStudentByEmailWithExistingEmailShouldReturnTrue() {
        assertTrue(studentRepository.existsStudentByEmail("Firstname1.Lastname1.JAVA23@edu.edugrade.se"));
    }

    @Test
    void existsStudentByEmailWithNonExistingEmailShouldReturnFalse() {
        assertFalse(studentRepository.existsStudentByEmail("Firstname2.Lastname2.JAVA23@edu.edugrade.se"));
    }
}