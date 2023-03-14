package com.reinder42.TodoApp.controller;

import com.reinder42.TodoApp.model.Todo;
import com.reinder42.TodoApp.model.TodoService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class TodoCell extends ListCell<Todo>
{
    // Todo object
    private Todo todo;

    // Reference naar TodoService
    private TodoService todoService;

    // Reference naar "parent" overzicht controller object
    private VerversbareInterface verversbareInterface;

    // JavaFX UI elementen
    @FXML private CheckBox checkBox;
    @FXML private HBox hBox;

    // JavaFX loader
    private FXMLLoader loader;

    public TodoCell(VerversbareInterface verversbareInterface, TodoService todoService) {
        // Maak een associatie met de "parent" controller
        this.verversbareInterface = verversbareInterface;
        this.todoService = todoService;
    }

    public void initialize()
    {
        // Verbind een event handler aan onAction van checkBox
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // gedaan en 'isSelected' hebben dezelfde waarde (boolean)
                todoService.markeerTodoAlsGedaan(todo, checkBox.isSelected());

                // Update todo counter via parent controller
                verversbareInterface.ververs();
            }
        });
    }

    @Override
    protected void updateItem(Todo todo, boolean empty) {
        super.updateItem(todo, empty);

        // Kijk of todo leeg is
        if(todo == null || empty) {
            return;
        }

        // Assign todo uit array aan eigenschap
        this.todo = todo;

        // Als nog niet geladen, laad FXML
        if(loader == null) {
            loader = new FXMLLoader(getClass().getResource("/TodoCell.fxml"));
            loader.setController(this);

            try {
                loader.load();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // Stel tekst en checkbox in
        checkBox.setText(todo.getText());
        checkBox.setSelected(todo.isGedaan());

        // Assign custom view hBox aan 'graphic' van ListViewCell
        setText(null);
        setGraphic(hBox);
    }
}
