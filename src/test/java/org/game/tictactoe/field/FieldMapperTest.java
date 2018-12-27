package org.game.tictactoe.field;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.game.tictactoe.api.FieldDto;
import org.game.tictactoe.builder.FieldBuilder;
import org.junit.Test;

public class FieldMapperTest {

	private FieldMapper mapper = new FieldMapper();

	private static final LocalDateTime TIMESTAMP = LocalDateTime.of(2000, 10, 11, 1, 3, 4);
	private Field entity = field("value");

	private FieldDto dto = new FieldDto(1L, TIMESTAMP, 3L, 2L, "value");

	@Test
	public void asDto() {
		Assertions.assertThat(mapper.asDto(entity)).isEqualTo(dto);
	}

	@Test
	public void asDtoList() {
		FieldDto anotherDto = new FieldDto(1L, TIMESTAMP, 3L, 2L, "new value");
		Assertions.assertThat(mapper.asDtoList(Arrays.asList(field("value"), field("new value"))))
				.isEqualTo(Arrays.asList(dto, anotherDto));
	}

	@Test
	public void fromDto() {
		Assertions.assertThat(mapper.fromNewDto(dto)).isEqualToIgnoringGivenFields(entity, "id", "lastModified");
	}

	private Field field(String value) {
		return FieldBuilder.aTicTacToeField().withId(1L).withLastModified(TIMESTAMP).withFieldId(2L).withGameId(3L).withValue(value)
				.build();
	}

}
