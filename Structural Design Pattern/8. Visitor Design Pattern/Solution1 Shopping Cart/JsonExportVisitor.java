// Concrete Visitor 3: added LATER without touching Book, Fruit or Electronics (Open/Closed)
public class JsonExportVisitor implements ItemVisitor {
    private StringBuilder json = new StringBuilder();

    @Override
    public void visit(Book book) {
        append("{\"type\":\"book\",\"name\":\"" + book.getName() + "\",\"price\":" + book.getPrice() + "}");
    }

    @Override
    public void visit(Fruit fruit) {
        append("{\"type\":\"fruit\",\"name\":\"" + fruit.getName() + "\",\"weightInKg\":" + fruit.getWeightInKg() + "}");
    }

    @Override
    public void visit(Electronics electronics) {
        append("{\"type\":\"electronics\",\"name\":\"" + electronics.getName() + "\",\"warrantyInYears\":" + electronics.getWarrantyInYears() + "}");
    }

    private void append(String element) {
        if (json.length() > 0) {
            json.append(",");
        }
        json.append(element);
    }

    public String getJson() { return "[" + json + "]"; }
}
