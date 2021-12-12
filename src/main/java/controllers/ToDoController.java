package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ToDoDaoImpl;
import io.javalin.http.Context;
import models.ToDo;
import services.ToDoService;


/*
 * Where the implementation of endpoints are going to be.
 * */

public class ToDoController {

    static ToDoService toDoServiceStatic = new ToDoService();

    public static void getAllToDos(Context ctx) throws JsonProcessingException {
        //the result content type
        ctx.contentType("Application/json");
        // writeValueAsString get an object as argument and return it as a string
        String jsonString = new ObjectMapper().writeValueAsString(toDoServiceStatic.getAllTodos());
        // the result sent to the front end
        ctx.result(jsonString);

        /*
         * ctx.json(toDoServiceStatic.getAllTodos()))
         * The above single line set the content type to json,
         * parses the returned object into a string (no need of use of jackson methods),
         * calls ctx.result(jsonString)
         * */
    }
    public static void getOneToDo(Context ctx) throws JsonProcessingException {
        Integer id = Integer.parseInt(ctx.pathParam("id"));
        ctx.json(toDoServiceStatic.getOneToDo(id));
    }

    public static void createToDo(Context ctx) {
        //converts the json string given as input in the body of the request into toDo object
        ToDo toDo = ctx.bodyAsClass(ToDo.class);
        if(toDo.getTask().length() > 20){
            ctx.result("The object has not been created.");
        }
        else{
            toDoServiceStatic.createToDo(toDo);
            ctx.result("The object has been created");
        }
    }

    public static void updateToDo(Context ctx) {
        Integer toDoId = Integer.parseInt(ctx.pathParam("id"));
        toDoServiceStatic.updateAToDo(toDoId);
        ctx.result("ToDo with id " + toDoId + " has been updated");
    }

    public static void deleteToDo(Context ctx) {
        Integer toDoId = Integer.parseInt(ctx.pathParam("id"));
        toDoServiceStatic.deleteATodo(toDoId);
        ctx.result("ToDo with id " + toDoId + " has been deleted if it exists.");
    }
}
