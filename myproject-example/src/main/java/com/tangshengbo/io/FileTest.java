package com.tangshengbo.io;

import jodd.io.FileNameUtil;
import jodd.util.StringPool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileTest {

    private static final Logger logger = LoggerFactory.getLogger(FileTest.class);

    @Test
    public void testFileName() {
        String fileName = "/account/20170605/0002900F0306973_20170605.txt";
        logger.info("getBaseName:{}", FileNameUtil.getBaseName(fileName));
        logger.info("getExtension:{}", FileNameUtil.getExtension(fileName));
        logger.info("getFullPath:{}", FileNameUtil.getFullPath(fileName));
        logger.info("getFullPathNoEndSeparator:{}", FileNameUtil.getFullPathNoEndSeparator(fileName));
        logger.info("getName:{}", FileNameUtil.getName(fileName));
        logger.info("getPath:{}", FileNameUtil.getPath(fileName));
        logger.info("getPathNoEndSeparator:{}", FileNameUtil.getPathNoEndSeparator(fileName));
        logger.info("getPrefix:{}", FileNameUtil.getPrefix(fileName));
        logger.info("getPrefixLength:{}", FileNameUtil.getPrefixLength(fileName));
    }

    @Test
    public void testReadFileToString() throws Exception {
        String result = FileUtils.readFileToString(new File("E:/string.txt"), StringPool.UTF_8);
        logger.info("{}", result);
//        InputStream in = IOUtils.toInputStream(result, StringPool.UTF_8);
        FileWriterWithEncoding fw = new FileWriterWithEncoding("D:/string.txt", "GBK");
        IOUtils.write(result, fw);
        fw.flush();
        IOUtils.closeQuietly(fw);
    }

    @Test
    public void testReadByte() {
        logger.info("{}", (byte) -128 & 0xFF);
        logger.info("{}", 127 & 0xFF);
    }

    public static void main(String[] args) throws Exception {
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

//        NetUtil.downloadFile("http://down.360safe.com/cpuleak_scan.exe", new File("E:/xx.exe"));
//        operationFile();
//        float i = 100.22f;

//        for(int i=1;i<=5;i++){
//            for(int j=5; i<=j; j--)
//                System.out.print(" ");
//            for(int j=1; j<=i; j++)
//                System.out.print("*");
//
//            System.out.println();
//        }
        Map<String, Class> beanClassMap = new LinkedHashMap<>();
        Map<String, Object> beanValueMap = new LinkedHashMap<>();

        beanClassMap.put("student", Student.class);
        beanClassMap.put("teacher", Teacher.class);
        beanClassMap.put("school", School.class);
        beanClassMap.put("tly", Tyl.class);
        beanClassMap.forEach((beanName, beanClass) -> {
            try {
                //创建实例
                Object o = beanClass.newInstance();
                //注入属性
                Field[] declaredFields = beanClass.getDeclaredFields();
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(Autowired.class)) {
                        System.out.println(beanClass + "\t" + field.getName() + ":开始注入");
                        if (!beanValueMap.containsKey(field.getName())) {
                            Class autoWiredCls = beanClassMap.get(field.getName());
                            beanValueMap.put(field.getName(), autoWiredCls.newInstance());
                        }
                        field.setAccessible(true);
                        field.set(o, beanValueMap.get(field.getName()));
                        System.out.println(beanClass + "\t" + field.getName() + ":注入完成");
                    }
                }
                //实例化完成 加入到bean实例工厂
                beanValueMap.put(beanName, o);
            } catch (Exception e) {
                //
            }
        });
        beanValueMap.forEach((beanName, beanValue) -> {
            if (beanValue instanceof InitializingBean) {
                try {
                    ((InitializingBean) beanValue).afterPropertiesSet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(beanName + "\t" + beanValue);
        });


//        Constructor[] declaredConstructors = stuCls.getDeclaredConstructors();
//        for (Constructor constructor : declaredConstructors) {
//            constructor.setAccessible(true);
//            if (constructor.getParameters() == null) {
//
//                Object o = constructor.newInstance();
//                System.out.println(o);
//            }
//        }
//        Field[] declaredFields = stuCls.getDeclaredFields();
//        for (Field field : declaredFields) {
//           if (field.isAnnotationPresent(Autowired.class)) {
//                System.out.println(field.getName() +  "注入");
//                field.setAccessible(true);
//                field.set(student, "唐雨伦");
//            }
//            System.out.println(field.getName());
//        }
//        System.out.println(student);


//
//        Student s = new Student();
//        System.out.println(s);


//        int row = 7;
//        for(int i = 0;i <= row;i++){
//
//            //實現每行縮進
//            for(int k = 0;k < (row - i);k++){
//                System.out.print("     ");
//            }
//
//            //左半部份
//            for(int j = 0;j <=i;j++){
//                //一位數的話4個空格
//                if(((int)Math.pow(2, j)) < 10){
//                    System.out.print("   ");
//                }
//                //三位數2個空格
//                else if(((int)Math.pow(2, j)) > 99){
//                    System.out.print(" ");
//                }
//                //兩位數3個空格
//                else{
//                    System.out.print("  ");
//                }
//                System.out.print(" " + (int)Math.pow(2,j));
//            }
//
//
//            //左右縮進
//            System.out.print("   ");
//
//            //右半部份
//            for(int l = i - 1 ;l >= 0;l--){
//                System.out.print(" " + (int)Math.pow(2, l));
//                //一位數的話4個空格
//                if(((int)Math.pow(2, l)) < 10){
//                    System.out.print("   ");
//                }
//                //三位數2個空格
//                else if(((int)Math.pow(2, l)) > 99){
//                    System.out.print(" ");
//                }
//                //兩位數3個空格
//                else{
//                    System.out.print("  ");
//                }
//            }
//            System.out.println();
//
//        }

    }

    public static void operationFile() throws IOException {
        File file = new File("D:\\tang.txt");
        System.out.println(file.toURI().toURL());
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
        URL url = new URL("file:/E:/Users/TangShengBo/intellJidea/myproject/myproject-example/target/classes/com/tangshengbo/loadclass/asm/AccountImpl.class");


    }

    @Test
    public void testLargeFileSearch() throws Exception {
        String file = IOUtils.toString(new FileInputStream("D:/大文件.json"),
                "UTF-8");
        logger.info("{}", file.contains("find"));
    }

    @Test
    public void buffered() throws IOException {
        BufferedInputStream ios = new BufferedInputStream(new FileInputStream("E:/SW_DVD5_Visio_Premium_2010_W32_ChnSimp_Std_Pro_Prem_MLF_X16-51022.iso"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:/spring-framework-0.9.iso"));
        byte[] buffer = new byte[1024 * 16];
        int read;
        int count = 0;
        while (true) {
            read = ios.read(buffer, 0, buffer.length);
            if (read == -1) {
                break;
            }
            bos.write(buffer, 0, read);
            count += read;
        }
        bos.flush();
        bos.close();
        ios.close();
        logger.info("传输完成！总字节数:{}", count);
    }

    @Test
    public void bufferRead() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E:/read.txt"), "GBK"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("E:/write.txt"))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                logger.info("{}", line);
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public void copyStream(InputStream inputStream, OutputStream outputStream) {
        try (final InputStream is = inputStream;
             final OutputStream os = outputStream) {
            int length;
            byte[] buffer = new byte[32 * 1024];
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

    @Test
    public void copyFileByStream() {
        try {
            copyStream(new FileInputStream("D:\\360极速浏览器下载\\Visio2010\\SW_DVD5_Visio_Premium_2010_W32_ChnSimp_Std_Pro_Prem_MLF_X16-51022.iso"), new FileOutputStream("E:/SW_DVD5_Visio_Premium_2010_W32_ChnSimp_Std_Pro_Prem_MLF_X16-51022.iso"));
        } catch (FileNotFoundException e) {
            logger.error("copy file 异常:{}", e);
        }
    }
}
