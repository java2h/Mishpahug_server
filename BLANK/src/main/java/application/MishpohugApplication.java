package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MishpohugApplication {

    public static void main(String[] args) {
        SpringApplication.run(MishpohugApplication.class, args);
        MainForm mainForm = new MainForm();
    }

}
