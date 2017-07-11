package com.infinitesense.bobill.infinitesense.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by bobill on 2017/6/14.
 */

public class HttpConn {
    public static void SetCookiesAutoManage(){
        CookieHandler.setDefault(new CookieManager(null,CookiePolicy.ACCEPT_ALL ));
    }

    public static String makeParams(List<String> labels,List<String> values)throws IOException{
        String params = "";
        for(int i=0;i<labels.size();i++){
            params += "&";
            params += URLEncoder.encode(labels.get(i), "UTF-8");
            params += "=";
            params += URLEncoder.encode(values.get(i), "UTF-8");
        }
        return params.substring(1);
    }

    public static String HttpGet(String url){
        String content = "#no data#";
        HttpURLConnection httpCon = null;
        try {
            URL net_url = new URL(url);
            httpCon = (HttpURLConnection) net_url.openConnection();
            if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream is = httpCon.getInputStream();
                content = readStream(is);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(httpCon !=null)
                httpCon.disconnect();
            return content;
        }
    }

    public static String HttpPost(String url, String params){
        String content = "#no data#";
        HttpURLConnection httpCon = null;
        try {
            URL net_url = new URL(url);
            httpCon = (HttpURLConnection) net_url.openConnection();
            httpCon.setRequestMethod("POST"); //set request method

            httpCon.setDoInput(true);
            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);

            // set request overtime
            httpCon.setReadTimeout(5000);
            httpCon.setConnectTimeout(5000);

            // set request head info
            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            httpCon.setRequestProperty("Connection", "keep-alive");
            httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpCon.setRequestProperty("Content-Length", String.valueOf(params.getBytes().length));
            httpCon.setRequestProperty("User-Agent"
                    ,"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
            httpCon.setRequestProperty("Accept-Language","en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2");
            httpCon.setRequestProperty("Accept-Encoding","gzip, deflate");
            // prepare upload data
            OutputStream os = httpCon.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();
            // post and receive
            if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK){   //success
                InputStream is = httpCon.getInputStream();
                content = readStream(is);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpCon!=null)
                httpCon.disconnect();
            return content;
        }
    }

    public static String readStream(InputStream is){
        String content ="";

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String line;

            while ((line = br.readLine())!= null){
                content += line;
                content += "\n";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
