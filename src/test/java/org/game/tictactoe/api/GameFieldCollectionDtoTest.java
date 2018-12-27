package org.game.tictactoe.api;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.commons.mapper.EntityMapper;
import org.game.tictactoe.builder.GameStatusFactory;
import org.junit.Test;

public class GameFieldCollectionDtoTest {

	private GameDto game = new GameDto(1L, LocalDateTime.of(2000, 11, 10, 1, 2, 3), "new description",
			GameStatusFactory.aAIHatGewonnen());
	List<FieldDto> fields = Arrays.asList(tttFieldWithFieldIdAndText(1L, "val1"), tttFieldWithFieldIdAndText(2L, "val2"),
			tttFieldWithFieldIdAndText(3L, "val3"), tttFieldWithFieldIdAndText(4L, "val4"), tttFieldWithFieldIdAndText(5L, "val5"),
			tttFieldWithFieldIdAndText(6L, "val6"));

	private String json = "{\"game\":{\"id\":1,\"lastModified\":\"2000-11-10T01:02:03\",\"description\":\"new description\",\"status\":{\"id\":2,\"text\":\"AI hat gewonnen\"}},\"fields\":[{\"id\":5,\"lastModified\":\"2010-10-11T01:02:03\",\"gameId\":1,\"fieldId\":1,\"value\":\"val1\"},{\"id\":5,\"lastModified\":\"2010-10-11T01:02:03\",\"gameId\":1,\"fieldId\":2,\"value\":\"val2\"},{\"id\":5,\"lastModified\":\"2010-10-11T01:02:03\",\"gameId\":1,\"fieldId\":3,\"value\":\"val3\"},{\"id\":5,\"lastModified\":\"2010-10-11T01:02:03\",\"gameId\":1,\"fieldId\":4,\"value\":\"val4\"},{\"id\":5,\"lastModified\":\"2010-10-11T01:02:03\",\"gameId\":1,\"fieldId\":5,\"value\":\"val5\"},{\"id\":5,\"lastModified\":\"2010-10-11T01:02:03\",\"gameId\":1,\"fieldId\":6,\"value\":\"val6\"}]}";

	@Test
	public void entityToJson_TimestampMappedCorrectly() {
		CompleteGameDto dto = new CompleteGameDto(game, fields);

		Assertions.assertThat(EntityMapper.entityToJson(dto)).isEqualTo(json);
	}

	@Test
	public void jsonToEntity_TimestampMappedCorrectly() {
		Assertions.assertThat(EntityMapper.jsonToEntity(json, CompleteGameDto.class))
				.isEqualToComparingFieldByFieldRecursively(new CompleteGameDto(game, fields));
	}

	private FieldDto tttFieldWithFieldIdAndText(long fieldId, String value) {
		return new FieldDto(5L, LocalDateTime.of(2010, 10, 11, 1, 2, 3), 1L, fieldId, value);
	}

}
