package dao;

import models.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.H2Util;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
*  What are integration test?
* - testing the part of your code that connects to external services.
* - What is H2?
*   - H2 is an "in memory" database. It is a database meant for testing and development
*  - When team working, there a certain amount of simultaneous connections allowed.
*       -H2 allows you to have your own database that replicate the original database.
*
* */

class ToDoDaoImplIT {

    ToDoDao toDoDao;

    public ToDoDaoImplIT(){
        this.toDoDao = new ToDoDaoImpl(H2Util.url, H2Util.username, H2Util.password);
    }

    @BeforeEach
    void setUp() {
        H2Util.createTable();
    }

    @AfterEach
    void tearDown() {
        H2Util.dropTable();
    }

    @Test
    void getAllToDosIT() { // IT stands stands for Integration testing

        //arrange
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1, "sweep", false));
        expectedResult.add(new ToDo(2, "mop", false));
        expectedResult.add(new ToDo(3, "vacuum", false));

        // Populate the H2 db with 3 todo objects
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));

        //act
        List<ToDo> actualResult = toDoDao.getAllToDos();

        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());

    }

    @Test
    void getOneToDoIT() {
        //arrange
        ToDo expectedResult = new ToDo(1, "sweep", false);
        toDoDao.createToDo(expectedResult);

        //act
        ToDo actualResult = toDoDao.getOneToDo(expectedResult.getId());

        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void createToDoIT() {
        //arrange
        List<ToDo> expectedResult = new ArrayList<>();
        expectedResult.add(new ToDo(1, "sweep", false));
        expectedResult.add(new ToDo(2, "mop", false));
        expectedResult.add(new ToDo(3, "vacuum", false));

        // Populate the H2 db with 3 todo objects
        toDoDao.createToDo(expectedResult.get(0));
        toDoDao.createToDo(expectedResult.get(1));
        toDoDao.createToDo(expectedResult.get(2));

        //act
        // For create, just compare the sizes of the created list and the list obtained from getAllTodos.
        Integer actualResult = toDoDao.getAllToDos().size();

        //assert
        assertEquals(expectedResult.size(), actualResult);
    }

    @Test
    void updateAToDoIT() {
        //arrange
        ToDo toDo  = new ToDo(1, "sweep", false);
        ToDo expectedResult = new ToDo(1,"sweep", true);
        toDoDao.createToDo(toDo);

        //act
        toDoDao.updateAToDo(toDo.getId());
        ToDo actualResult = toDoDao.getOneToDo(toDo.getId());

        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void deleteAToDo() {
        //arrange
        ToDo toDo = new ToDo(1, "sweep", false);
        toDoDao.createToDo(toDo);

        //act
        toDoDao.deleteAToDo(toDo.getId());
        ToDo actualResult = toDoDao.getOneToDo(toDo.getId());

        //assert
        assertNull(actualResult);
    }
}