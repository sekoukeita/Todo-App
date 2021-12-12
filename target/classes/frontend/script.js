/* 
<div class="todo-item" id="1">
    <div class="todo-info">
        <span class="todo-task">Sweep the floor</span>
        <span class="todo-status green">Completed</span>
    </div> 
    <div class="todo-btns">
        <button class="btn btn-primary" id="complete-btn-1">Complete</button>
        <button class="btn btn-danger" id="delete-btn-1">Delete</button>
    </div>
</div>

*/

let domain = "";

window.addEventListener("load", () => {
    populateTodos();
})

async function populateTodos(){

    //fetch todos from backend. Do this first to avoid delay in execution.
    let response = await fetch(`${domain}/todo`)
    let data = await response.json();

    // sort the todos returned by order of ids.
    data.sort((a, b) => a.id > b.id ? -1 : 1);


    // clear the container
    let todoContainer = document.getElementById("todo-container");
    todoContainer.innerHTML = "";

    
    // loop through each todo and create the dom elements for the todo
    data.forEach(todo => {
        
        // create the todo-item element and change its innerHTML
        let todoItemElem = document.createElement("div");
        todoItemElem.className = "todo-item";
        todoItemElem.id = todo.id;

        todoItemElem.innerHTML = `
        <div class="todo-info">
            <span class="todo-task">${todo.task}</span>
            ${todo.completed ? '<span class="todo-status green">Complete</span>' : '<span class="todo-status red">Incomplete</span>'}
        </div> 
        <div class="todo-btns">
            ${todo.completed ? '' : `<button class="btn btn-primary" id="complete-btn-${todo.id}" onclick="completeTodo(event)">Complete</button>`}                                  
            <button class="btn btn-danger" id="delete-btn-${todo.id}" onclick="deleteTodo(event)">Delete</button>
        </div>
        `
        // append newly created element to the container
        todoContainer.appendChild(todoItemElem);

    })
}

async function createTodo(e){
    e.preventDefault(); // stop from automatically refreshing the page.

    // get the value from the task input
    let taskInputElem = document.getElementById("task-input");
    let task = taskInputElem.value; // get the value typed in the input text in the form.

    // send post request with task in the body
    await fetch(`${domain}/todo`, {
        method: "POST",
        body: JSON.stringify({ // convert the javascript object into string before sending it through the request.
            task: task
        })
    })

    taskInputElem.value = ""; // clear the value in the text input.
    populateTodos();
}

async function completeTodo(e){
    // get id of todo to complete
    let id = e.target.id.slice("complete-btn-".length, e.target.id.length); // event.target return the elt the event happen on. 

    // send request to complete todo
    await fetch(`${domain}/todo/${id}`, {
        method: "PATCH"
    })

    populateTodos();
}

async function deleteTodo(e){
     // get id of todo to complete
     let id = e.target.id.slice("delete-btn-".length, e.target.id.length); // event.target return the elt the event happen on. 

     // send request to complete todo
     await fetch(`${domain}/todo/${id}`, {
         method: "DELETE"
     })
 
     populateTodos();
}