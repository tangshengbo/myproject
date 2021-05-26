package com.tangshengbo.io.netty;

import com.tangshengbo.io.netty.http.NettyRequest;
import com.tangshengbo.io.netty.http.NettyResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Tangshengbo on 2021/4/19
 */
public class TomcatHandler extends ChannelInboundHandlerAdapter {


    private static final String rootDirectory = "E:/";

    private static final Map<String, String> mediaTypeMap = new HashMap<>();

    static {

        mediaTypeMap.put(".pdf", "application/pdf");
        mediaTypeMap.put(".css", "text/html");
        mediaTypeMap.put(".js", "text/html");
        mediaTypeMap.put(".html", "text/html;charset=utf-8");
        mediaTypeMap.put(".mp4", "video/mpeg4");
        mediaTypeMap.put(".ico", "image/x-icon");
        mediaTypeMap.put(".jpg", "image/*");
        mediaTypeMap.put(".doc", "application/msword");
        mediaTypeMap.put(".docx", "application/msword");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("======================================================");
        System.out.println(msg.toString());
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            System.out.println(fullHttpRequest.content().toString(StandardCharsets.UTF_8));
        }
        if (msg instanceof HttpRequest) {
            System.out.println("hello");
            HttpRequest req = (HttpRequest) msg;

            // 转交给我们自己的request实现
            NettyRequest request = new NettyRequest(ctx, req);
            // 转交给我们自己的response实现
            NettyResponse response = new NettyResponse(ctx, req);
            handler(request, response);
        }

    }

    private void handler(NettyRequest request, NettyResponse response) throws Exception {
        // 实际业务处理
        String url = request.getUrl();
        try {
            url = URLDecoder.decode(url, "UTF-8");
            System.out.println(url);
            File theFile = new File(rootDirectory,
                    url);
            if (theFile.isDirectory()) {
                StringBuilder builder = new StringBuilder();
                for (File file : Objects.requireNonNull(theFile.listFiles())) {

                    String path = "";
                    String canonicalPath = file.getCanonicalPath();
                    if (canonicalPath.contains("E:") || canonicalPath.contains("F:")) {
                        path = canonicalPath.substring(2);
                    }
                    System.out.println(path);
                    String name = String.format("<a href='%s'>%s</a>", path, file.getName());
                    builder.append(name).append("</br>");
                }
                response.write(builder.toString(), "text/html;charset=utf-8");
            } else {
                String substring = url.substring(url.lastIndexOf("."));
                String mediaType = mediaTypeMap.get(substring);
                byte[] theData = Files.readAllBytes(theFile.toPath());
                response.write(new String(theData), mediaType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            response.write("文件没有找到", "text/html");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }

}