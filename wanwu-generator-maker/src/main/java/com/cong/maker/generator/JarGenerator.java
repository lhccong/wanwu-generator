package com.cong.maker.generator;

import java.io.*;

/**
 * @author Cong
 */
public class JarGenerator {
    public static void doGenerate(String projectDit) throws InterruptedException, IOException {
        //调用Process类执行Maven打包命令
        String winMavenCommand= "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand= "mvn clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;

        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDit));

        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        System.out.println("命令执行结束，退出码："+exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("D:\\develop\\wanwu-generator\\wanwu-generator-basic");
    }
}
