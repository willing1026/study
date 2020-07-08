package study.spring.restapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class EventControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void createEvent() throws Exception {
		Event event = Event.builder()
				.name("Spring")
				.description("REST API")
				.beginEventDateTime(LocalDateTime.of(2020, 7, 8, 17, 58))
				.closeEnrollmentDateTime(LocalDateTime.of(2020, 7, 8, 18, 58))
				.beginEventDateTime(LocalDateTime.of(2020, 7, 10, 9, 0))
				.endEventDateTime(LocalDateTime.of(2020, 7, 10, 18, 0))
				.basePrice(100)
				.maxPrice(200)
				.limitOfEnrollment(100)
				.location("강남역 D2")
				.build();

		mockMvc.perform(post("/api/events/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(event))
				.accept(MediaTypes.HAL_JSON)
		)
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("id").exists());
	}
}
