package entity.Person;

public abstract class Person {
    private String name;//学号从0开始
    private boolean sex;//性别true为男false为女

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSex()
    {
        if(sex==true)
            return "男";
        else
            return "女";
    }

    public String getName() {
        return name;
    }
    //public abstract void st;
}
