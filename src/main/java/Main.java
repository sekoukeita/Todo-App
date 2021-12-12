import dao.ToDoDao;
import dao.ToDoDaoImpl;
import models.ToDo;
import services.ToDoService;

public class Main {

    /*
    * This main class is used to test the Dao interface methods.
    * */

    public static void main(String[] args) {
        ToDoDao toDoDao = new ToDoDaoImpl(); // upcasting

        // create the service object that will call ToDoService class methods.
        //ToDoService service = new ToDoService(new ToDoDaoImpl());

        //service.completeTodo(10);
        System.out.println(toDoDao.getAllToDos());

        //System.out.println(toDoDao.getAllToDos());
        //System.out.println(toDoDao.getOneToDo(10));

        /*The id doesn't matter because this field is of type serial in the db*/
        //toDoDao.updateAToDo(14);
        //toDoDao.updateAToDo(9);
        //toDoDao.deleteAToDo(11);
        //toDoDao.createToDo(new ToDo("Love is nice"));

        //System.out.println(toDoDao.getOneToDo(14));

    }
}
