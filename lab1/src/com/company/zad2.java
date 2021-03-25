package com.company;

import java.util.LinkedList;

public class zad2 {

    static Queue queue = new Queue();

    public static void main(String[] args) {
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

        System.out.println(queue);
    }
}

class Patient implements Comparable<Patient> {
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
        return "Pacjent: " + this.name + " " + this.surname + ", Dolegliwość " + this.illness + ", Priorytet : " + this.priority;
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


    @Override
    public int compareTo(Patient o) {
        return o.getPriority()-getPriority();
    }
}

class Queue {
    private final LinkedList<Patient> q = new LinkedList<>();

    public Patient getFirst(){
        Patient patient = q.getFirst();
        q.removeFirst();
        return patient;
    }

    public void addPatient(Patient patient) {
        if (patient.getPriority() == 4 || q.size() == 0 || q.getLast().getPriority() < patient.getPriority()) {
            q.addLast(patient);
        } else if (q.getFirst().getPriority() > patient.getPriority()) {
            q.addFirst(patient);
        } else {
            for (int i = 0; i < q.size(); i++) {
                if (q.get(i).compareTo(patient) >= 0 && q.get(i+1).compareTo(patient) < 0) { //(q.get(i).getPriority() <= patient.getPriority() && patient.getPriority() < q.get(i + 1).getPriority()) {
                    q.add(i + 1, patient);
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Patient patient : q) {
            output.append(patient.toString()).append("\n");
        }
        return new StringBuilder().append("Queue{").append("q=\n").append(output).append("\n").toString();
    }
}
