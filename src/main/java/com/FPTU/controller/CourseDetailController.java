package com.FPTU.controller;

import com.FPTU.dto.CourseDetailDTO;
import com.FPTU.dto.CourseDetailRequest;
import com.FPTU.exceptions.CourseDetailNotFoundException;
import com.FPTU.exceptions.CourseNotFoundException;
import com.FPTU.service.CourseDetailService;
import com.FPTU.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/coursedetail")
@CrossOrigin("http://127.0.0.1:5173/")
public class CourseDetailController {
    @Autowired
    private CourseDetailService courseDetailService;
    @Autowired
    private CourseService courseService;


    @GetMapping("/{course_id}")
    public List<CourseDetailDTO> getCourseDetailByCourseId(@PathVariable("course_id") Long courseId) {
        if(!courseService.existsById(courseId)) {
            throw new CourseNotFoundException(courseId);
        }
        return courseDetailService.findAllByCourseId(courseId);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping()
    public ResponseEntity<CourseDetailDTO> addCourseDetail(@RequestBody @Valid CourseDetailDTO courseDetailDTO) {
        courseDetailDTO = courseDetailService.save(courseDetailDTO);
        return  ResponseEntity.ok(courseDetailDTO);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/list")
    public ResponseEntity<List<CourseDetailDTO>> addListCourseDetail(@RequestBody @Valid CourseDetailRequest request) {
        List<CourseDetailDTO> listCourse = courseDetailService.saveAll(request.getCourseDetails(), request.getCourseId());
        return  ResponseEntity.ok(listCourse);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDetailDTO> updateCourseDetail(@RequestBody @Valid CourseDetailDTO courseDetailDTO, @PathVariable("id") Long id) {
        if(!courseDetailService.existsById(id)) {
            throw new CourseDetailNotFoundException(id);
        }
        courseDetailDTO.setId(id);
        courseDetailService.save(courseDetailDTO);
        return ResponseEntity.ok(courseDetailDTO);
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public String deleteDetail(@PathVariable("id") Long id) {
        if(!courseDetailService.existsById(id)) {
            throw new CourseDetailNotFoundException(id);
        }
        courseDetailService.deleteById(id);
        return "Delete the course's detail by id " + id;
    }

}