/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lunatech.assessment.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Fred
 */
@RunWith( Suite.class )
@Suite.SuiteClasses(
                {
            com.lunatech.assessment.model.AirportIT.class, com.lunatech.assessment.model.RunwayIT.class, com.lunatech.assessment.model.LunaModelIT.class, com.lunatech.assessment.model.SurfaceTypeIT.class, com.lunatech.assessment.model.CountryIT.class
        } )
public class ModelITSuite
{

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

}
