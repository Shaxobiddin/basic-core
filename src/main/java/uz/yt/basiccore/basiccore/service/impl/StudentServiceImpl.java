package uz.yt.basiccore.basiccore.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uz.yt.basiccore.basiccore.domain.Student;
import uz.yt.basiccore.basiccore.repository.StudentRepository;
import uz.yt.basiccore.basiccore.service.StudentService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }

        studentRepository.save(student);
    }

    @Transactional
    @Override
    public void updateStudent(Student student) {
        Student entity = studentRepository.findById(student.getId()).orElseThrow(() -> new IllegalStateException("Student with id " + student.getId() + " does not exist"));

        if (StringUtils.hasText(student.getName()) && !Objects.equals(student.getName(), entity.getName())) {
            entity.setName(student.getName());
        }

        if (StringUtils.hasText(student.getEmail()) && !Objects.equals(student.getEmail(), entity.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            entity.setEmail(student.getEmail());
        }

        studentRepository.save(entity);
    }


    @Override
    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id " + id + " does not exists");
        }
        studentRepository.deleteById(id);
    }
}
