import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Shape> shapes = Arrays.asList(new Circle(2), new Rectangle(3, 4), new Triangle(3, 4, 5));

        AreaVisitor area = new AreaVisitor();
        PerimeterVisitor perimeter = new PerimeterVisitor();
        XmlExportVisitor xml = new XmlExportVisitor();

        for (Shape shape : shapes) {
            shape.accept(area);
            shape.accept(perimeter);
            shape.accept(xml);
        }

        System.out.printf("Total area      : %.2f%n", area.getTotalArea());
        System.out.printf("Total perimeter : %.2f%n", perimeter.getTotalPerimeter());
        System.out.println(xml.getXml());
    }
}
