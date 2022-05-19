package uz.yt.basiccore.basiccore.service;

import uz.yt.basiccore.basiccore.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getStudents();

    Optional<Student> getStudentById(Long id);

    void addNewStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Long id);

}
