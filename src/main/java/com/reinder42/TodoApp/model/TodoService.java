package com.reinder42.TodoApp.model;

import java.util.ArrayList;

public class TodoService implements TodoServiceInterface {
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

    @Override
    public void markeerTodoAlsGedaan(Todo todo, boolean gedaan) {
        if(!todos.contains(todo)) {
            throw new RuntimeException("todo zit niet in todos; integriteit klopt niet!");
        }

        todo.setGedaan(gedaan);
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }
}
