package com.example.lostandfoundapp.model;

public class LostFoundMod {

    private String name;
    private String phone;
    private String date;
    private String description;
    private String location;
    private String type;
    private int item_ID;

    public void setItem_ID(int item_ID) {
        this.item_ID = item_ID;
    }

    public LostFoundMod(String type, String name, String phone, String description, String date,
                        String location)
    {
        this.type = String.valueOf(type);
        this.name = String.valueOf(name);
        this.phone = String.valueOf(phone);
        this.description = String.valueOf(description);
        this.date = String.valueOf(date);
        this.location = String.valueOf(location);
    }

    public LostFoundMod(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
