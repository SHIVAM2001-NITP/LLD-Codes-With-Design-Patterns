// Counts files and directories
public class CountVisitor implements FileSystemVisitor {
    private int files = 0;
    private int directories = 0;

    @Override
    public void visit(File file) { files++; }

    @Override
    public void visit(Directory directory) { directories++; }

    public int getFiles() { return files; }
    public int getDirectories() { return directories; }
}
