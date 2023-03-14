package com.reinder42.TodoApp.controller;

// De 'VerversbareInterface' interface kan gebruikt worden om een controller verversbaar
// te maken. De afspraak is dat deze controller 'ververst' kan worden. O.a. TodoCell en
// TodoToevoegen maken hier gebruik van -- ze kunnen bijv. de TodoOverzichtController
// zeggen dat die moet verversen. Dit design pattern heet 'delegation'.
//
// Waarom niet gewoon een referentie maken naar de TodoOverzichtController? Omdat TodoCell
// en TodoToevoegen nu "ontkoppeld" of "los gekoppeld" zijn van de TodoOverzichtController.

public interface VerversbareInterface {
    public void ververs();
}
