// Adds up the size of every file in the tree
public class SizeCalculatorVisitor implements FileSystemVisitor {
    private long totalSizeInKb = 0;

    @Override
    public void visit(File file) {
        totalSizeInKb += file.getSizeInKb();
    }

    @Override
    public void visit(Directory directory) {
        // a directory has no size of its own, its files are visited next
    }

    public long getTotalSizeInKb() { return totalSizeInKb; }
}
