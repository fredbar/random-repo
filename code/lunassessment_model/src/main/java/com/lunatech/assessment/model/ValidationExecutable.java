package com.lunatech.assessment.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * simple test app, non interactive, that makes some searches and prints a
 * report.
 * Done to act as a proof of concept, validation and skeleteon for future
 * interactive apps and unit tests.
 *
 * @author Frederic Barachant
 */
public class ValidationExecutable
{

    public static void main( String args[] )
    {

        ValidationExecutable main = new ValidationExecutable();
        main.go();
    }
    private OldschoolLunaModel model;

    private void go()
    {
        FileReader countries = null;
        try
        {
            System.out.println( "Please wait while loading dataset." );
            this.model = new OldschoolLunaModel();
            URL countriesURL = getClass().getClassLoader().getResource( "countries.csv" );
            URL airportsURL = getClass().getClassLoader().getResource( "airports.csv" );
            URL runwaysURL = getClass().getClassLoader().getResource( "runways.csv" );
            System.err.println( "country:" + countriesURL );
            System.err.println( "airport:" + airportsURL );
            System.err.println( "runways:" + runwaysURL );
            countries = new FileReader( countriesURL.getPath() );
            FileReader airports = new FileReader( airportsURL.getPath() );
            FileReader runways = new FileReader( runwaysURL.getPath() );
            this.model.fillModel( countries, airports, runways );
            System.out.println( "Data model loaded.\n\n" );
            testGuessCountry( "frnc" );
            testGuessCountry( "ita" );
            testGuessCountry( "zabi" );
            report();

        }
        catch ( FileNotFoundException ex )
        {
            Logger.getLogger( ValidationExecutable.class.getName() ).log( Level.SEVERE, null, ex );
        }
        catch ( IOException ex )
        {
            Logger.getLogger( ValidationExecutable.class.getName() ).log( Level.SEVERE, null, ex );
        }
        catch ( Throwable ex )
        {
            Logger.getLogger( ValidationExecutable.class.getName() ).log( Level.SEVERE, null, ex );
        }
        finally
        {
            try
            {
                countries.close();
            }
            catch ( IOException ex )
            {
                Logger.getLogger( ValidationExecutable.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }

    }

    private void report()
    {
        System.err.println( "\n\n***********REPORT\n" );

        System.err.println( "airport count report: Top list:" );
        List<Country> sortByAirportCount = model.sortByAirportCount();
        for ( int index = 0; index < 10; index++ )
        {//showing top list.
            Country country = sortByAirportCount.get( index );
            System.err.println( String.format( "pos:%02d %s %04d airports", index, country.getName(), country.getAirportCount() ) );
        }

        System.err.println( "\n\nairport count report: bottom list:" );
        for ( int index = sortByAirportCount.size() - 10; index < sortByAirportCount.size(); index++ )
        {//showing top list.
            Country country = sortByAirportCount.get( index );
            System.err.println( String.format( "pos:%02d %s %04d airports", index, country.getName(), country.getAirportCount() ) );
        }

        System.err.println( "\n\ntypes of runways per country:" );
        Map<String, Collection<SurfaceType>> reportSurfaceTypes = model.reportSurfaceTypes();
        String[] keyArray = reportSurfaceTypes.keySet().toArray( new String[reportSurfaceTypes.size()] );
        for ( int index = 0; index < keyArray.length; index++ )
        {
            String key = keyArray[index];
            System.err.print( String.format( "\n\nin %s, surfaces are\n\t", key ) );
            Collection<SurfaceType> types = reportSurfaceTypes.get( key );
            for ( SurfaceType type : types )
            {
                System.err.print( type.getTypeName() + "," );
            }
        }

        System.err.println( "\n\nMost common latitudes:" );
        LatitudeScore[] sortByLattitude = model.sortByLattitude();
        for ( int index = 0; index < 10; index++ )
        {
            LatitudeScore tuple = sortByLattitude[index];
            System.err.println( String.format( "\t%d: orientation %d has %d instances", index, tuple.getOrientation(), tuple.getCount() ) );
        }
    }

    private void testGuessCountry( String searchString )
    {
        long start = System.nanoTime();
        Country result = model.guessCountry( searchString );
        double time = ( System.nanoTime() - start ) / 1000000.;

        if ( null != result )
        {
            System.err.println( "your search :" + searchString + " found this:" + result.getName() + " in " + time + " ms" );
        }
        else
        {
            System.err.println( "sorry, did not find a correct match" );
        }
    }
}
