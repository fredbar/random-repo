/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lunatech.assessment.model;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Fred
 */
public class LunaModelIT
{

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    public LunaModelIT()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of fillModel method, of class LunaModel.
     */
    @Test
    public void testFillModel() throws Exception
    {
        System.out.println( "fillModel" );
        InputStreamReader countries = null;
        InputStreamReader airports = null;
        InputStreamReader runways = null;
        //    LunaModel instance = new LunaModelImpl();
//        instance.fillModel( countries, airports, runways );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of guessCountry method, of class LunaModel.
     */
    @Test
    public void testGuessCountry()
    {
        System.out.println( "guessCountry" );
        String name = "";
        //     LunaModel instance = new LunaModelImpl();
        Country expResult = null;
//        Country result = instance.guessCountry( name );
//        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of reportSurfaceTypes method, of class LunaModel.
     */
    @Test
    public void testReportSurfaceTypes()
    {
        System.out.println( "reportSurfaceTypes" );
        //     LunaModel instance = new LunaModelImpl();
        Map<String, Collection<SurfaceType>> expResult = null;
//        Map<String, Collection<SurfaceType>> result = instance.reportSurfaceTypes();
//        assertEquals( expResult, result );
//        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of sortByAirportCount method, of class LunaModel.
     */
    @Test
    public void testSortByAirportCount()
    {
        System.out.println( "sortByAirportCount" );
        boolean ascendingSort = false;
        int limit = 0;
        // LunaModel instance = new LunaModelImpl();
        Collection<Country> expResult = null;
//        Collection<Country> result = instance.sortByAirportCount( ascendingSort, limit );
//        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of sortByLattitude method, of class LunaModel.
     */
    @Test
    public void testSortByLattitude()
    {
        System.out.println( "sortByLattitude" );
        // LunaModel instance = new LunaModelImpl();
//        Map<String, Collection<Runway>> expResult = null;
//        Map<String, Collection<Runway>> result = instance.sortByLattitude();
//        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

}
