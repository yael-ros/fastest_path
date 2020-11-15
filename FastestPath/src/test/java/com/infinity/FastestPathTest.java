package com.infinity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FastestPathTest {
    @Test
    public void testFromVertexCToF(){
        FastestPath fastestPath = new FastestPath("user_input_1.txt");
        assertEquals("C--(5)-->B--(1)-->F", fastestPath.getFastestPath());
    }
    @Test
    public void testFromVertexAToF(){
        FastestPath fastestPath = new FastestPath("user_input_2.txt");
        assertEquals("A--(5)-->F", fastestPath.getFastestPath());
    }
    @Test
    public void testFromVertexFToC(){
        FastestPath fastestPath = new FastestPath("user_input_3.txt");
        assertEquals("F--(2)-->A--(1)-->C", fastestPath.getFastestPath());
    }
    @Test
    public void testFromVertexEToD(){
        FastestPath fastestPath = new FastestPath("user_input_4.txt");
        assertEquals("E--(3)-->A--(4)-->D", fastestPath.getFastestPath());
    }
    @Test
    public void testFromVertexAToB(){
        FastestPath fastestPath = new FastestPath("user_input_5.txt");
        assertEquals("A--(1)-->C--(5)-->B", fastestPath.getFastestPath());
    }

    @Test
    public void testFromVertexBToF(){
        FastestPath fastestPath = new FastestPath("user_input_6.txt");
        assertEquals("B--(1)-->F", fastestPath.getFastestPath());
    }

    @Test
    public void testFromVertexDToA(){
        FastestPath fastestPath = new FastestPath("user_input_7.txt");
        assertEquals("D--(2)-->C--(2)-->A", fastestPath.getFastestPath());
    }
}
