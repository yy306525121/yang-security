package cn.codeyang.controller;

import cn.codeyang.demo.YangSecurityDemoApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YangSecurityDemoApplication.class)
public class FileControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testFileUpload() throws Exception {
        //String result = mockMvc.perform(MockMvcRequestBuilders.multipart("/file").file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
        //        .andExpect(MockMvcResultMatchers.status().isOk())
        //        .andReturn().getResponse().getContentAsString();
        //
        //System.out.println(result);
    }
}
