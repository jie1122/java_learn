package com.example.reggie.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import com.example.reggie.common.R;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/common")
public class commonController {
  @Value("${reggie.path}")
  private String basePath;

  @PostMapping("/upload")
  public R<String> upload(MultipartFile file) {

    String originalFilename = file.getOriginalFilename();
    String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    //  通过原始文件后缀和uuid生成新的文件名
    String fileName = UUID.randomUUID().toString() + suffix;

    File dir = new File(basePath);
    if (!dir.exists()) {
      dir.mkdir();
    }

    try {
      file.transferTo(new File(basePath + fileName));
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return R.success(fileName);
  }

  @GetMapping("/download")
  public void download(String name, HttpServletResponse response){

    try {
      response.setHeader("Content-Type","image/jpeg");
      FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
      ServletOutputStream outputStream = response.getOutputStream();

      byte[] bytes = new byte[1024];

      while (true) {
        int len = fileInputStream.read(bytes);
        if (len == -1) {
          break;
        }
        outputStream.write(bytes);
        outputStream.flush();
      }

      fileInputStream.close();
      outputStream.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
