package com.reinder42.TodoApp.model;

import java.util.ArrayList;

public class TodoService implements TodoServiceInterface {
    private ArrayList<Todo> todos;

    public TodoService() {
        // Initialiseer lege array
        todos = new ArrayList<Todo>();

        // Nieuwe afgevinkte todo
        Todo todo = new Todo();
        todo.setText("Pakketje halen bij boekhandel");
        todo.setGedaan(true);

        // Todo's "hard-coded" toevoegen aan app
        voegTodoToe("Boodschappen doen");
        voegTodoToe("Lekkende kraan repareren");
        voegTodoToe(todo);
        voegTodoToe("Hardlopen met Alex");
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
