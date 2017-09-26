package com.tangshengbo.io;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.math.BigDecimal;

public class FileTest {


    public static void main(String[] args) throws Exception {
        FileTest fileTest = new FileTest();
//		fileTest.operationFile();
//		fileTest.dataFIleStream();
//		fileTest.read();
//		fileTest.buffered();
        //fileTest.serializeObject();
//        fileTest.printToDisplay();
//        fileTest.serializeObject();
//        fileTest.operationFile();
        int count = fileTest.countMatchesOfFile("C:/Users/Tangshengbo/Desktop/IOUtils.java", "IO");
        System.out.println(count);
    }

    public void operationFile() throws IOException {
        File file = new File("D:\\tang.txt");
        if (file.exists()) {
            file.delete();
        } else {
            file.createNewFile();
        }
        System.out.println(file.getAbsolutePath() + "\t" + file.getParent());
        FileWriter fw = new FileWriter("D:\\tang.txt");
        fw.write("hello world!" + System.getProperty("line.separator"));
        fw.write("你好！北京！");
        fw.close();

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
        byte[] bytes = new byte[1024];
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

    public void printToDisplay() {
        char ch;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  //将字节流转为字符流，带缓冲
        try {
            while ((ch = (char) in.read()) != -1) {
                System.out.print(ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printFilesByFolder(String folderName) {
        if (folderName == null) {
            return;
        }
        FileInputStream fis = null;
        String fileName;
        double fileSize = 0;
        BigDecimal bigDecimal;

        File baseFile = new File(folderName);
        File[] files = baseFile.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                printFilesByFolder(file.getPath());
            }

            if (file.isFile()) {
                fileName = file.getName();
                try {
                    fis = new FileInputStream(file);
                    fileSize = fis.available();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                fileSize = fileSize / 1048576;
                bigDecimal = new BigDecimal(fileSize);
                fileSize = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                System.out.println(fileName + "\t" + suffix + "\t" + fileSize + "MB");
            }
        }
    }

    private int countMatchesOfFile(String fileName, String words) {
        int count = 0;
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                count += StringUtils.countMatches(line, words);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

}
