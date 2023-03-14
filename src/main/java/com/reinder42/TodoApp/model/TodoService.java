package com.reinder42.TodoApp.model;

import java.util.ArrayList;

public class TodoService {
    private ArrayList<Todo> todos;

    public TodoService() {
        todos = new ArrayList<Todo>();
    }

    public void voegTodoToe(String text) {
        Todo todo = new Todo();
        todo.setText(text);
        voegTodoToe(todo);
    }

    public void voegTodoToe(Todo todo) {
        todos.add(todo);
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }
}
