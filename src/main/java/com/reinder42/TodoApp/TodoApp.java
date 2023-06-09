package com.reinder42.TodoApp;

import com.reinder42.TodoApp.controller.TodoOverzichtController;
import com.reinder42.TodoApp.model.Todo;
import com.reinder42.TodoApp.model.TodoService;
import com.reinder42.TodoApp.model.TodoServiceInterface;
import com.reinder42.TodoApp.model.TodoServicePersisted;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TodoApp extends Application
{
    private TodoServiceInterface todoService = null;

    public TodoApp()
    {
        // Nieuwe todo service maken
        todoService = new TodoServicePersisted(); // new TodoService();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Let op: FXML bestanden zitten in /resources
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TodoOverzicht.fxml"));
        Parent root = loader.load();

        // Todo service injecteren in overzicht controller
        TodoOverzichtController controller = loader.getController();
        controller.setTodoService(todoService);

        stage.setTitle("Todo's");
        stage.setScene(new Scene(root));
        stage.show();
    }
}

