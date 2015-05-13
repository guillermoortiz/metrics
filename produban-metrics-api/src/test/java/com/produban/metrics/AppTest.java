package com.produban.metrics;

import com.produban.metrics.util.Factory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        
        
        return new TestSuite( AppTest.class );
        
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        //assertTrue( true );
        createPL_EM_ORDEN();
    }
    
    public static void createPL_EM_ORDEN()
    {
    	String[] lines;
    	String line;
    	String delimiter;
    	String pLine;
    	String[] pLines;
    	
    	line = "10|IBM|2015113|171646002851|ORN01|PL_EM_ORDEN|“ISRT|0000:1d8b:5f00:cad7:0002|0000:ced4:c014:705f:0001|2015-04-21-13.06.55|PLBALQDI|0000|0049|0015|754|QBBBBKC|001|0049|0001|PTD| 0009|ES|S|CCC|INM|001|2015-04-10|0001-01-01|2015-04-13|2015-04-13|2015-04-13|0049|0015|RED| | |PLBALE1 |2015-04-10-09.31.04.455131|PLCHDV1 |2015-04-20-18.46.03.028611|2015-04-13|2015-04-10|SEPA|RCUR| |CORE|003|27 |188,09|EUR| |N|OAP|PMS| | | | | | | | | ";
    	delimiter = ";";
    	line = line.replace("|", ";");
    	    	
    	lines = line.split(delimiter);
    	
    	pLine = "OpenBank;Intranet;Celda0;PL_EM_ORDEN";
    	pLines = pLine.split(";");
    	
    	Factory.createPL_EM_ORDEN(pLines,lines);
    	
    	
    }
}
