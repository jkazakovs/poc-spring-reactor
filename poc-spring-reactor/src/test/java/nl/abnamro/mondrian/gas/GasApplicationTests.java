package nl.abnamro.mondrian.gas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GasApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testAllocation() {
        webTestClient
                .get().uri("/accept?externalAccountId=my_external_account_id")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.allocatedAccountId").isEqualTo("my_external_account_id");
    }
}