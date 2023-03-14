package com.reinder42.TodoApp.controller;

import com.reinder42.TodoApp.model.Todo;
import com.reinder42.TodoApp.model.TodoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TodoOverzichtController implements VerversbareInterface
{
    // JavaFX UI elementen
    public ListView<Todo> todoListView;
    public Button nieuweTodoButton;
    public Label todoCountLabel;

    // TodoService met todos array
    private TodoService todoService;

    public TodoOverzichtController() {
        // Lege constructor
    }

    public void initialize() {
        // Let op: todoService is null in initialize()
        // Dus daarom doen we de "setup" in setTodoService()
    }

    public void setTodoService(TodoService service) throws Exception {
        if(service == null) {
            throw new Exception("todoService mag niet null zijn!");
        }

        // Assign service aan todoService
        todoService = service;

        // Vul ListView met todos
        maakTodoList();

        // Maak count label
        updateTodoCount();
    }

    public void maakTodoList()
    {
        // Pak alle Todo objecten
        ArrayList<Todo> todos = todoService.getTodos();

        // Maak er een observable list van
        ObservableList observableList = FXCollections.observableArrayList(todos);

        // Voeg observable list van Todo objecten toe aan list view
        todoListView.setItems(observableList);

        // Maak voor elke Todo een TodoCell object
        todoListView.setCellFactory(listView -> new TodoCell(this, todoService));
    }

    public void updateTodoCount()
    {
        // Pak alle Todo objecten
        ArrayList<Todo> todos = todoService.getTodos();

        // Loop over array met filter(), houd alle 'gedaan' todos, tel aantal in resulterende array
        int gedaan = (int) todos.stream().filter(t -> t.isGedaan()).count();

        // Dit is hetzelfde als ...
        /*int gedaan = 0;

        for(Todo todo : todos) {
            if(todo.isGedaan()) {
                gedaan += 1;
            }
        }*/

        todoCountLabel.setText(gedaan + " / " + todos.size());
    }

    @Override
    public void ververs() {
        maakTodoList();
        updateTodoCount();
    }

    public void handleButtonNieuweTodo(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TodoToevoegen.fxml"));
        Parent root = loader.load();

        TodoToevoegenController controller = loader.getController();
        controller.setTodoOverzichtController(this);
        controller.setTodoService(todoService);

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setResizable(false);
        newStage.setTitle("Nieuwe todo toevoegen");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.showAndWait();
    }
}
