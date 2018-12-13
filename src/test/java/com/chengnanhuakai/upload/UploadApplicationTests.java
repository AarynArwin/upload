//package com.chengnanhuakai.upload;
//
//import com.chengnanhuakai.upload.config.Qiniu;
//import com.chengnanhuakai.upload.controller.ImageUploadController;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import javax.annotation.Resource;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UploadApplicationTests {
//
//    private MockMvc mockMvc;
//
//    @Resource
//    private Qiniu qiniu;
//
//    @Test
//    public void contextLoads() {
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(new ImageUploadController()).build();
//    }
//
//    @Test
//    public void getHello() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/image/uploadtoken/simple")
//                .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
//    }
//
//}
