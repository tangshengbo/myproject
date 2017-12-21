package com.tangshengbo.xml;

import com.tangshengbo.xml.sign.RSAEncryptUtil;
import com.tangshengbo.xml.sign.SignUtil;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/12/21.
 */
public class XMLToObjectTest {

    public static void main(String[] args) throws Exception {
        xmlByJaxb();
//        xmlByStream();
    }

    private static void xmlByStream() {
        String xml = XmlUtil.toXMl(getSimpleDepartment("doSign"));
        Department department = XmlUtil.fromXML(xml, Department.class);

        System.out.println(department);
    }

    private static void xmlByJaxb() throws Exception {
        JAXBUtil<Department> departmentJAXBUtil = new JAXBUtil<>();
        String xml = departmentJAXBUtil.marshal(getSimpleDepartment(null));
        System.out.println(xml);
        System.out.println("===================================================");
        String sign = doSign(departmentJAXBUtil, xml);
        System.out.println("===================================================");
        verify(xml, sign);
    }

    private static String doSign(JAXBUtil<Department> departmentJAXBUtil, String xml) throws Exception {
        String sign;
        String encryptXml;
        String privateKey = RSAEncryptUtil.loadPrivateKeyByFile("E:/");
        sign = SignUtil.sign(xml, privateKey, "UTF-8");
        encryptXml = departmentJAXBUtil.marshal(getSimpleDepartment(sign));
        new FileOutputStream("E:/xx.xml").write(encryptXml.getBytes());
        System.out.println(encryptXml);
        return sign;
    }

    private static void verify(String xml, String sign) throws Exception {
        String publicKey = RSAEncryptUtil.loadPublicKeyByFile("E:/");
        System.out.println(SignUtil.verify(xml, sign, publicKey, "UTF-8"));
    }

    /**
     * 生成一个简单的Department对象
     *
     * @return
     */
    public static Department getSimpleDepartment(String sign) {
        List<Staff> staffs = new ArrayList<>();
        Staff stf = new Staff();
        stf.setName("周杰伦");
        stf.setAge(30);
        stf.setSmoker(false);
        staffs.add(stf);
        Staff stf2 = new Staff();
        stf2.setName("周笔畅");
        stf2.setAge(28);
        stf2.setSmoker(false);
        staffs.add(stf2);
        Staff stf3 = new Staff();
        stf3.setName("周星驰");
        stf3.setAge(40);
        stf3.setSmoker(true);
        staffs.add(stf3);
        Department dept = new Department();
        dept.setName("娱乐圈");
        dept.setSign(sign);
        dept.setStaffs(staffs);
        return dept;
    }
}
