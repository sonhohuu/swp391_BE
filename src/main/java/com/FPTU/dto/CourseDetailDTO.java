package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {
    private Long id;

    @NotNull(message = "CourseId null")
    private Long courseId;

    @NotNull(message = "Title null")
    private String title;

    @NotNull(message = "Description null")
    private String description;

    @NotNull(message = "Url null")
    private String url;

    @NotNull(message = "EstimatedTime null")
    private Long estimatedTime;

    @NotNull(message = "Document null")
    private String document;

}