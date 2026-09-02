package com.manuitwork.expensetracker.group;

import jakarta.validation.constraints.NotBlank;

public class CreateGroupForm {

    @NotBlank(message = "Group Name is required.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name != null) ? name.trim() : null;
    }
}
