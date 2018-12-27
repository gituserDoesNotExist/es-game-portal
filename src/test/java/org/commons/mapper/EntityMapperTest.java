package org.commons.mapper;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.game.tictactoe.api.FieldDto;
import org.game.tictactoe.builder.FieldBuilder;
import org.game.tictactoe.field.Field;
import org.junit.Test;

public class EntityMapperTest {

	private static final LocalDateTime TIMESTAMP = LocalDateTime.of(2000, 10, 3, 10, 2, 3);
	private static final long ID = 4L;
	private static final String DESCRIPTION = "this is a description";

	@Test
	public void jsonToEntity() {
		String testObjectJson = "{\"id\":4,\"description\":\"this is a description\",\"lastModified\":\"2000-10-03T10:02:03\"}";

		TestObject result = EntityMapper.jsonToEntity(testObjectJson, TestObject.class);

		Assertions.assertThat(result).isEqualToComparingFieldByField(new TestObject(4L, DESCRIPTION, TIMESTAMP));
	}

	@Test
	public void jsonToEntity_2() {

		String json = "{\"id\":47,\"lastModified\":\"2018-01-14T15:15:01\",\"gameId\":8,\"fieldId\":6,\"value\":\"--\"}";

		FieldDto jsonToEntity = EntityMapper.jsonToEntity(json, FieldDto.class);

		Assertions.assertThat(jsonToEntity.getLastModified()).isEqualTo(LocalDateTime.of(2018, 1, 14, 15, 15, 1));
	}

	@Test
	public void jsonToEntity_IncompleteJson() {
		String json = "{\"id\":1,\"gameId\":2,\"fieldId\":3,\"value\":\"x\"}";

		Field result = EntityMapper.jsonToEntity(json, Field.class);

		Assertions.assertThat(result).isEqualToComparingFieldByFieldRecursively(
				FieldBuilder.aTicTacToeField().withId(1L).withGameId(2L).withFieldId(3L).withValue("x").build());
	}

}
