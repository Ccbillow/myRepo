package org.cbillow.util;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by wuyafei on 15-5-8.
 */
public class BrowserHttpClient {

    private final static Logger logger = LoggerFactory.getLogger(BrowserHttpClient.class);

    private final static int SOCKETTIMEOUT = 10000;
    private final static int CONNECTIONTIMEOUT = 5000;
    private final static int CONNREQUESTTIMEOUT = 5000;
    private final static int SOCKETSOTIMEOUT = 15000;

    private CloseableHttpClient browserHttpClient = null;
    private RequestConfig requestConfig = null;
    private BasicCookieStore cookieStore = null;

    public BrowserHttpClient(String ip, int port, Cookie[] cookies) {
        cookieStore = new BasicCookieStore();
        RequestConfig.Builder builder = RequestConfig.custom().setSocketTimeout(SOCKETTIMEOUT)
                .setConnectionRequestTimeout(CONNREQUESTTIMEOUT)
                .setConnectTimeout(CONNECTIONTIMEOUT).setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY);
        if (cookies != null && cookies.length > 0) {
            cookieStore.addCookies(cookies);
        }
        if (StringUtils.isNotBlank(ip)) {
            HttpHost proxy = new HttpHost(ip, port);
            requestConfig = builder.setProxy(proxy).build();
        } else {
            requestConfig = builder.build();
        }
        this.browserHttpClient = createHttpsClient();
    }

    public BrowserHttpClient(String ip, int port, Cookie[] cookies, int conTimeout, int socketTimeout) {
        cookieStore = new BasicCookieStore();
        RequestConfig.Builder builder = RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectionRequestTimeout(CONNREQUESTTIMEOUT)
                .setConnectTimeout(conTimeout).setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY);
        if (cookies != null && cookies.length > 0) {
            cookieStore.addCookies(cookies);
        }
        if (StringUtils.isNotBlank(ip)) {
            HttpHost proxy = new HttpHost(ip, port);
            requestConfig = builder.setProxy(proxy).build();
        } else {
            requestConfig = builder.build();
        }
        this.browserHttpClient = createHttpsClient();
    }


    public BrowserHttpClient(String ip, int port, Cookie[] cookies, int conTimeout, int socketTimeout, Boolean isRedirect) {
        cookieStore = new BasicCookieStore();
        RequestConfig.Builder builder = RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectionRequestTimeout(CONNREQUESTTIMEOUT)
                .setConnectTimeout(conTimeout).setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
                .setRedirectsEnabled(isRedirect);
        if (cookies != null && cookies.length > 0) {
            cookieStore.addCookies(cookies);
        }
        if (StringUtils.isNotBlank(ip)) {
            HttpHost proxy = new HttpHost(ip, port);
            requestConfig = builder.setProxy(proxy).build();
        } else {
            requestConfig = builder.build();
        }
        this.browserHttpClient = createHttpsClient();
    }

    public void updateCookieStore(Cookie[] cookies) {
        cookieStore.addCookies(cookies);
    }

    public void updateCookieStore(String str) {
        if (StringUtils.isNotBlank(str)) {
            String[] strs = str.split(";");
            for (int i = 0; i < strs.length; i++) {
                int opstion = strs[i].indexOf("=");
                int length = strs[i].length();
                BasicClientCookie cookie = new BasicClientCookie(strs[i].substring(0, opstion),
                        strs[i].substring(opstion + 1, length));
                cookie.setDomain("ch.com");
                cookieStore.addCookie(cookie);
            }
        }
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }


    /**
     * 天津的pay获取location
     * @throws IOException
     */
    /*public GSCommonRes<String> postForHeader(String url, String content, Header[] headers) throws IOException {
        GSCommonRes<String> result=new GSCommonRes<String>();
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(new StringEntity(content));
            httpPost.setHeaders(headers);
            HttpResponse httpResp = browserHttpClient.execute(httpPost);
            logger.info("pay postForHeader statusCode:{}", httpResp.getStatusLine().getStatusCode());
            String response = EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());
            if (StringUtils.isBlank(response)){
                result.setSuccess(true);
                Header location = httpResp.getFirstHeader("Location");
                result.setData(location.getValue());
                logger.info("pay postForHeader location:{}",location.getValue());
            }else {
                result.setErrorMsg(response);
            }
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return result;
    }*/

    /**
     * 首航的pay获取location
     * @throws IOException
     */
    public String postForHeader(String url, String content, Header[] headers, String type) throws IOException {
        String result = "";
        HttpPost httpPost = null;
        if (StringUtils.equalsIgnoreCase("JDPay", type)) {
            try {
                httpPost = new HttpPost(url);
                httpPost.setConfig(requestConfig);
                httpPost.setEntity(new StringEntity(content));
                httpPost.setHeaders(headers);
                HttpResponse httpResp = browserHttpClient.execute(httpPost);
                logger.info("pay postForHeader statusCode:{}", httpResp.getStatusLine().getStatusCode());
                String response = EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());
                if (StringUtils.isBlank(response)) {
                    Header location = httpResp.getFirstHeader("Location");
                    result = location.getValue();
                    logger.info("pay postForHeader location:{}", location.getValue());
                }
            } finally {
                if (httpPost != null) {
                    httpPost.releaseConnection();
                }
            }
        }
        return result;
    }

    /*public String get(String url, Header[] headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        if (headers != null && headers.length > 0) {
            httpGet.setHeaders(headers);
        }

        try {
            HttpResponse httpResponse = browserHttpClient.execute(httpGet);
            logger.info("statusCode:{}", httpResponse.getStatusLine().getStatusCode());
            Header[] headersRes = httpResponse.getAllHeaders();
            for (Header header : headersRes) {
                if (StringUtils.equalsIgnoreCase(header.getName(), "Location")) {
                    MUThreadLocal.setLocation(header.getValue());
                }
            }
            return EntityUtils.toString(httpResponse.getEntity(), Charsets.UTF_8.toString());
        } finally {
            httpGet.releaseConnection();
        }
    }*/

    public String get(String url, Header[] headers, int retryCount) throws IOException {
        for (int i = 0; i < retryCount; i++) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            if (headers != null && headers.length > 0) {
                httpGet.setHeaders(headers);
            }
            try {
                HttpResponse httpResponse = browserHttpClient.execute(httpGet);
                logger.info("statusCode:{}", httpResponse.getStatusLine().getStatusCode());
                String response = EntityUtils.toString(httpResponse.getEntity(), Charsets.UTF_8.toString());
                if (response != null && response.contains("The requested URL could not be retrieved")
                        && i < retryCount - 1) {
                    logger.warn("可能是代理IP异常，重试:{}", response);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.error("sleep异常", e);
                    }
                    continue;
                }
                return response;
            } catch (IOException e) {
                if (i == retryCount - 1) {
                    throw e;
                } else {
                    logger.warn("请求网络异常重试", e);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                        logger.error("sleep异常", e2);
                    }
                    continue;
                }
            } finally {
                httpGet.releaseConnection();
            }
        }
        return null;
    }

    public byte[] getEntity(String url, Header[] headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        if (headers != null && headers.length > 0) {
            httpGet.setHeaders(headers);
        }
        try {
            HttpResponse httpResponse = browserHttpClient.execute(httpGet);
            return EntityUtils.toByteArray(httpResponse.getEntity());
        } finally {
            httpGet.releaseConnection();
        }
    }

    public String get(String url, Header[] headers, List<Header> resHeaders) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        if (headers != null && headers.length > 0) {
            httpGet.setHeaders(headers);
        }

        try {
            HttpResponse httpResponse = browserHttpClient.execute(httpGet);
            logger.info("statusCode:{}", httpResponse.getStatusLine().getStatusCode());
            resHeaders.addAll(Arrays.asList(httpResponse.getAllHeaders()));
            return EntityUtils.toString(httpResponse.getEntity(), Charsets.UTF_8.toString());
        } finally {
            httpGet.releaseConnection();
        }
    }


    public String post(String url, List<NameValuePair> nvPairs, Header[] headers) throws IOException {
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(new UrlEncodedFormEntity(nvPairs, Charsets.UTF_8.toString()));
            httpPost.setHeaders(headers);
            HttpResponse httpResp = browserHttpClient.execute(httpPost);
            logger.info("statusCode:{}", httpResp.getStatusLine().getStatusCode());
            return EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    public String post(String url, Map<String, String> parameter) throws IOException {
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(mapToHttpEntity(parameter));

            HttpResponse httpResp = browserHttpClient.execute(httpPost);
            logger.info("statusCode:{}", httpResp.getStatusLine().getStatusCode());
            return EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());

        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    public String post(String url, Map<String, String> parameter, Header[] headers) throws IOException {
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(mapToHttpEntity(parameter));
            httpPost.setHeaders(headers);
            HttpResponse httpResp = browserHttpClient.execute(httpPost);
            logger.info("statusCode:{}", httpResp.getStatusLine().getStatusCode());
            return EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    public String post(String url, String content, Header[] headers) throws IOException {
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(new StringEntity(content));
            httpPost.setHeaders(headers);
            HttpResponse httpResp = browserHttpClient.execute(httpPost);
            logger.info("statusCode:{}", httpResp.getStatusLine().getStatusCode());
            return EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    public HttpResponse httpPost(String url, Map<String, String> parameter, Header[] headers) throws IOException {
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(mapToHttpEntity(parameter));
            httpPost.setHeaders(headers);
            return browserHttpClient.execute(httpPost);
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    public byte[] httpExecuteBinary(HttpUriRequest request, Header[] headers)
            throws IOException {
        HttpEntity entity = null;
        try {
            request.setHeaders(headers);
            HttpResponse response = browserHttpClient.execute(request);
            StatusLine status = response.getStatusLine();
            entity = response.getEntity();
            if (status != null && status.getStatusCode() == 200) {
                byte[] content = EntityUtils.toByteArray(entity);
                entity = null;
                return content;
            } else {
                logger.warn("can\'t post url: " + request.getURI() + "   " + status);
                return null;
            }
        } finally {
            EntityUtils.consume(entity);
        }
    }

    public String post(String url, Map<String, String> parameter, Header[] headers, int retryCount) throws IOException {
        for (int i = 0; i < retryCount; i++) {
            HttpPost httpPost = null;
            try {
                httpPost = new HttpPost(url);
                httpPost.setConfig(requestConfig);
                httpPost.setEntity(mapToHttpEntity(parameter));
                httpPost.setHeaders(headers);
                HttpResponse httpResp = browserHttpClient.execute(httpPost);
                logger.info("statusCode:{}", httpResp.getStatusLine().getStatusCode());
                String response = EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());
                if (response != null && response.contains("The requested URL could not be retrieved")
                        && i < retryCount - 1) {
                    logger.warn("可能是代理IP异常，重试:{}", response);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.error("sleep异常", e);
                    }
                    continue;
                }
                return response;
            } catch (IOException e) {
                if (i == retryCount - 1) {
                    throw e;
                } else {
                    logger.warn("请求网络异常重试", e);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                        logger.error("sleep异常", e2);
                    }
                    continue;
                }
            } finally {
                if (httpPost != null) {
                    httpPost.releaseConnection();
                }
            }
        }
        return null;
    }

    public String post(String url, Map<String, String> parameter, Header[] headers, List<Header> resHeaders)
            throws IOException {
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(mapToHttpEntity(parameter));
            httpPost.setHeaders(headers);
            HttpResponse httpResp = browserHttpClient.execute(httpPost);
            logger.info("statusCode:{}", httpResp.getStatusLine().getStatusCode());
            resHeaders.addAll(Arrays.asList(httpResp.getAllHeaders()));
            return EntityUtils.toString(httpResp.getEntity());
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    private HttpEntity mapToHttpEntity(Map<String, String> params) throws UnsupportedEncodingException {
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> nvPairs = Lists.newArrayList();
            for (String key : params.keySet()) {
                nvPairs.add(
                        new BasicNameValuePair(StringUtils.trimToEmpty(key), StringUtils.trimToEmpty(params.get(key))));
            }
            return new UrlEncodedFormEntity(nvPairs, Charsets.UTF_8.toString());
        }
        return null;
    }

    private CloseableHttpClient createHttpsClient() {
        X509TrustManager x509mgr = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }

            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509mgr}, null);
        } catch (NoSuchAlgorithmException e) {
            logger.error("https createHttpsClient NoSuchAlgorithmException:{}", e);
        } catch (KeyManagementException e1) {
            logger.error("https createHttpsClient KeyManagementException:{}", e1);
        }

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setSoLinger(-1).
                setSoReuseAddress(false).setSoTimeout(SOCKETSOTIMEOUT).setTcpNoDelay(true).build();

        return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).setDefaultCookieStore(cookieStore)
                .setMaxConnTotal(400)
                .setMaxConnPerRoute(50).setDefaultSocketConfig(socketConfig).build();
    }

}
