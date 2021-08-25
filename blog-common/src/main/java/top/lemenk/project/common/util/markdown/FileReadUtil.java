package top.lemenk.project.common.util.markdown;

import cn.hutool.core.io.file.FileReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import top.lemenk.project.common.exception.CommonException;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

/**
 * @author lemenk@163.com
 * @date 2021/8/11 17:56
 * @className WriteHtml
 * @desc
 */
@Slf4j
public class FileReadUtil {
    public static String getFileStr(File file) {
        if (Objects.isNull(file)) {
            throw new CommonException("文档信息不能为空");
        }
        long length = file.length();
        String name = file.getName();
        log.info("读取到的file的名称为：{}，大小为={}", name, length);
        FileReader fileReader = new FileReader(file);
        return fileReader.readString();
    }

    public static String getFileStr(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new CommonException("文档信息不能为空");
        }
        FileReader fileReader = new FileReader(filePath);
        return fileReader.readString();
    }

    public static InputStream getFileStreamByUrl(String fileUrl) {
        // 创建URL
        try {
            URL url = new URL(fileUrl);
            // 试图连接并取得返回状态码
            URLConnection urlConn = url.openConnection();
            urlConn.connect();
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 取数据长度
                int filesize = urlConn.getContentLength();
                log.info("获取资源{}内容长度为{}", fileUrl, filesize);
                return urlConn.getInputStream();
            } else {
                throw new CommonException("获取资源" + fileUrl + "失败，状态码：" + responseCode);
            }
        } catch (Exception e) {
            throw new CommonException("获取资源" + fileUrl + "失败");
        }
    }

    public static InputStream getFileStreamByPath(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new CommonException("文档信息不能为空");
        }
        FileReader fileReader = new FileReader(filePath);
        return fileReader.getInputStream();
    }

    /*
    * FileReader fileReader = new FileReader(filePath);
        return fileReader.getInputStream();*/


    /*public static void writeHtml(String htmlStr, String fileFirstName) {
        if (StringUtils.isBlank(htmlStr)) {
            throw new CommonException("文档信息不能为空");
        }
        if (StringUtils.isBlank(fileFirstName)) {
            throw new CommonException("文档信息不能为空");
        }
        String fileName = fileFirstName + ".html";
        File testFile = new File("D:" + File.separator + "filepath" + File.separator + "test" + File.separator + fileName);
        if (!testFile.exists()) {
            boolean mkDirs = testFile.mkdirs();
            if (!mkDirs) {
                throw new CommonException("创建文件夹失败");
            }
        }
        FileWriter fw = new FileWriter(fileFirstName + ".html");
        fw.write(htmlStr);
    }*/
}
