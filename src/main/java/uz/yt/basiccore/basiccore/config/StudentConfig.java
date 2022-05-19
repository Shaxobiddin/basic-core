package uz.yt.basiccore.basiccore.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.yt.basiccore.basiccore.domain.Student;
import uz.yt.basiccore.basiccore.repository.StudentRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student eshmat = new Student(
                    1L,
                    "Eshmat",
                    "eshmat.toshmatov@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Student ahmad = new Student(
                    2L,
                    "Ahmad",
                    "ahmad.tursunov@gmail.com",
                    LocalDate.of(2010, Month.JANUARY, 15)
            );

            studentRepository.saveAll(Arrays.asList(eshmat, ahmad));
        };
    }
}
