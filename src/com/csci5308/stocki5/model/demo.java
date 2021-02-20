package com.csci5308.stocki5.model;

public class demo {
    int actor_id;
    String name;

    public demo(int actor_id, String name) {
        this.actor_id = actor_id;
        this.name = name;
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
