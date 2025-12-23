package mycode.flashwork2;

import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.jobs.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class FlashWork2Application {

    public static void main(String[] args) {
        SpringApplication.run(FlashWork2Application.class, args);


    }
    @Bean
    CommandLineRunner show(JobRepository jobRepository){
        return args -> {
            List<Job> jobs=jobRepository.findAll();
            jobs.stream().forEach(System.out::println);

        };
    }

}
