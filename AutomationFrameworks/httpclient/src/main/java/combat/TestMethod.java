package combat;

import okhttp3.*;

import java.io.IOException;

public class TestMethod {
    public static Object[] getMethodCall(String testUrl)  {
        OkHttpClient okHttpClient=new OkHttpClient();
        //构建request
        Request request=new Request.Builder().url(testUrl).get().build();
        //使用okHttpClient客户端发送请求,并创建响应对象来获取请求结果
        try {
            Response response = okHttpClient.newCall(request).execute();
            return new Object[]{
                    response.code(),
                    response.headers(),
                    response.body().string(),
                    ""
            };

        } catch (IOException e) {
            System.out.println(e);
            return new Object[]{
                    "",
                    "",
                    "",
                    e
            };
        }
    }
    public static Object[] postMethodCall(String testUrl, String testData,String contentType, String testCookie)  {
        OkHttpClient okHttpClient = new OkHttpClient();
        //构建POST请求
        //--先申明请求参数格式申明
        MediaType mediaType = MediaType.parse(contentType);
        //--对于请求参数内容说明
        RequestBody requestBody = RequestBody.create(mediaType, testData);
        //请求体对象构建包含url/对于Cookie有要求的需要申明在header/请求方法
        Request request = new Request.Builder().header("Cookie", testCookie).url(testUrl).post(requestBody).build();
        //响应对象的构建，并打印响应内容，可以根据响应内容做断言
        try {
            Response response = okHttpClient.newCall(request).execute();
            return new Object[]{
                    response.code(),
                    response.headers(),
                    response.body().string(),
                    ""
            };

        } catch (IOException e) {
            System.out.println(e);
            return new Object[]{
                    "",
                    "",
                    "",
                    e
            };
        }
    }
}
