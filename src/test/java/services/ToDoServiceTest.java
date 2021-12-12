package services;

import dao.ToDoDao;
import models.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest {
    // mock the toDoDao object
    ToDoDao toDoDao = Mockito.mock(ToDoDao.class);

    ToDoService toDoService;

    /*
    * Use of loose coupling.
    * Inside the ToDoServiceTest constructor, the one argument constructor ToDoService is used to allow
    * referencing the mocked object toDoDao rather that referencing a "normal" toDoDao that will in turn reference
    * an ToDoDaoImpl object
    * */
    public ToDoServiceTest(){
        this.toDoService = new ToDoService(toDoDao);
    }

    @Test
    void getAllTodos() {
        //arrange
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1, "sweep", false));
        expectedResult.add(new ToDo(2, "mop", false));
        expectedResult.add(new ToDo(3, "Listen to Kevin talk", false));
        // Without this line, running the method will run the toDoDao object,then the ToDoDaoImpl then the db
        // That is what we are avoiding using Mockito (mocking the ToDoDao to test the toDoService)
        Mockito.when(toDoDao.getAllToDos()).thenReturn(expectedResult);

        //act
        List<ToDo> actualResult = toDoService.getAllTodos();

        //assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getOneToDo() {
        //arrange
        ToDo expectedResult = new ToDo(1, "sweep", false);
        Mockito.when(toDoDao.getOneToDo(expectedResult.getId())).thenReturn(expectedResult);

        //act
        ToDo actualResult = toDoService.getOneToDo(expectedResult.getId());

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createToDoGreaterThan20() {
        //arrange
        ToDo toDo = new ToDo(1, "Is this greater than?", false);
        // No mock needed because toDoDao is not used in this part of if clause.

        //act
        Boolean actualResult = toDoService.createToDo(toDo);

        //assert
        assertFalse(actualResult);
    }

    @Test
    void createToDoLessThanOrEquals20() {
        ToDo toDo = new ToDo(1, "less than 20?", false);

        //act
        Boolean actualResult = toDoService.createToDo(toDo);

        //assert
        assertTrue(actualResult);
    }

    @Test

    void updateAToDo() {
        //arrange
        Integer toDoId = 1;

        //act
        toDoService.updateAToDo(toDoId);

        //assert
        // when nothing is returned, use Mockito.verify to verify the method was invoked.
        Mockito.verify(toDoDao, Mockito.times(1)).updateAToDo(toDoId);
        // or : Mockito.verify(toDoDao).updateAToDo(toDoId);
    }

    @Test
    void deleteATodo() {
        //arrange
        Integer toDoId = 1;

        //act
        toDoService.deleteATodo(toDoId);

        //assert
        Mockito.verify(toDoDao).deleteAToDo(toDoId);
    }
}