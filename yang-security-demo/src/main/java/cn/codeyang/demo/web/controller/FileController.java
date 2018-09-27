package cn.codeyang.demo.web.controller;

import cn.codeyang.demo.domain.FileInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    private String folder = "/Users/yangzhongyang/Desktop/img";

    @PostMapping
    @ApiOperation(value = "文件上传")
    public FileInfo upload(MultipartFile file) throws IOException {
        log.info(file.getName());
        log.info(file.getOriginalFilename());
        log.info(file.getSize() + "");


        File localFile = new File(folder, System.currentTimeMillis() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "文件下载")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        try {
            InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
            OutputStream outputStream = response.getOutputStream();

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
