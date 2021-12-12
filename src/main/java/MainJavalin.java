import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ToDoDaoImpl;
import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import models.ToDo;
import services.ToDoService;

import java.util.List;

public class MainJavalin {

    /*
    * This class is used to test javalin class functionalities.
    * All the commented codes have been copied into ToDoController and Dispatcher classes
    *
    * */

    //the object todoServiceStatic is used with the static method getAllTodos form method implementation rather than lambda implementation
    //static ToDoService toDoServiceStatic = new ToDoService(new ToDoDaoImpl());

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/frontend", Location.CLASSPATH);
        }).start(9000);
        new FrontController(app);














        //ToDoService toDoService = new ToDoService(new ToDoDaoImpl());

        // MainJavalin::getAllToDos is  the method getAllToDos defined in the class MainJavalin class
        /*app.get("/getAllToDos", MainJavalin::getAllToDos);

        // Detailed version
        *//*app.get("/getAllToDos", context -> {
            context.contentType("application/json");
            List<ToDo> toDoList = toDoService.getAllTodos();
            String jsonString = new ObjectMapper().writeValueAsString(toDoList);
            context.result(jsonString);
            //context.result(toDoService.getAllTodos().toString());
        });*//*

        app.get("/getOneToDo", ctx ->{
            ctx.contentType("application/json");
            Integer id = Integer.parseInt(ctx.queryParam("id"));
            String jsonString = new ObjectMapper().writeValueAsString(toDoService.getOneToDo(id));
            ctx.result(jsonString);
        });

        // Method1: using query param
        app.post("/createToDo1", ctx ->{
            String task = ctx.queryParam("task"); // the parameter is type into postmam in query param section
            //This creation use the one the constructor with only the task since id is serial and complete is false by default
            if(task.length() > 20){
                ctx.result("The object has not been created because the task length is over 20");
            }
            else{
                toDoService.createToDo(new ToDo(task));
                ctx.result("The object has been created");
            }
        });

        // Method2: using context.bodyAsClass and typing the task content into postman in body json section
        // The entry in postman should be in json format like: {"task": "I am doing well"}
        app.post("/createToDo2", ctx ->{
            ToDo toDo = ctx.bodyAsClass(ToDo.class);
            if(toDo.getTask().length() > 20){
                ctx.result("The object has not been created.");
            }
            else{
                toDoService.createToDo(toDo);
                ctx.result("The object has been created");
            }
        });

        app.patch("/updateToDo", ctx ->{
            Integer toDoId = Integer.parseInt(ctx.queryParam("id"));
            toDoService.updateAToDo(toDoId);
            ctx.result("ToDo with id " + toDoId + " has been updated");
        });

        app.delete("/deleteToDo", ctx ->{ // if pathParam is used "deleteToDo/{id}'
            Integer toDoId = Integer.parseInt(ctx.queryParam("id")); // if pathParam: Integer toDoId = Integer.parseInt(ctx.pathParam("id"));
            toDoService.deleteATodo(toDoId);
            ctx.result("ToDo with id " + toDoId + " has been deleted");
        });

        // if not found(endpoint not found), the error code is 404. Rather than showing not found, shows the result text.
        app.error(404, context -> {
            context.contentType("text/html");
            context.result("this is server side error");
        });

        app.before("/*", ctx ->{
            ctx.result("Please enter credential first");
        });
    }*/

        // definition of the method getAllToDos used for method implementation rather than lambda implementation
    /*public static void getAllToDos(Context ctx) throws JsonProcessingException {
        ctx.contentType("Application/json");
        // writeValueAsString get an object argument and return a string
        String jsonString = new ObjectMapper().writeValueAsString(toDoServiceStatic.getAllTodos());
        ctx.result(jsonString);
    }*/
    }
}
