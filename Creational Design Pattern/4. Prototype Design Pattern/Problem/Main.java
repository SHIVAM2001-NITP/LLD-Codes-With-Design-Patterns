public class Main {
    public  static void main(String[] args){
        Student obj=new Student(20,76,"Ram");

        //Creating clone of Obj
        Student cloneObj=new Student();
        cloneObj.name=obj.name;
        cloneObj.age=obj.age;
        cloneObj.rollNumber=obj.rollNumber;//gives error because trying to access private member
    }
}
