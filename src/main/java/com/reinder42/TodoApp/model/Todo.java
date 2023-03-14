package com.reinder42.TodoApp.model;

import java.io.Serializable;

public class Todo implements Serializable {
    private String text;
    private boolean gedaan;

    public Todo() {
        text = "";
        gedaan = false;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setGedaan(boolean gedaan) {
        this.gedaan = gedaan;
    }

    public String getText() {
        return text;
    }

    public boolean isGedaan() {
        return gedaan;
    }

    public String toString() {
        return text + (gedaan ? "(gedaan)" : "");
    }

    public boolean equals(Object object) {
        if(object == null || object instanceof Todo == false) {
            return false;
        }

        Todo todo = (Todo) object;

        return text.equals(todo.getText());
    }
}
