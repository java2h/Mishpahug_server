package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.utils.RandomString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
@Slf4j
@Transactional
public class SalLoader implements  ILoader {
    @Autowired
    LoaderDependencies data;

    private BufferedReader br;

    public SalLoader(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void load() {
        try {
            String detail;
            while ((detail = br.readLine()) != null) {
//TODO
                log.debug("DBLoadTest -> SalLoader -> Sal = " + "" );
                this.data.userRepository.save(null);
            }
            log.debug("DBLoadTest -> UserLoader -> In repository " + this.data.userRepository.findAll().size() + " records");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
