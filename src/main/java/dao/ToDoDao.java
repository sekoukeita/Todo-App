package dao;

import models.ToDo;

import java.util.List;

//crud operations
public interface ToDoDao {
    List<ToDo> getAllToDos();
    ToDo getOneToDo(Integer toDoId);
    void createToDo(ToDo toDo);
    void updateAToDo(Integer toDoId);
    void deleteAToDo(Integer toDoId);
}
