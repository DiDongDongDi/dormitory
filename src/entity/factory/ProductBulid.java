package entity.factory;

import entity.building.*;

public class ProductBulid implements Bulid {
    public Block produce()
    {
        Block block=new Block();
        block.create_building();
        return block;
    }
}
