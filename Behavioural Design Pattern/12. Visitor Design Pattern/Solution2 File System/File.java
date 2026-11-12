public class File implements FileSystemElement {
    private String name;
    private long sizeInKb;

    public File(String name, long sizeInKb) {
        this.name = name;
        this.sizeInKb = sizeInKb;
    }

    public String getName() { return name; }
    public long getSizeInKb() { return sizeInKb; }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}
