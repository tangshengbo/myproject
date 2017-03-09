package com.tangshengbo.io;

import java.io.*;

public class FileTest {

    public static void main(String[] args) throws Exception {
        FileTest fileTest = new FileTest();
//		fileTest.operationFile();
		fileTest.dataFIleStream();
//		fileTest.read();
//		fileTest.buffered();
        //fileTest.serializeObject();

//        fileTest.serializeObject();

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
        FileInputStream fileInputStream = new FileInputStream("E:\\read.txt");
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        System.out.println(dataInputStream.read());
        fileInputStream.close();
        dataInputStream.close();

    }

    public void buffered() throws IOException {
        BufferedInputStream ios = new BufferedInputStream(new FileInputStream("E:\\入职\\junit部署.avi"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\junit部署.avi"));
        byte [] bytes = new byte[1024];
        int len;
        while ((len = ios.read()) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.flush();
        bos.close();
        ios.close();
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
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\read.txt"), "UTF-8"));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\writer.txt"), "UTF-8"));
        PrintWriter pw = new PrintWriter(bw);
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
//            pw.write(line);
//            pw.println(line);

			bw.newLine();
            bw.flush();
//            pw.flush();
        }
    }

    private void serializeObject() throws FileNotFoundException, IOException, ClassNotFoundException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\serialize.txt"));
        Student student = new Student("00000001", "唐声波", 42);
        out.writeObject(student);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\serialize.txt"));
        student = (Student) in.readObject();
        System.out.println(student.toString());

    }

}
