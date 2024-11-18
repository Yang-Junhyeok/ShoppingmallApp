package com.example.test1104.constant;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    Role(String value){
        this.value = value;
    }
    private String value;
}
