public class Main {
    public static  void main (String []args){
        int x=0;
        int y=0;
        for(int i=0;i<500000;i++){
            Sprites humanoidSprites=new Sprites();
            Robot humanoidRoboticObject=new Robot(x+i,y+i,"HUMANOID",humanoidSprites);

        }
        for(int i=0;i<500000;i++){
            Sprites roboticDogSprites=new Sprites();
            Robot RoboticDogObject=new Robot(x+i,y+i,"ROBOTIC_DOGS",roboticDogSprites);

        }
    }
}

//Total 10 lakhs objects are created and each  takes 31KB
//Total Memory=31GB but if user have only 8GB or 16GB memory than crashed will happen
//Creation of Object is Expensive

//Solution:
//From Object remove all the Extrinsic data and keep Intrinsic data (This object calledFlyWeight Object)
//This Flyweight class can be Immutable
//Extrinsic Data can be passed to the flyweight class in method parameter
//once the flyweight Object is created, It is Cached and reused whenever required

