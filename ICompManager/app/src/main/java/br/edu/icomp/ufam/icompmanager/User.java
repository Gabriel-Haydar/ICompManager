package br.edu.icomp.ufam.icompmanager;

public class User {
    private int id;
    private String name;
    private String function;
    private String password;
    private String notes;

    User(int id, String name, String function, String password, String notes) {
        this.id = id;
        this.name = name;
        this.function = function;
        this.password = password;
        this.notes = notes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
