package frontcontroller;

import io.javalin.Javalin;

public class FrontController {

    public FrontController(Javalin app){
        /*
        * middleware
        *
        * app.before("/protected/*", ClassName::methodName)
        *
        * */

        app.exception(NumberFormatException.class, (e, context) ->{
            context.result("Invalid! Expected a number");
        });

        new Dispatcher(app);

    }

}




