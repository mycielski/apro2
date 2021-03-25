package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class zad2Test {

    Queue queue = new Queue();

    @BeforeEach
    public void setUp() throws Exception{
        Patient pacjent = new Patient("Tomasz", "Mycielski", "złamana ręka", 2);
        Patient pacjent2 = new Patient("Antoni", "Adamowicz", "bolą plecy", 4);
        Patient pacjent3 = new Patient("Bartosz", "Bartniczuk", "nadwichnięcie kręgu szyjnego", 1);
        Patient pacjent4 = new Patient("Cezary", "Ciszewski", "COVID-19", 3);
        Patient pacjent5 = new Patient("Danuta", "Daniluk", "COVID-20", 1);
        Patient pacjent6 = new Patient("Edmund", "Etterbeek", "Sars-Cov 3", 4);
        Patient pacjent7 = new Patient("Filip", "Fijałkowski", "Poparzenia drugiego stopnia", 2);
        queue.addPatient(pacjent6);
        queue.addPatient(pacjent5);
        queue.addPatient(pacjent);
        queue.addPatient(pacjent7);
        queue.addPatient(pacjent4);
        queue.addPatient(pacjent3);
        queue.addPatient(pacjent2);
    }

    @Test
    public void testAdditionToQueue(){
        Patient pacjent0 = new Patient("Tadeusz", "Tarkowski", "schizofrenia", 4);
        queue.addPatient(pacjent0);
        assertEquals(8, queue.getSize());
    }


}