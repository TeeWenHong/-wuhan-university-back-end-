package com.qingshuge.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {

    public static String test ="0";
    private static final String PYTHON_SCRIPT_PATH = "C:\\Users\\Huawei\\Desktop\\phashFunc\\main.py";

    @GetMapping("/compare")
    @ResponseBody
    public int compareImages(
            @RequestParam("imagePath") String imagePath) {


        String imagePath1 =  "C:\\Users\\Huawei\\Desktop\\cahce\\"+imagePath;
        String line;
        System.out.println(imagePath1);
        // 对参数进行 URL 编码

        List<String> command = new ArrayList<>();
        command.add("python");
        command.add(PYTHON_SCRIPT_PATH);
        command.add(imagePath1);

//        System.out.println("command传值前"+command);
        System.out.println("test"+test);


        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
//            System.out.println("这是什么"+process);

            // 读取Python子进程的错误信息流
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorLine = null;
            while ((errorLine = errorReader.readLine()) != null) {
                System.out.println(errorLine);
            }
            errorReader.close();

            // 读取Python子进程的标准输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((line = reader.readLine()) != null) {
                System.out.println("line="+line);
                test = line;
            }

            reader.close();

            process.waitFor();
        } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return 2;
            }
        System.out.println("test:"+test);
        int test2 = Integer.parseInt(test);
        System.out.println("test2:"+test2);
        if(test2 == 1){
            test ="0";
            return 0;
        }else {
            test ="0";
            return 1;
        }

    }
}





















//    ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\Huawei\\Desktop\\phashFunc\\main.py");
//            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//                    pb.redirectError(ProcessBuilder.Redirect.INHERIT);
//                    Process process = pb.start();
// 等待子进程运行完毕
//int exitCode = process.waitFor();
//            if (exitCode != 0) {
//                    System.err.println("Python script failed with exit code " + exitCode);
//                    }


//@Controller
//public class ImageController {
//
//    private static final String PYTHON_SCRIPT_PATH = "C:\\Users\\Huawei\\Desktop\\phashFunc\\main.py";
//
//    @GetMapping("/compare")
//    @ResponseBody
//    public String compareImages(
//            @RequestParam("imagePath") String imagePath2,
//            @RequestParam("hashSize") int hashSize,
//            @RequestParam("threshold") int threshold) {
//
//        String imagePath1 = "C:\\Users\\Huawei\\Desktop\\img\\" + imagePath2;
//        String folderPath = "C:\\Users\\Huawei\\Desktop\\img\\";
//        System.out.println(imagePath1);
//        try {
//            // 对路径参数进行 URL 编码
//            String imagePath = URLEncoder.encode(imagePath1, "UTF-8");
//            folderPath = URLEncoder.encode(folderPath, "UTF-8");
//
//            // 构造 Python 命令和参数列表
//            List<String> command = new ArrayList<>();
//            command.add("python");
//            command.add(PYTHON_SCRIPT_PATH);
//            command.add(imagePath);
//            command.add(folderPath);
//            command.add(String.valueOf(hashSize));
//            command.add(String.valueOf(threshold));
//            System.out.println(command);
//
//            // 创建 ProcessBuilder 对象，并设置工作目录
//            ProcessBuilder processBuilder = new ProcessBuilder(command);
////            processBuilder.directory(new File("C:\\Users\\Huawei\\Desktop\\phashFunc"));
//
//            // 启动 Python 子进程，并等待其执行完毕
//            Process process = processBuilder.start();
//            process.waitFor();
//
//            System.out.println("Python subprocess started with command: " + processBuilder);
////            System.out.println("Python subprocess output: " + sb.toString());
//
//            // 读取 Python 子进程的标准输出流
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            StringBuilder resultBuilder = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                resultBuilder.append(line);
//            }
//
//            return resultBuilder.toString();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}