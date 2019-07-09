package entity.factory;

import entity.Person.Person;

public class factory {
        public Person addPerson(int i)//1代表创建学生 2代表创建管理员
        {
            switch (i){
                case 1:
                    ProductStu ps=new ProductStu();
                    return ps.produce();
                case 2:
                default:
                    System.out.println("输入错误");
                    return null;
            }
        }
}
