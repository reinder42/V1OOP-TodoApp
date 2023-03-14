package com.reinder42.TodoApp.controller;

import com.reinder42.TodoApp.model.Todo;
import com.reinder42.TodoApp.model.TodoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TodoToevoegenController
{
    // Tekstveld
    @FXML private TextField todoTextField;

    // Reference naar overzicht controller
    private VerversbareInterface verversbareInterface;

    // Reference naar todo service object
    private TodoService todoService;

    public void setTodoOverzichtController(VerversbareInterface verversbareInterface) {
        this.verversbareInterface = verversbareInterface;
    }

    public void setTodoService(TodoService service) {
        todoService = service;
    }

    public void handleButtonVoegToe(ActionEvent actionEvent) throws Exception
    {
        // Check of overzicht controller er is
        if(verversbareInterface == null) {
            throw new Exception("verversbareInterface is null!");
        }

        // Check of service er is
        if(todoService == null) {
            throw new Exception("todoService is null!");
        }

        // String van tekstveld pakken
        String text = todoTextField.getText();

        // Als leeg, "focus" het tekstveld en doe niks
        if(text.isEmpty()) {
            todoTextField.requestFocus();
            return; // Doe niks
        }

        // Maak een nieuw todo object
        Todo todo = new Todo();
        todo.setText(text);

        // Voeg toe aan service
        todoService.voegTodoToe(todo);

        // Zorg dat overzicht zichzelf update
        verversbareInterface.ververs();

        // Sluit het scherm
        sluiten();
    }

    public void handleButtonAnnuleren(ActionEvent actionEvent) {
        sluiten();
    }

    public void sluiten() {
        // Hack: Stage pakken via text field (of elk ander veld met fx:id)
        Stage stage = (Stage) todoTextField.getScene().getWindow();

        // Stage en dus venster sluiten
        stage.close();
    }
}
