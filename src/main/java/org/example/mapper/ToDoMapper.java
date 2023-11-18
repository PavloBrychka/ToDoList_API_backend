package org.example.mapper;


import org.example.dto.ToDoItemDTO;
import org.example.entities.ToDoListEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ToDoMapper {
    ToDoItemDTO toDoItemDTO(ToDoListEntity task);

    List<ToDoItemDTO> listToDoItemDTO(List<ToDoListEntity> list);
}
