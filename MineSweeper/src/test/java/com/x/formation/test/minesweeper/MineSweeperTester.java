/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.x.formation.test.minesweeper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aabum
 */
public class MineSweeperTester {
    
     MineCommander mc ;
    public MineSweeperTester() {
        mc = new MineCommander();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test(expected = IllegalStateException.class)
    public void testHintFeildBeforInitialization() {
        mc.getHintField();
    }     

    @Test(expected = IllegalArgumentException.class)
    public void testSetFeildWithNullData() {
        mc.setMineField(null);
    }         
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetFeildWithEmptyData() {
        mc.setMineField("");
    }    
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetFeildWithDifferentColumnLengths() {
        mc.setMineField("*...\n..*..\n....");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetFeildWithWrongCharacters() {
        mc.setMineField("*...\n.A*.\n....");
    }    
    @Test
    public void testHintFeildWithCorrectInput1() {
        mc.setMineField("*...\n..*.\n....");
        String result= mc.getHintField(); 
        assertEquals(result,  "*211\n12*1\n0111" );
    }
    
    @Test
    public void testHintFeildWithCorrectInput2() {
        mc.setMineField("*.*.\n..*.\n....");
        String result= mc.getHintField();
        assertEquals(result,  "*3*2\n13*2\n0111" );
    }
}
