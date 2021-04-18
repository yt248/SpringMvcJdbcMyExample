package ru.eugene.example.model;


import javax.validation.constraints.*;

public class Student {
    private int id;

    @NotEmpty(message = "Поле ввода не должно быть пустым!")
    @Size(min = 2, max = 30, message = "Имя должно содержать от 2 до 30 символов")
    private String firstname;

    @NotEmpty(message = "Поле ввода не должно быть пустым!")
    @Size(min = 2, max = 30, message = "Фамилия должно содержать от 2 до 30 символов")
    private String lastname;

    @Email
    @NotEmpty(message = "Поле ввода не должно быть пустым!")
    private String email;

    @Min(value = 2, message = "Минимальный возраст 2 года")
    @Max(value = 150, message = "Максимальный возраст 150 лет")
    private int age;


    public Student() {
    }

    public Student(String firstname, String lastname, String email, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", surname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
