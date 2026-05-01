// Shared script for login, register, and todos pages
const SERVER_URL = "http://localhost:8080";
const token = localStorage.getItem("token");

function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch(`${SERVER_URL}/auth/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Invalid email or password");
        }
        return response.json();  
    })
    .then(data => {
        console.log("Login success:", data);

        localStorage.setItem("token", data.token);
        window.location.href = "todos.html";
    })
    .catch(error => {
        console.error(error);
        alert(error.message);
    });
}

// Register page logic
function register() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch(`${SERVER_URL}/auth/register`,{
        method:"POST",
        headers:{
             "Content-Type": "application/json"
        },
        body:JSON.stringify({email,password})
    })
    .then(response => {
            console.log("Response status:", response.status);

            if (!response.ok) {
                return response.json().then(data => {
                    throw new Error(data.message || "Registration failed");
                });
            }

            return response.json();
        })
        .then(data => {
            console.log("Success:", data);
            alert("Registration successful");
            window.location.href = "login.html";
        }).catch(error =>{
        alert(error.message);
    })

}

// Todos page logic
function createTodoCard(todo) {
    const card = document.createElement("div")
    card.className = "todo-card"

    const checkbox =document.createElement("input");
    checkbox.type = "checkbox"
    checkbox.checked = todo.iscompleted
    checkbox.addEventListener("change",function(){
        const updateTodo = {...todo,iscompleted : checkbox.iscompleted}
        updateTodoStatus(updateTodo)
    });

    const span = document.createElement("span")
    span.textContent = todo.title;

    if(todo.iscompleted){
        span.style.textDecoration = "line-through"
        span.style.color = "#aaa"
    }

    const deleteBtn = document.createComment("button")
    deleteBtn.textContent = "X"
    deleteBtn.onclick = function(){
        deleteTodo(todo.id)
    }
    card.appendChild(checkbox)
    card.appendChild(span)
    card.appendChild(deleteBtn)


    return card;
}

function loadTodos() {

}

function addTodo() {

}

function updateTodoStatus(todo) {
    fetch(`${SERVER_URL}/`)
}

function deleteTodo(id) {
    fetch(`${SERVER_URL}/delete/${id}`, {
            method: "DELETE",
            headers: {
                Authorization: `Bearer ${token}`
            }, 
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Todo-deleted");
            }
            return response.text();  
        })
        //load the todos after delete
        .then(() =>loadTodos())
        .catch(error => {
            console.error(error);
            alert(error.message);
        });
}

// Page-specific initializations
document.addEventListener("DOMContentLoaded", function () {
    if (document.getElementById("todo-list")) {
        loadTodos();
    }
});