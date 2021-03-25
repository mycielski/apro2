package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class zad2Test {

    static Queue queue = new Queue();

    @BeforeAll
    static void setUp() {
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
    public void testAdditionToQueue() {
        Patient pacjent0 = new Patient("Tadeusz", "Tarkowski", "schizofrenia", 4);
        int expected = queue.getSize() + 1;
        queue.addPatient(pacjent0);
        assertEquals(expected, queue.getSize());
    }

    @RepeatedTest(4)
    public void testPrioritiesOrderInQueue() {
        System.out.println(queue);
        assertTrue(queue.getFirst().compareTo(queue.getFirst()) >= 0);
    }

}