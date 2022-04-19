package com.example.helloworld.server;

import fi.iki.elonen.NanoHTTPD;

public class HttpService extends NanoHTTPD {
    //构造函数 赋值父类
    public HttpService(int port){
        super(port);
    }

    //重写Serve方法，每次请求时会调用该方法
    @Override
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
//        //获取请求uri
//        String uri = session.getUri();
//        //这里默认把接收到的uri返回
//        return NanoHTTPD.newFixedLengthResponse(uri);

        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html><html><body>");
        builder.append("Sorry, Can't Found the page!");
        builder.append("</body></html>\n");
        return newFixedLengthResponse(builder.toString());
    }

}
