package cn.codeyang.demo.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author yangzhongyang
 */
public class MockServer {
    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8060);
        WireMock.removeAllMappings();


        mock("/order/1", "01");

    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".json");
        String content = FileUtils.readFileToString(resource.getFile(), "utf-8");

        WireMock.stubFor(
                WireMock.get(WireMock.urlEqualTo(url))
                        .willReturn(WireMock.aResponse()
                                .withBody(content).withStatus(200))
        );
    }
}
