package org.game.tictactoe.api;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.commons.mapper.EntityMapper;
import org.game.tictactoe.builder.GameStatusFactory;
import org.junit.Test;

public class GameFieldDtoTest {

	private GameDto game = new GameDto(1L, LocalDateTime.of(2000, 11, 10, 1, 2, 3), "new description",
			GameStatusFactory.aAIHatGewonnen());
	private FieldDto field = new FieldDto(5L, LocalDateTime.of(2010, 10, 11, 1, 2, 3), 1L, 1L, "tttvalue");

	private String json = "{\"game\":{\"id\":1,\"lastModified\":\"2000-11-10T01:02:03\",\"description\":\"new description\",\"status\":{\"id\":2,\"text\":\"AI hat gewonnen\"}},\"field\":{\"id\":5,\"lastModified\":\"2010-10-11T01:02:03\",\"gameId\":1,\"fieldId\":1,\"value\":\"tttvalue\"}}";

	@Test
	public void entityToJson_TimestampMappedCorrectly() {
		GameFieldDto dto = new GameFieldDto(game, field);

		Assertions.assertThat(EntityMapper.entityToJson(dto)).isEqualTo(json);
	}

	@Test
	public void jsonToEntity_TimestampMappedCorrectly() {
		Assertions.assertThat(EntityMapper.jsonToEntity(json, GameFieldDto.class))
				.isEqualToComparingFieldByFieldRecursively(new GameFieldDto(game, field));
	}

}
