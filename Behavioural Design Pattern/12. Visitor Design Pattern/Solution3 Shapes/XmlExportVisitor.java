// A brand new operation: no shape class had to change to support XML export
public class XmlExportVisitor implements ShapeVisitor {
    private StringBuilder xml = new StringBuilder("<shapes>\n");

    @Override
    public void visit(Circle circle) {
        xml.append("  <circle radius=\"").append(circle.getRadius()).append("\"/>\n");
    }

    @Override
    public void visit(Rectangle rectangle) {
        xml.append("  <rectangle width=\"").append(rectangle.getWidth())
           .append("\" height=\"").append(rectangle.getHeight()).append("\"/>\n");
    }

    @Override
    public void visit(Triangle triangle) {
        xml.append("  <triangle a=\"").append(triangle.getA()).append("\" b=\"").append(triangle.getB())
           .append("\" c=\"").append(triangle.getC()).append("\"/>\n");
    }

    public String getXml() { return xml + "</shapes>"; }
}
