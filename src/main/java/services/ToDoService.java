package services;

import dao.ToDoDao;
import dao.ToDoDaoImpl;
import models.ToDo;

import java.util.List;

public class ToDoService {

    ToDoDao toDoDao;

    public ToDoService(){
        this.toDoDao = new ToDoDaoImpl();
    }

    public ToDoService(ToDoDao toDoDao){
        this.toDoDao = toDoDao;
    }

    public List<ToDo> getAllTodos(){
        /*
        * Use the interface object toDoDao to call its abstract function getAllToDos that has its
        * implementation inside the ToDoDaoImpl class.
        * */
        return toDoDao.getAllToDos();
    }

    public ToDo getOneToDo(Integer toDoId){
        return toDoDao.getOneToDo(toDoId);
    }

    // Example of business logic.
    public Boolean createToDo(ToDo todo){
        if(todo.getTask().length() > 20){
            return false;
        }
        toDoDao.createToDo(todo);
        return true;
    }

    public void updateAToDo(Integer toDoId){
        System.out.printf("Todo id %d has been completed", toDoId);
        toDoDao.updateAToDo(toDoId);
    }

    public void deleteATodo(Integer toDoId){
        toDoDao.deleteAToDo(toDoId);
    }
}
