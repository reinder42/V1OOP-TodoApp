package com.reinder42.TodoApp.model;

import java.util.ArrayList;

public interface TodoServiceInterface {
    public void voegTodoToe(String text);
    public void voegTodoToe(Todo todo);
    public void markeerTodoAlsGedaan(Todo todo, boolean gedaan);
    public ArrayList<Todo> getTodos();
}
