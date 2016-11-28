package com.tangshengbo.controller;

import com.tangshengbo.commons.AccountThread;
import com.tangshengbo.model.*;
import com.tangshengbo.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/courses")
public class AccountController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private AccountService accountService;


    // 本方法将处理 /courses/view?courseId=123 形式的URL
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String getAccount(@RequestParam("tang") String tang, Model model) {

        System.out.println(".............RequestParam" + tang);
        System.out.println(accountService.getAccount(4));
        List<Account> list = accountService.getAccountAll();
        System.out.println(list.size());
        model.addAttribute("accountList", list);
        return "index";

    }

    // 本方法将处理 /courses/view2/123 形式的URL restful
    @RequestMapping(value = "/view2/{tang}", method = RequestMethod.GET)
    public ModelAndView getAccount2(@PathVariable("tang") String tang, Model model) {

        System.out.println(".............@PathVariable" + tang);
        System.out.println(accountService.getAccount(4));
        List<Account> list = accountService.getAccountAll();
        System.out.println(list.size());
        model.addAttribute("accountList", list);
        return new ModelAndView("forward:/index.jsp");

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAccount(Account account, Model model) {

        log.info("apache"+account.toString());
        ExecutorService executorService = Executors.newFixedThreadPool(500);
        executorService.execute(new AccountThread(accountService));
      //  System.out.println(file.getOriginalFilename());
        try {
            Thread.sleep(10000);

        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //accountService.addAccount(account);
        System.out.println("添加用户信息...................OK");
//		List<Account> list = accountService.getAccountAll();
//		System.out.println(list.size());

      /*  try {
            FileCopyUtils.copy(file.getInputStream(),
                    new FileOutputStream(new File("D:\\", System.currentTimeMillis() + file.getOriginalFilename())));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        List<Account> list = accountService.getAccountAll();
        System.out.println(list.size());
        model.addAttribute("accountList", list);
        return "index";
    }

    @RequestMapping(value = "/tojson/{all}", method = RequestMethod.GET)
    public ResponseEntity accountToJson(@PathVariable("all") String all) {
        System.out.println("toJson.................." + all);
        List<Account> list = accountService.getAccountAll();
        return new ResponseEntity(list, HttpStatus.OK);

    }

    // public void init() {
    // System.out.println("初始化.....................");
    // ApplicationContext applicationContext = WebApplicationContextUtils
    // .getRequiredWebApplicationContext(this.getServletContext());
    // accountService = (AccountService)
    // applicationContext.getBean("accountService");
    //
    // }
    // http://localhost:8080/webdome/courses/toarray?name=t&name=s&name=b
    @RequestMapping(value = "/toarray", method = RequestMethod.GET)
    public ResponseEntity accountArray(String[] name) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String string : name) {
            stringBuffer.append(string);
        }

        return new ResponseEntity(stringBuffer, HttpStatus.OK);
    }

    // http://localhost:8080/webdome/courses/toobj?name=tang&age=12&contact.phone=12345555
    @RequestMapping(value = "/toobj", method = RequestMethod.GET)
    public ResponseEntity<User> accountObject(User user) {

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // http://localhost:8080/webdome/courses/toobjs?user.name=tang&admin.name=sheng&age=12
    @RequestMapping(value = "/toobjs", method = RequestMethod.GET)
    public ResponseEntity accountObjects(User user, Admin admin) {
        List list = new ArrayList();
        list.add(user);
        list.add(admin);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @InitBinder("user")
    public void initUser(WebDataBinder webDataBinder) {
        System.out.println("init user");
        webDataBinder.setFieldDefaultPrefix("user.");
    }

    @InitBinder("admin")
    public void initAdmin(WebDataBinder webDataBinder) {
        System.out.println("init admin");

        webDataBinder.setFieldDefaultPrefix("admin.");
    }

    // http://localhost:8080/webdome/courses/tolist?users[0].name=tang&users[33].name=sheng
    @RequestMapping(value = "/tolist", method = RequestMethod.GET)
    public ResponseEntity accountList(UserListForm userForm) {
        return new ResponseEntity(userForm, HttpStatus.OK);
    }

    // http://localhost:8080/webdome/courses/toset?users[0].name=tang&users[1].name=sheng
    @RequestMapping(value = "/toset", method = RequestMethod.GET)
    public ResponseEntity accountSet(UserSetForm userForm) {
        return new ResponseEntity(userForm, HttpStatus.OK);
    }

    // http://localhost:8080/webdome/courses/tomap?users[%22A%22].name=tang&users[%27B%27].name=sheng&users[%27C%27].name=bo
    @RequestMapping(value = "/tomap", method = RequestMethod.GET)
    public ResponseEntity accountMap(UserMapForm userForm) {
        return new ResponseEntity(userForm, HttpStatus.OK);
    }

    // { "name": "Jim", "age": 16, "contact": { "address": "hunan", "phone":
    // "110" } } application/json

    @RequestMapping(value = "/tojson", method = RequestMethod.POST)
    public ResponseEntity accountJson(@RequestBody User user) {
        System.out.println(user.toString());
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // <?xml version="1.0" encoding="UTF-8" ?> <admin> <name>Jim</name>
    // <age>16</age> </admin> application/xml
    //
    @RequestMapping(value = "/toxml", method = RequestMethod.POST)
    public ResponseEntity accountXml(@RequestBody Admin admin) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(admin.toString());
        return new ResponseEntity(admin, HttpStatus.OK);
    }

    @RequestMapping(value = "/todate", method = RequestMethod.GET)
    @ResponseBody
    public String accountDate(Date dateFirst, Date dateEnd) {
        //return dateFirst.toLocaleString() + dateEnd.toLocaleString();
        return null;
    }

	/*
     * @InitBinder("date") public void initDate1(WebDataBinder binder) {
	 * System.out.println("init date"); binder.registerCustomEditor(Date.class,
	 * new CustomDateEditor(new SimpleDateFormat("yyyy.MM.dd"), true)); }
	 */

}
