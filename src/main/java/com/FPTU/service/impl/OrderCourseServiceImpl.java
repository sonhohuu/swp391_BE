package com.FPTU.service.impl;

import com.FPTU.converter.CourseConverter;
import com.FPTU.converter.OrderCourseConverter;
import com.FPTU.dto.*;
import com.FPTU.model.Course;
import com.FPTU.model.OrderCourse;
import com.FPTU.model.OrderDetailCourse;
import com.FPTU.model.User;
import com.FPTU.repository.CourseRepository;
import com.FPTU.repository.OrderCourseRepository;
import com.FPTU.repository.OrderDetailCourseRepository;
import com.FPTU.repository.UserRepository;
import com.FPTU.service.OrderCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class OrderCourseServiceImpl implements OrderCourseService {

    @Autowired
    private OrderCourseRepository orderCourseRepository;
    @Autowired
    private OrderCourseConverter orderCourseConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseConverter courseConverter;

    @Autowired
    private OrderDetailCourseRepository orderDetailCourseRepository;

    @Override
    public List<OrderCourseDTO> findAll() {
        List<OrderCourse> list = orderCourseRepository.findAllByOrderDateDesc();
        return getListDTO(list);
    }
    @Override
    public OrderCourseDTO save(OrderCourseDTO orderCourseDTO) {

        OrderCourse orderCourse = new OrderCourse();
        orderCourse = orderCourseConverter.toEntity(orderCourseDTO);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        orderCourse.setOrderDate(formattedDateTime);

        User user = userRepository.findByUsername(orderCourseDTO.getUser().getUsername());
        orderCourse.setUser(user);
        orderCourse = orderCourseRepository.save(orderCourse);
        for (CourseDTO c : orderCourseDTO.getCourses()) {
            OrderDetailCourse orderDetailCourse = new OrderDetailCourse();
            orderDetailCourse.setCourse(courseRepository.getOne(c.getId()));
            orderDetailCourse.setOrderCourse(orderCourseRepository.getOne(orderCourse.getOrderId()));
            orderDetailCourseRepository.save(orderDetailCourse);
        }
        return orderCourseConverter.toDTO(orderCourse);
    }

    @Override
    public OrderCourseDTO findById(Long id) {
        OrderCourseDTO orderCourseDTO = orderCourseConverter.toDTO(orderCourseRepository.getOne(id));
        List<Course> courses = courseRepository.findCourseByOrderId(orderCourseDTO.getId());
        List<CourseDTO> coursesDTO = new ArrayList<>();
        for (Course c: courses) {
            coursesDTO.add(courseConverter.toDTO(c));
        }

        orderCourseDTO.setCourses(coursesDTO);
        return orderCourseDTO;
    }

    @Override
    public List<OrderRevenueByMonth> getMonthlyRevenue() {
        List<OrderRevenueByMonth> list = new ArrayList<>();
        for (Object[] o : orderCourseRepository.getMonthlyRevenue()) {
            OrderRevenueByMonth or = new OrderRevenueByMonth(o);
            list.add(or);
        }
        return list;
    }

    @Override
    public List<InstructorStatic> getInstructorStatic(String username) {
        User user = userRepository.findByUsername(username);

        List<InstructorStatic> list = new ArrayList<>();
        for (Object[] o : orderCourseRepository.getInstructorStatic(user.getUserId())) {
            InstructorStatic i = new InstructorStatic(o);
            list.add(i);
        }
        return list;
    }

    @Override
    public List<InstructorHistory> getInstructorHistory(String username) {
        User user = userRepository.findByUsername(username);

        List<InstructorHistory> list = new ArrayList<>();
        for (Object[] o : orderCourseRepository.getInstructorHistory(user.getUserId())) {
            InstructorHistory i = new InstructorHistory(o);
            list.add(i);
        }
        return list;
    }

    @Override
    @Transactional
    public String updateStatus(Long orderId, String newStatus) {
        // Implement the logic to update the status based on orderId and newStatus
        OrderCourse orderCourse = orderCourseRepository.findById(orderId).orElse(null);
        if (orderCourse.getStatus() == "Old") {
            return "Old Status";
        }
        if (orderCourse != null) {
            orderCourse.setStatus(newStatus);
            orderCourseRepository.save(orderCourse);
        }
        return "Update Success";
    }

    @Override
    public List<OrderCourseDTO> findByUserNameRoleCustomer(String username) {
        List<OrderCourse> list = orderCourseRepository.findByUser_UserId(userRepository.findByUsername(username).getUserId());
        return getListDTO(list);
    }

    @Override
    public List<OrderCourseDTO> findByUserNameRoleInstructor(String username) {
        return null;
    }

    private List<OrderCourseDTO> getListDTO(List<OrderCourse> list) {
        List<OrderCourseDTO> listDTO = new ArrayList<>();
        for (OrderCourse o : list) {
            OrderCourseDTO orderCourseDTO = orderCourseConverter.toDTO(o);

            String orderDate = getOrderDate(o.getOrderDate());
            orderCourseDTO.setOrderDate(orderDate);

            List<Course> courses = courseRepository.findCourseByOrderId(orderCourseDTO.getId());
            List<CourseDTO> coursesDTO = new ArrayList<>();
            for (Course c: courses) {
                coursesDTO.add(courseConverter.toDTO(c));
            }

            orderCourseDTO.setCourses(coursesDTO);
            listDTO.add(orderCourseDTO);
        }
        return listDTO;
    }

    public String getOrderDate(String l) {
        String time = "";
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime orderDate = convertStringToLocalDateTime(l);
        Duration duration = Duration.between(orderDate, currentTime);
        long minutes = duration.toMinutes();

        if (minutes < 60) {
            time = minutes + " minutes ago";
        } else if (minutes < (24 * 60)) {
            long hours = duration.toHours();
            time = hours + " hours ago";
        } else if (minutes < (24 * 60 * 30)) {
            long days = duration.toDays();
            time = days + " days ago";
        } else {
            long months = minutes / (24 * 60 * 30);
            time = months + " months ago";
        }

        return time;
    }

    public LocalDateTime convertStringToLocalDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateString, formatter);
    }
}