package uz.yt.basiccore.basiccore.controller;

import org.springframework.web.bind.annotation.*;
import uz.yt.basiccore.basiccore.domain.Student;
import uz.yt.basiccore.basiccore.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{id}")
    public Optional<Student> getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

//    @Operation(summary = "",
//            description = "",
//            parameters = {}
//    )
    @PutMapping
    public void updateStudent(@RequestBody Student student) {
        if (student.getId() == null) {
            throw new IllegalStateException("Student id is empty");
        }
        studentService.updateStudent(student);
    }
}
