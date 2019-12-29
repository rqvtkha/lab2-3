package com.example.lab2_3;

public class Item {
    private String name;
    private String userName;
    private int id;

    public Item(int id,String name, String userName){

        this.id=id;
        this.name=name;
        this.userName=userName;
    }

    public int getId(){
        return this.id;
    }

    public  void setId(int id)
    {
        this.id=id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

}
