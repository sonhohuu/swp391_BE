package com.FPTU.converter;

import com.FPTU.dto.CourseDetailDTO;
import com.FPTU.model.CourseDetail;
import org.springframework.stereotype.Component;

@Component
public class CourseDetailConverter {
    public CourseDetail toEntity(CourseDetailDTO courseDetailDTO) {
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setTitle(courseDetailDTO.getTitle());
        courseDetail.setDescription(courseDetailDTO.getDescription());
        courseDetail.setDocument(courseDetailDTO.getDocument());
        courseDetail.setVideoURL(courseDetailDTO.getUrl());
        courseDetail.setEstimatedTime(courseDetailDTO.getEstimatedTime());
        return courseDetail;
    }
    public CourseDetailDTO toDTO(CourseDetail courseDetail) {
        CourseDetailDTO courseDetailDTO = new CourseDetailDTO();
        courseDetailDTO.setId(courseDetail.getVideoId());
        courseDetailDTO.setDescription(courseDetail.getDescription());
        courseDetailDTO.setTitle(courseDetail.getTitle());
        courseDetailDTO.setDocument(courseDetail.getDocument());
        courseDetailDTO.setUrl(courseDetail.getVideoURL());
        courseDetailDTO.setEstimatedTime(courseDetail.getEstimatedTime());
        courseDetailDTO.setCourseId(courseDetail.getCourse().getCourseId());
        return courseDetailDTO;
    }
    public CourseDetail toEntity(CourseDetailDTO courseDetailDTO, CourseDetail courseDetail) {
        courseDetail.setTitle(courseDetailDTO.getTitle());
        courseDetail.setDescription(courseDetailDTO.getDescription());
        courseDetail.setDocument(courseDetailDTO.getDocument());
        courseDetail.setVideoURL(courseDetailDTO.getUrl());
        courseDetail.setEstimatedTime(courseDetailDTO.getEstimatedTime());
        return courseDetail;
    }
}