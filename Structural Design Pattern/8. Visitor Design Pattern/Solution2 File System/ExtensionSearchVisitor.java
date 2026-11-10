import java.util.ArrayList;
import java.util.List;

// Collects the names of all files that end with a given extension
public class ExtensionSearchVisitor implements FileSystemVisitor {
    private String extension;
    private List<String> matches = new ArrayList<>();

    public ExtensionSearchVisitor(String extension) {
        this.extension = extension;
    }

    @Override
    public void visit(File file) {
        if (file.getName().endsWith(extension)) {
            matches.add(file.getName());
        }
    }

    @Override
    public void visit(Directory directory) { }

    public List<String> getMatches() { return matches; }
}
