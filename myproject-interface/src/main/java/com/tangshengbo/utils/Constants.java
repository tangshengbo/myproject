package com.tangshengbo.utils;

import java.util.ResourceBundle;

/**
 * Created by Administrator on 2016/11/24.
 */
public class Constants {
    private Constants() {
        // hide me
    }
    // ~ Static fields/initializers
    // =============================================

    /**
     * Assets Version constant
     */
    public static final String ASSETS_VERSION = "assetsVersion";
    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting
     * this key to the same one that Struts uses, we get synchronization in
     * Struts w/o having to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "ROLE_USER";

    /**
     * The name of the user's role list, a request-scoped attribute when
     * adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute when
     * adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     *
     * @deprecated No longer used to set themes.
     */
    public static final String CSS_THEME = "csstheme";

    /**
     * The name of System config
     *
     * @deprecated 系统配置文件
     * @author wanghui
     */
    public static final class SYSTEM {
        public static final String PROPERTIES_FILE = "systemconf";
    }

    public static final String PROD_CODE_MAPPING_FILE = "prodCode.xml";

    public static final String CCP_RES_ALL_PUT_FLAG_UPDATEIN = "70";
    public static final String CCP_RES_ALL_PUT_FLAG_WAITPUBLISH = "20";

    public static final String CCP_BAOSTEEL_WEBSERVICEPURL = "http://sim-com.tangshengbo.service.baosteel.info/services/ServiceSOWB005r";
    public static final String CCP_BATCHPUT_FIEL_PATH = "D:/tmp/";

    public static final String TELE_RETURNRESTRANSPRICE = "V5XZ02";
    public static final String TELE_CALLBACKRETURNRESTRANSPRICE = "XZV501";
    public static final String TELE_RELATIONBETWRESANDTRANSCENTER = "XZV502";
    public static final String TELE_SENDSTORERESINFO = "XZV503";

    public static final String CCP_PACTPAYMENTINFOSEND = "XZXSH1";

    private static ResourceBundle rb = ResourceBundle.getBundle(SYSTEM.PROPERTIES_FILE);

    public static final String CCP_FTP_HOST = rb.getString("ccpftp.formal.host");
    public static final String CCP_FTP_JYZXUSERNAME = rb.getString("ccpftp.formal.jyzxusername");
    public static final String CCP_FTP_JYZXPASSWORD = rb.getString("ccpftp.formal.jyzxpassword");
    public static final String CCP_FTP_JYZXINFILEPATH = rb.getString("ccp.formal.jyzxinfilepath");
    public static final String CCP_FTP_JYZXOUTFILEPATH = rb.getString("ccp.formal.jyzxoutfilepath");
    public static final int CCP_FTP_PORT = Integer.parseInt(rb.getString("ccpftp.formal.port"));

    public static final String CCP_FTP_YTHUSERNAME = rb.getString("ccpftp.formal.ythusername");
    public static final String CCP_FTP_YTHPASSWORD = rb.getString("ccpftp.formal.ythpassword");
    public static final String CCP_FTP_YTHINFILEPATH = rb.getString("ccp.formal.ythinfilepath");
    public static final String CCP_FTP_YTHOUTFILEPATH = rb.getString("ccp.formal.ythoutfilepath");

    public static final String CCP_FTP_BGZXUSERNAME = rb.getString("ccpftp.formal.bgzxusername");
    public static final String CCP_FTP_BGZXPASSWORD = rb.getString("ccpftp.formal.bgzxpassword");
    public static final String CCP_FTP_BGZXINFILEPATH = rb.getString("ccp.formal.bgzxinfilepath");
    public static final String CCP_FTP_BGZXOUTFILEPATH = rb.getString("ccp.formal.bgzxoutfilepath");

    public static final String CCP_FILE_OUTPATH = "/OUT";
    public static final String CCP_FILE_INPATH = "/IN";

    public static final String CCP_RES_SALE_FIEL_OUTPATH = rb.getString("ccp.formal.outfilepath");
    public static final String CCP_RES_SALE_FIEL_INPATH = rb.getString("ccp.formal.infilepath");
    public static final int CPP_RES_ALL_GROUPCOUNT = 1000;
    public static final String CCP_CREATESPOTCONTRACT = "XZV504"; // 现货合同统货合同电文号
    public static final String CCP_CREATESTOCKCONTRACT = "XZXSH2"; // 股份合同接收反馈电文号

    public static final String CCP_WEBSERVICE_BRIDGE = rb.getString("ccp.webservice.bridge");
    public static final String WEBSERVICE_PUTRESTIMING = rb.getString("webservice.putResTiming");
    public static final String WEBSERVICE_DOWNRESTIMING = rb.getString("webservice.downResTiming");
    public static final String WEBSERVICE_CANCELAPPLICATION = rb.getString("webservice.cancelApplication");
    public static final String WEBSERVICE_SYNCSALERULE = rb.getString("webservice.syncSaleRule");

    public static final String ServiceSOWBhe31_URL = rb.getString("webservice.ServiceSOWBhe31_URL");
    public static final String ServiceSOWBhe32_URL = rb.getString("webservice.ServiceSOWBhe32_URL");
    public static final String ServiceQDAOfflinePack_URL = rb.getString("webservice.ServiceQDAOfflinePack_URL");

    public static final String CCP_FUTURE_RES_ALL_QDBSYNC = "XSXZHA";

    public static final String CCP_FUTURE_RES_ALL_QDBSYNCBACK = "XZXSHA";

    public static final String CCP_FUTURE_RES_ALL_SendStoreResInfo = "XZV511";
    public static final String CCP_FUTURE_CREATESTOCKCONTRACT = "XZXSH2"; // 股份合同接收反馈电文号

    public static final String CCP_FUTURE_CreateOrCancelOrder = "XZXSHB";

    public static final String CCP_FUTURE_UPLOADPACKS_URL = rb
            .getString("webservice.QDAUploadPacksService_PortType_URL");

    public static final String CPP_WEBADD_ServiceSOWB008=rb
            .getString("webservice.ServiceSOWB008_URL");

    public static final String CPP_WEBADD_ServiceSOWB0022=rb
            .getString("webservice.ServiceSOWB0022_URL");

    public static final String CPP_WEBADD_ServiceSOWB007=rb
            .getString("webservice.ServiceSOWB007_URL");

    public static final String CPP_WEBADD_ServiceSOWBhe003=rb
            .getString("webservice.ServiceSOWBhe003_URL");

    public static final String CPP_WEBADD_QDADealInfoService_address=rb
            .getString("webservice.QDADealInfoService_address");

    public static final String RANDOM_LOGIN_KEY="login_code";
    public static final String DATACENTER_SOAP_RPC = rb.getString("datacenter.soap.rpc");
    public static final String JYZX_PARSE_RES_SALE_LIST = rb.getString("jyzx.parse.res.sale.list");


}
