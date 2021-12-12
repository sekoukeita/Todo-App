package frontcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.ToDoController;
import io.javalin.Javalin;
import models.ToDo;
import services.ToDoService;

import static io.javalin.apibuilder.ApiBuilder.*;

/*
* This is where we are going to route our endpoints to the specific methods.
* */

/*
* REST: Representational State Transfer
* - It is architectural standard for HTTP
* - Below is how Restfull endpoints look like.
* - With RESTFULL naming queryParam are replaced by pathParam in the getOne, path and delete.
* */
public class Dispatcher {

    public Dispatcher(Javalin app){

        /*
                                                          //Endpoints before following RESTFULL naming standard
        app.get("/todo", ToDoController::getAllToDos);  //  /getAllToDos

        app.get("/todo/{id}", ToDoController::getOneToDo); // /getOneToDo  Now using pathParam
        app.post("/todo", ToDoController::createToDo);  //  /createToDo

        app.patch("/todo/{id}", ToDoController::updateToDo);  //  /updateToDo  Now using pathParam

        app.delete("/todo/{id}", ToDoController::deleteToDo); //  /deleteToDo

        */

        // In case of bigger project with many endpoints. Single location for endpoints

        app.routes(() -> {
            path("todo", () -> {
                get(ToDoController::getAllToDos);
                post(ToDoController::createToDo);
                path("{id}", () -> {
                    get(ToDoController::getOneToDo);
                    patch(ToDoController::updateToDo);
                    delete(ToDoController::deleteToDo);
                });
            });
        });
    }
}
