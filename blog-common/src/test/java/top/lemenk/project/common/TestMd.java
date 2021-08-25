package top.lemenk.project.common;

import org.junit.Test;
import org.pegdown.PegDownProcessor;
import top.lemenk.project.common.util.markdown.FileReadUtil;

import java.io.FileReader;

/**
 * @author lemenk@163.com
 * @date 2021/8/11 17:53
 * @className TestMd
 * @desc
 */
public class TestMd {

    @Test
    public void get() throws Exception {

        String html = null;
        FileReader fr = new FileReader("F:/myWorkSpace/PUE-blog/README.md");

        char[] cbconf = new char[1024];
        while (fr.read(cbconf) != -1) {
            html = new String(cbconf);
        }

        PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
        html = pdp.markdownToHtml(html);
        //WriteHtml.writeHtml(html, "aaa");
    }
}
