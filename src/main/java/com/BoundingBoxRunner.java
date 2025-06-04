package com;

import java.io.*;

public class BoundingBoxRunner {

    public static String runProgram(String input) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("input", ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            writer.write(input);
            writer.close();
            ProcessBuilder pb = new ProcessBuilder("java", "com.example.BoundingBox");
            pb.redirectInput(tempFile);
            pb.redirectOutput(ProcessBuilder.Redirect.PIPE);

            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            process.waitFor();
            return output.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
