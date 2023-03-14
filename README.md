# TodoApp

## Installatie

Je kunt deze repository direct van GitHub binnenhengelen in bijv. IntelliJ IDEA. Zorg dat je de JavaFX library toevoegt, zoals beschreven in de installatiehandleiding op Canvas.

Merk op dat de `.fxml` bestanden in de resources root `/resources` zitten, en dus niet naast de controllers zijn opgeslagen.

## Points of Interest

De TodoApp applicatie laat een simpele lijst van taken zien. Je kunt taken toevoegen en afvinken.

Er zijn 3 User Interfaces, nl. een overzicht van todo's, een UI om een todo toe te voegen, en een `TodoCell.fxml` view die per todo getoond wordt in de `ListView` in `TodoOverzicht.fxml`. In `TodoCell.java` en `TodoOverzichtController.java` kan je zien hoe het laden van een 'custom view' in een `ListView` in z'n werk gaat.

Zowel `TodoCell` als `TodoToevoegenController` hebben een referentie terug naar hun "parent" `TodoOverzichtController`. Dit wordt bewerkstelligd met een interface, zodat er een vorm van _loose coupling_ ontstaat. Het maakt bijv. voor de `TodoToevoegenController` niet uit op welk object `ververs()` aangeroepen wordt, als er een nieuwe todo is toegevoegd, zolang dit object maar aan de `VerversbareInterface` interface voldoet.

Ook `TodoService` en `TodoServicePersisted` werken met polymorfisme, d.m.v. de `TodoServiceInterface`. De TodoServicePersisted heeft een extra implementatie die een `ArrayList<Todo>` leest uit een todos.obj bestand. Hierdoor worden taken tussen herstarts van de applicatie onthouden. De `TodoService` werkt simpelweg met een hard-coded todo list waar todo's aan toegevoegd kunnen worden.