package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;

public class BoundingBoxIntegrationTests {

    private String runWithInput(String input) throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try (ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(out)) {

            System.setIn(in);
            System.setOut(printStream);

            BoundingBox.main(new String[]{});
            return out.toString().trim();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    @Test
    public void testSampleCaseFromProblem() throws Exception {
        String input = 
            "**-------***\n" +
            "-*--**--***-\n" +
            "-----***--**\n" +
            "-------***--\n";
        
        String expectedOutput = "(1,1)(2,2)";
        assertEquals(expectedOutput, runWithInput(input));
    }

    @Test
    public void testSingleAsterisk() throws Exception {
        String input = 
            "----\n" +
            "--*-\n" +
            "----\n";
        
        String expectedOutput = "(2,3)(2,3)";
        assertEquals(expectedOutput, runWithInput(input));
    }

    @Test
    public void testMultipleNonOverlappingGroups() throws Exception {
        String input =
            "*---*---*\n" +
            "---------\n" +
            "----*----\n";
        
        String expectedOutput = "(3,5)(3,5)";
        assertEquals(expectedOutput, runWithInput(input));
    }

    @Test
    public void testLargeGroup() throws Exception {
        String input =
            "********\n" +
            "********\n" +
            "********\n";
