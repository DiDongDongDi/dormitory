package entity.factory;

import entity.Person.Person;
import entity.Person.Superior;

public class productSuper implements Product {
    public Person produce()
    {
        Superior sp=new Superior();
        sp.create_super();
        return sp;
    }
}
