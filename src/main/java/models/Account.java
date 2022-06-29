package models;

import java.util.List;

public class Account {
    String name;
    String password;

    public Account(List<String> List) {
        this.name = List.get(0);
        this.password = List.get(1);

    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

}