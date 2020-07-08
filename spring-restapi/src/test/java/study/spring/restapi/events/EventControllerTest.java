package study.spring.restapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class EventControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	EventRepository eventRepository;

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
		event.setId(10);
		Mockito.when(eventRepository.save(event)).thenReturn(event);

		mockMvc.perform(post("/api/events/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(event))
				.accept(MediaTypes.HAL_JSON)
		)
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("id").exists())
				.andExpect(header().exists("Location"))
				.andExpect(header().string("Content-Type", "application/hal+json"))
		;
	}
}
