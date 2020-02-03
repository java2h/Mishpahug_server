package application.controller;

import application.dtoes.LoginDTO;
import application.dtoes.LoginResponse;
import application.models.data.sensor.ISensorModel;
import application.repositories.SensorRepository;
import application.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DataWebRequestTest {

  private final String TOKEN = "f796c5b8-861b-4da7-81e1-f065d0a47d97";
    private final HttpHeaders headers = new HttpHeaders();
    private String token;


    @Autowired
    ISensorModel sensorModel;
    
    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();


    @Before
    public void buildEntities() { //TODO: save token manually instead of doing login;

    }
    
    @After
    public void tearDown() {

    }


}