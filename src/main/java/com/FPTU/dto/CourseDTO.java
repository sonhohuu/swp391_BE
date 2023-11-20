package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;

    @NotNull(message = "User null")
    private UserDTO user;

    @NotNull(message = "Title null")
    private String title;

    @NotNull(message = "Description null")
    private String description;

    @NotNull(message = "Price null")
    private Long price;

    private String img;

    @NotNull(message = "Level null")
    private String level;

    @NotNull(message = "Category null")
    private CourseCategoryDTO category;

    private String createdDate;
    private Long duration;
    private Double rating;
    private List<CommentDTO> comments;
}