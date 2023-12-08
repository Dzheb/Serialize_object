package org.example;

import java.io.Serial;
import java.io.Serializable;

public class Department implements Serializable {
@Serial
private static final long serialVersionUID = 2L;
    public final String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}
