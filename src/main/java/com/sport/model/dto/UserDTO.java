package com.sport.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String telephone;
    private Date createdAt;
    private Date modifiedAt;
    private Integer enabled;
    private String role;
}
