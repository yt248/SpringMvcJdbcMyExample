package ru.eugene.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eugene.example.dao.impl.StudentDaoIml;
import ru.eugene.example.model.Student;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentsController {
    private final StudentDaoIml studentDaoIml;

    @Autowired
    public StudentsController(StudentDaoIml studentDaoIml) {
        this.studentDaoIml = studentDaoIml;
    }

    //--------------------Чтение всех студентов--------------------------------------------------
    @GetMapping
    public String findAll(Model model) {
        List<Student> studentList = studentDaoIml.findAll();
        model.addAttribute("studentList", studentList);
        return "student/view";
    }

    //----------------------- Создание студента-------------------------
    @GetMapping("/new")
    public String newStudent(@ModelAttribute("student") Student student) {
        return "student/new";
    }

    @PostMapping
    public String create(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "student/new";

        studentDaoIml.insert(student);
        return "redirect:/student";
    }

    //--------------------Чтение студена по Id--------------------------------------------------
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentDaoIml.findById(id));
        return "student/viewById";
    }

    //--------------------Удаление студенат--------------------------------------------------
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        studentDaoIml.delete(id);
        return "redirect:/student";
    }

    //----------------------- Обновление студента-------------------------
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentDaoIml.findById(id));
        return "student/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "student/edit";

        studentDaoIml.update(id, student);
        return "redirect:/student";
    }
    //-------------------------------------------------------------------

}
