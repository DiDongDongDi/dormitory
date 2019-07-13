package entity.factory;

import entity.Person.Person;
import entity.building.Block;

public class factory {
        public Person addPerson(int i)//1代表创建学生 2代表创建管理员
        {
            switch (i){
                case 1:
                    ProductStu ps=new ProductStu();
                    return ps.produce();
                case 2:
                    productSuper pro=new productSuper();
                    return pro.produce();
                default:
                    System.out.println("您的输入有误!");
                    return null;
            }
        }
        public Block addBlock()
        {
            ProductBulid pb=new ProductBulid();
            return pb.produce();
        }
}
