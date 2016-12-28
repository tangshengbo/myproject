package com.tangshengbo.io;

import java.io.*;
import java.lang.reflect.Proxy;

public class FileTest {

    public static void main(String[] args) throws Exception {
        FileTest fileTest = new FileTest();
//		fileTest.operationFile();
//		fileTest.dataFIleStream();
//		fileTest.read();
//		fileTest.bufferRead();
        //fileTest.serializeObject();

        System.out.println(0 - 0);

    }

    public void operationFile() throws IOException {
        File file = new File("D:\\tang.txt");
        if (file.exists()) {
            file.delete();
        } else {
            file.createNewFile();
        }
        System.out.println(file.getAbsolutePath() + "\t" + file.getParent());

    }

    public void stream() throws FileNotFoundException {
        InputStream is = new FileInputStream("C:\\Users\\TangShengBo\\Desktop\\55eba12b0001f25b00000000\\New Text Document.txt");

    }

    public void fileStream() throws FileNotFoundException {
        FileInputStream fInputStream = new FileInputStream("c:\\tang.txt");

    }

    public void dataFIleStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:/tang.txt");
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        System.out.println(dataInputStream.readInt());
        fileInputStream.close();
        dataInputStream.close();

    }

    public void buffered() throws FileNotFoundException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(""));
    }

    public void read() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\read.txt");
        InputStreamReader reader = new InputStreamReader(fileInputStream, "GBK");
        reader.read();
        int i;
        while ((i = reader.read()) != -1) {
            System.out.print((char) i);
        }
    }

    public void bufferRead() throws IOException {
        //BufferReader br = new BufferReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\read.txt"), "GBK"));


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\writer.txt", true)));
        PrintWriter pw = new PrintWriter(bw);
        String line;
        while ((line = br.readLine()) != null) {
            //	bw.write(line);
            pw.write(line);
            pw.println(line);

//			bw.newLine();
            pw.flush();
        }
    }

    private void serializeObject() throws FileNotFoundException, IOException, ClassNotFoundException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("folder\\serialize.txt"));
        Student student = new Student("00000001", "唐声波", 42);
        out.writeObject(student);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("folder\\serialize.txt"));
        student = (Student) in.readObject();
        System.out.println(student.toString());
        Proxy proxy;


    }

}
