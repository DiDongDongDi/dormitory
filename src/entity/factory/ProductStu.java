package entity.factory;

import entity.Person.Person;
import entity.Person.Student;

public class ProductStu implements Product {
    public Person produce()
    {
        Student stu=new Student();
        stu.create_student();
        return stu;
    }
}
