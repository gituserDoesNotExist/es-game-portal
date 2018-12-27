package org.game.tictactoe.field;

import java.util.List;
import java.util.stream.Collectors;

import org.game.tictactoe.api.FieldDto;

public class FieldMapper {

	public List<FieldDto> asDtoList(List<Field> fields) {
		return fields.stream().map(entity -> asDto(entity)).collect(Collectors.toList());
	}

	public FieldDto asDto(Field entity) {
		return new FieldDto(entity.getId(), entity.getLastModified(), entity.getGameId(), entity.getFieldId(), entity.getValue());
	}

	public Field fromNewDto(FieldDto dto) {
		Field entity = new Field();
		entity.setGameId(dto.getGameId());
		entity.setFieldId(dto.getFieldId());
		entity.setValue(dto.getValue());
		return entity;
	}

	public Field fromUpdatedDto(long id, FieldDto dto) {
		Field entity = fromNewDto(dto);
		entity.setId(id);
		entity.setLastModified(dto.getLastModified());
		return entity;
	}
}
