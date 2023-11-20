package com.FPTU.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "Username null")
    private String username;

    @NotNull(message = "Name null")
    private String name;

    @NotNull(message = "Img null")
    private String img;

    @NotNull(message = "Address null")
    private String address;

}