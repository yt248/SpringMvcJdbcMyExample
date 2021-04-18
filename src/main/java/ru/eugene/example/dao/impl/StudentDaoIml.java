package ru.eugene.example.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.eugene.example.dao.StudentDAO;
import ru.eugene.example.model.Student;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDaoIml implements StudentDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDaoIml(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "select * from students";
        return jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<>(Student.class));
    }

    @Override
    public void insert(Student student) {
        String insertQuery = "INSERT INTO students (firstname, lastname, email, age) VALUES (?,?,?,?)";
        jdbcTemplate.update(insertQuery, student.getFirstname(), student.getLastname(), student.getEmail(), student.getAge());
    }

    @Override
    public void delete(int id) {
        String deleteQuery = "delete from Students where id=?";
        jdbcTemplate.update(deleteQuery, id);
    }

    @Override
    public void update(int id, Student student) {
        String updateQuery = "update Students set firstname = ?, lastname = ?,email = ?, age = ? where id = ?";
        jdbcTemplate.update(updateQuery, student.getFirstname(), student.getLastname(), student.getEmail(), student.getAge(), id);
    }

    @Override
    public Student findById(int id) {
        String findByIdQuery = "select * from students where id=?";
        return (Student) jdbcTemplate.query(findByIdQuery, new Object[]{id}, new BeanPropertyRowMapper<>(Student.class))
                .stream().findAny().orElse(null);

    }
}
