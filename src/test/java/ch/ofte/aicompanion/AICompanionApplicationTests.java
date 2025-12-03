package ch.ofte.aicompanion;

import ch.ofte.aicompanion.server.ai.dto.PromptRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class AICompanionApplicationTests {
    private static final String URL = "https://localhost:";

    @LocalServerPort
    private static int port;

	@Test
	void contextLoads() {
	}

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void testDeepSeekSimplePrompt() {
        PromptRequest request = new PromptRequest();
        request.setPrompt("Who are you?");
        String response = restTemplate.getForObject(URL + port + "/api/aiService", String.class);

        System.out.println(response);
        Assertions.assertFalse(response.isEmpty());
    }
}
