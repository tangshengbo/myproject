package com.tangshengbo.io;

import jodd.io.NetUtil;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;

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
//        int count = fileTest.countMatchesOfFile("C:/Users/Tangshengbo/Desktop/IOUtils.java", "IO");
//        System.out.println(count);
//        int eachSize = 100 * 1024; // 100k
//        File srcFile = new File("C:/Users/Tangshengbo/Desktop/IOUtils.java");
//        splitFile(srcFile, eachSize);
//        murgeFile("C:/Users/Tangshengbo/Desktop", "IOUtils.java");

        NetUtil.downloadFile("http://down.360safe.com/cpuleak_scan.exe", new File("E:/xx.exe"));

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

    /**
     * 拆分的思路，先把源文件的所有内容读取到内存中，然后从内存中挨个分到子文件里
     *
     * @param srcFile  要拆分的源文件
     * @param eachSize 按照这个大小，拆分
     */
    private static void splitFile(File srcFile, int eachSize) {
        if (0 == srcFile.length())
            throw new RuntimeException("文件长度为0，不可拆分");
        byte[] fileContent = new byte[(int) srcFile.length()];
        // 先把文件读取到数组中
        try {
            FileInputStream fis = new FileInputStream(srcFile);
            fis.read(fileContent);
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 计算需要被划分成多少份子文件
        int fileNumber;
        // 文件是否能被整除得到的子文件个数是不一样的
        // (假设文件长度是25，每份的大小是5，那么就应该是5个)
        // (假设文件长度是26，每份的大小是5，那么就应该是6个)
        if (0 == fileContent.length % eachSize)
            fileNumber = (int) (fileContent.length / eachSize);
        else
            fileNumber = (int) (fileContent.length / eachSize) + 1;
        for (int i = 0; i < fileNumber; i++) {
            String eachFileName = srcFile.getName() + "-" + i;
            File eachFile = new File(srcFile.getParent(), eachFileName);
            byte[] eachContent;
            // 从源文件的内容里，复制部分数据到子文件
            // 除开最后一个文件，其他文件大小都是100k
            // 最后一个文件的大小是剩余的
            if (i != fileNumber - 1) // 不是最后一个
                eachContent = Arrays.copyOfRange(fileContent, eachSize * i, eachSize * (i + 1));
            else // 最后一个
                eachContent = Arrays.copyOfRange(fileContent, eachSize * i, fileContent.length);
            try {
                // 写出去
                FileOutputStream fos = new FileOutputStream(eachFile);
                fos.write(eachContent);
                // 记得关闭
                fos.close();
                System.out.printf("输出子文件%s，其大小是 %d字节%n", eachFile.getAbsoluteFile(), eachFile.length());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 合并的思路，就是从eclipse.exe-0开始，读取到一个文件，就开始写出到 eclipse.exe中，直到没有文件可以读
     *
     * @param folder   需要合并的文件所处于的目录
     * @param fileName 需要合并的文件的名称
     * @throws FileNotFoundException
     */
    private static void murgeFile(String folder, String fileName) {
        try {
            // 合并的目标文件
            File destFile = new File(folder, fileName);
            FileOutputStream fos = new FileOutputStream(destFile);
            int index = 0;
            while (true) {
                //子文件
                File eachFile = new File(folder, fileName + "-" + index++);
                //如果子文件不存在了就结束
                if (!eachFile.exists())
                    break;
                //读取子文件的内容
                FileInputStream fis = new FileInputStream(eachFile);
                byte[] eachContent = new byte[(int) eachFile.length()];
                fis.read(eachContent);
                fis.close();
                //把子文件的内容写出去
                fos.write(eachContent);
                fos.flush();
                System.out.printf("把子文件 %s写出到目标文件中%n", eachFile);
            }
            fos.close();
            System.out.printf("最后目标文件的大小：%,d字节", destFile.length());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 将输入流复制到输出流
     *
     * @param inputStream
     * @param outputStream
     */
    public static void copyStream(InputStream inputStream, OutputStream outputStream) {
        try (final InputStream is = inputStream;
             final OutputStream os = outputStream) {
            int length;
            byte[] buffer = new byte[4 * 1024];
            while ((length = is.read(buffer, 0, buffer.length)) != -1) {
                System.out.printf("缓冲数据组:%s,d字节", length);
                System.out.println();
                os.write(buffer, 0, length);
            }
            System.out.println(length);
            os.flush();
        } catch (Exception e) {
            System.out.println("copy stream failure" + e);
            throw new RuntimeException(e);
        }
    }
}
