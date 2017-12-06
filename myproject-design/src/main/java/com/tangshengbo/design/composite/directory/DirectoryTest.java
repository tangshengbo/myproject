package com.tangshengbo.design.composite.directory;

/**
 * Created by TangShengBo on 2017-10-15.
 */
public class DirectoryTest {

    public static void main(String[] args) {
        System.out.println("Making root entries .......");
        Entry rootDir = new Directory("root");
        Entry binDir = new Directory("bin");
        Entry tmpDir = new Directory("tmp");
        Entry usrDir = new Directory("usr");

        rootDir.add(binDir);
        rootDir.add(tmpDir);
        rootDir.add(usrDir);

        File vi = new File("vi", 1000);
        File composite = new File("Composite.java", 1000);
        binDir.add(vi);
        binDir.add(composite);
        binDir.add(new File("tng", 11000));
        rootDir.printList();

        System.out.println("vi = " + vi.getFullName());
        System.out.println("composite = " + composite.getFullName());
    }
}
