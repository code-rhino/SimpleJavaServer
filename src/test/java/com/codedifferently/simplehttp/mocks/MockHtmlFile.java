package com.codedifferently.simplehttp.mocks;

import java.io.IOException;
import java.nio.charset.Charset;

public class MockHtmlFile {
    public static String readFile(String path , Charset encoding) throws IOException {
        return  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "Hello world\n" +
                "</body>\n" +
                "</html>";
    }
}
