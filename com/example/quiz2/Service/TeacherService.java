package com.example.quiz2.Service;

import com.example.quiz2.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public boolean removeTeacher(int id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateTeacher(int id, Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    public Teacher getTeacher(int id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                return teachers.get(i);
            }
        }
        return null;
    }

    public ArrayList<Teacher> getTeacherBySalary(int salary) {
        ArrayList<Teacher> sal = new ArrayList<>();

        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getSalary() >= salary) {
                sal.add(teachers.get(i));
            }
        }
        if (sal.isEmpty()) {
            return null;
        }
        return sal;
    }
}
