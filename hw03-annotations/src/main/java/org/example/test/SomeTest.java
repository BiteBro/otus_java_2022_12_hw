package org.example.test;

import org.example.annotations.After;
import org.example.annotations.Before;
import org.example.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SomeTest {

    @Before
    public void before(){
        List<Integer> list = new ArrayList<>();
    }
    @Test
    public void testA(){
        int[] ints = {1,2,3};
        int i = ints[1];
    }
    @Test
    public void testB(){
        int[] ints = {1,2,3};
        int i = ints[3];
    }
    @Test
    public void testC(){
        int[] ints = {1,2,3};
        int i = ints[0];
    }
    @Test
    public void testD(){
        int[] ints = {1,2,3};
        int i = ints[4];
    }
    @Test
    public void testE(){
        int[] ints = {1,2,3};
        int i = ints[1];
    }
    @Test
    public void testF(){
        int[] ints = {1,2,3};
        int i = ints[3];
    }
    @Test
    public void testG(){
        int[] ints = {1,2,3};
        int i = ints[0];
    }
    @After
    public void after(){
        List<Integer> list = null;
    }
}
