package study.spring.restapi.events;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {
	@Test
	void builder() {
		Event event = Event.builder().build();
		assertThat(event).isNotNull();
	}

	@Test
	void javaBean() {
		//given
		Event event = new Event();
		String name = "Event";
		String description = "Spring";

		//when
		event.setName(name);
		event.setDescription(description);

		//then
		assertThat(event.getName()).isEqualTo(name);
		assertThat(event.getDescription()).isEqualTo(description);
	}
}