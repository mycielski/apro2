package com.company;

import java.util.LinkedList;

public class zad2 {
}

class Patient {
    private String name, surname, illness;
    private int priority;

    public Patient(String name, String surname, String illness, int priority) {
        this.name = name;
        this.surname = surname;
        this.illness = illness;
        this.priority = priority;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "patient{}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

class queue {
    private LinkedList<Patient> q = new LinkedList<>();

    public void addPatient(Patient patient){

    }
}
