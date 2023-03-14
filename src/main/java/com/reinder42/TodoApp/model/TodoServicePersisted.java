package com.reinder42.TodoApp.model;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TodoServicePersisted implements TodoServiceInterface
{
    // Array met Todo objecten (single source of truth)
    private ArrayList<Todo> todos;

    // String met bestandsnaam
    private static String filename = "todos.obj";

    public TodoServicePersisted()
    {
        // Laad Todo's uit todos.obj, returnt een lege array
        // als er iets fout gaat of het bestand niet bestaat.
        todos = todosLadenUitBestand();
    }

    public void voegTodoToe(String text) {
        Todo todo = new Todo();
        todo.setText(text);
        voegTodoToe(todo);
    }

    public void voegTodoToe(Todo todo)
    {
        // Voeg toe aan array
        todos.add(todo);

        // Sla Todo objecten op in todos.obj
        todosOpslaanInBestand();
    }

    @Override
    public void markeerTodoAlsGedaan(Todo todo, boolean gedaan)
    {
        if(!todos.contains(todo)) {
            throw new RuntimeException("todo zit niet in todos; integriteit klopt niet!");
        }

        // Markeer todo als gedaan
        todo.setGedaan(gedaan);

        // Sla Todo objecten op in todos.obj
        todosOpslaanInBestand();
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    private ArrayList<Todo> todosLadenUitBestand()
    {
        // Maak lege array
        ArrayList<Todo> nieuweTodos = new ArrayList<Todo>();

        try {
            // Zet een nieuwe stream op naar het bestand
            InputStream inputStream = Files.newInputStream(Path.of(filename));

            // Zet een nieuwe "object stream" op
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            // Lees het object uit het bestand, d.w.z. "het object" hier is een array van Todo objecten!
            Object object = objectInputStream.readObject();

            // Cast het object naar een ArrayList van Todo objecten
            // Let op: er is geen garantie dat er Todo objecten in de ArrayList uit todos.obj zitten.
            // Het enige alternatief (dat veiliger is), is Todo objecten 1 voor 1 lezen/toevoegen aan de array.
            if(object instanceof ArrayList) {
                nieuweTodos = (ArrayList<Todo>) object;
            }

            // Sluit de stream naar het bestand
            objectInputStream.close();

        } catch(Exception e) {
            System.out.println(e);
        }

        return nieuweTodos;
    }

    private void todosOpslaanInBestand()
    {
        try {
            OutputStream outputStream = Files.newOutputStream(Path.of(filename));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // Schrijf todos array naar todos.obj
            objectOutputStream.writeObject(todos);

            // Sluit output stream
            objectOutputStream.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
