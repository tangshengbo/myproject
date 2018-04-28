package com.tangshengbo.util.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SimpleHttpClient {

    private CloseableHttpClient httpClient;

    /**
     * 默认请求超时（单位：毫秒）
     */
    private static final int DEFAULT_CONNECTION_TIMEOUT = 30000;
    /**
     * 默认读取超时（单位：毫秒）
     */
    private static final int DEFAULT_SO_TIMEOUT = 90000;

    /**
     * 发送数据实体
     */
    private HttpSendModel httpSendModel;

    {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT)
                .setSocketTimeout(DEFAULT_SO_TIMEOUT)
                .setConnectionRequestTimeout(DEFAULT_SO_TIMEOUT)
                .build();
        httpClient = HttpClients.custom()
                .setConnectionManager(new BasicHttpClientConnectionManager())
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
    }

    /**
     * 按默认请求超时，读取超时设置
     */
    public SimpleHttpClient() {
    }

    public SimpleHttpClient(HttpSendModel httpSendModel) {
        this.httpSendModel = httpSendModel;
    }

    public void enableSSL() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", 443, ssf));
        } catch (Exception e) {
            //
        }

    }

    /**
     * @return the httpclient
     */
    public HttpClient getHttpclient() {
        return httpClient;
    }

    public HttpSendModel getHttpSendModel() {
        return httpSendModel;
    }
}
