package com.bay.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetail {
    private String name;
    private String branch;
    private String role;
}
