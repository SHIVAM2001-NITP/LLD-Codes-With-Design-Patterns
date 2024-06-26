public class Main {
    public static void main(String[] args){
        ShapeFactory shapeFactoryObj=new ShapeFactory();
        Shape shapeObj1=shapeFactoryObj.getShape("CIRCLE");
        shapeObj1.draw();

        Shape shapeObj2=shapeFactoryObj.getShape("RECTANGLE");
        shapeObj2.draw();
    }
}
