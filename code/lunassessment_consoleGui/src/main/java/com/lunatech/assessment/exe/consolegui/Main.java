package com.lunatech.assessment.exe.consolegui;

import com.lunatech.assessment.model.Airport;
import com.lunatech.assessment.model.Country;
import com.lunatech.assessment.model.LatitudeScore;
import com.lunatech.assessment.model.LunaModel;
import com.lunatech.assessment.model.OldschoolLunaModel;
import com.lunatech.assessment.model.Runway;
import com.lunatech.assessment.model.SurfaceType;
import com.lunatech.assessment.model.ValidationExecutable;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Console executable.
 * Run in a shell as follows:
 * mvn exec:java -Dexec.mainClass="com.lunatech.assessment.exe.consolegui.Main"
 *
 * @author Frederic Barachant
 */
public class Main
{

    public static void main( String args[] )
    {

        Main main = new Main();
        main.go();
    }
    private LunaModel model;

    private void go()
    {
        InputStreamReader countries = null;
        InputStreamReader airports = null;
        InputStreamReader runways = null;
        try
        {
            System.out.println( "Please wait while loading dataset." );
            this.model = new OldschoolLunaModel();
            InputStream countriesStream = getClass().getClassLoader().getResourceAsStream( "countries.csv" );
            InputStream airportStream = getClass().getClassLoader().getResourceAsStream( "airports.csv" );
            InputStream runwaysStream = getClass().getClassLoader().getResourceAsStream( "runways.csv" );
            countries = new InputStreamReader( countriesStream );
            airports = new InputStreamReader( airportStream );
            runways = new InputStreamReader( runwaysStream );
            this.model.fillModel( countries, airports, runways );
            System.out.println( "Data model loaded.\n\n" );

            Console c = System.console();
            if ( c == null )
            {
                System.err.println( "No console." );
                System.exit( 1 );
            }

            while ( true )
            {
                String command = c.readLine( "\n\nEnter something to search a country or just press enter to get a database report.\n-> " );
                switch ( command )
                {
                    case "":
                        report();
                        break;
                    default:
                        Country result = model.guessCountry( command );
                        if ( null == result )
                        {
                            System.err.println( "Sorry, i found nothing approaching this" );
                        }
                        else
                        {
                            System.err.println( "Thank you. I found this:\n" + result.getName() );
                            int count = result.getAirportCount();
                            System.err.println( String.format( "This country has %d airports.\n", count ) );
                            c.readLine( "press enter to see them now." );
                            final Iterator<Airport> airportIterator = result.iterator();
                            while ( airportIterator.hasNext() )
                            {
                                Airport airport = airportIterator.next();
                                System.err.println( String.format( "- %s, that has those runways:", airport.getName() ) );
                                Iterator<Runway> runwayIterator = airport.iterator();
                                while ( runwayIterator.hasNext() )
                                {
                                    Runway runway = runwayIterator.next();
                                    System.err.println( String.format( "\t- %02d, %04.02f feet long, in %s ", ( runway.getOrientation() / 10 ) - 1, runway.getLength(), runway.getSurfaceType().getTypeName() ) );
                                }
                                System.err.println( "" );
                            }
                        }
                }
            }

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

}
