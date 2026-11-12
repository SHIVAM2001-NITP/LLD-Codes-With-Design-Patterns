public class Main {
    public static void main(String[] args) {

        Directory root = new Directory("project");
        Directory src = new Directory("src");
        src.add(new File("Main.java", 10));
        src.add(new File("Util.java", 6));
        root.add(src);
        root.add(new File("README.md", 4));
        root.add(new File("logo.png", 120));

        SizeCalculatorVisitor size = new SizeCalculatorVisitor();
        CountVisitor count = new CountVisitor();
        ExtensionSearchVisitor javaFiles = new ExtensionSearchVisitor(".java");

        root.accept(size);
        root.accept(count);
        root.accept(javaFiles);

        System.out.println("Total size  : " + size.getTotalSizeInKb() + " KB");
        System.out.println("Files       : " + count.getFiles() + ", directories: " + count.getDirectories());
        System.out.println("Java files  : " + javaFiles.getMatches());
    }
}
