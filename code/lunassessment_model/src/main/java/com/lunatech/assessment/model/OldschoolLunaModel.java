package com.lunatech.assessment.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.IntegerValidator;

/**
 * Implementation of the model to reflect an oldschool/java7 way of doing
 * things.
 *
 * @author Frederic Barachant
 */
public class OldschoolLunaModel implements LunaModel
{

    private static final IntegerValidator VALIDATORINSTANCE = IntegerValidator.getInstance();

    /**
     * list of airports of the model. Those are also referenced in countries.
     */
    private ArrayList<Airport> airportsList = new ArrayList<>();

    /**
     * List of countries of the model.
     */
    private ArrayList<Country> countriesList = new ArrayList<>();

    @Override
    public void fillModel( InputStreamReader countries, InputStreamReader airports, InputStreamReader runways ) throws IOException
    {
        //TODO: remove all commented , profiling and stderr messages when comitting to production.
        long start = 0;//used to monitor performances
        long end = 0;//used to monitor performances
        //all entries will be uppercased for simplicity and efficiency in search matching. CSV files are not really consistent with casing.

        CSVParser countriesParser = new CSVParser( countries, CSVFormat.DEFAULT.withHeader() );
        CSVParser airportsParser = new CSVParser( airports, CSVFormat.DEFAULT.withHeader() );
        CSVParser runwayParser = new CSVParser( runways, CSVFormat.DEFAULT.withHeader() );

        // first, loading all countries into a list.
        // there are no filterings or operations made, we just read and add objects.
        //TODO: binary tree could be used for optimizing next step.
        System.err.println( "Parsing countries" );
        Iterator<CSVRecord> countriesIterator = countriesParser.iterator();
        start = System.nanoTime();
        while ( countriesIterator.hasNext() )
        {
            CSVRecord next = countriesIterator.next();
            String code = next.get( "code" ).toUpperCase();
            String name = next.get( "name" ).toUpperCase();
            this.countriesList.add( new Country( code, name ) );
        }
        end = System.nanoTime();
        double time = ( end - start ) / 1000000.;
        System.err.println( String.format( "added %d countries in %.02fms (%.03f ms per item)", this.countriesList.size(), time, time / this.countriesList.size() ) );
        //then i'll import the airports. I will need to link airports and countries so this will be a little more complex loop.
        //TODO: could be eventually optimized if list of runways was in a linkedlist first, and that list would be read and cleared from runways as we fill airports. Profile.
        System.err.println( "parsing airports" );
        Iterator<CSVRecord> airportsIterator = airportsParser.iterator();
        start = System.nanoTime();
        while ( airportsIterator.hasNext() )
        {
            CSVRecord next = airportsIterator.next();

            String ident = next.get( "ident" ).toUpperCase();
            String iso = next.get( "iso_country" ).toUpperCase();
            //getting the country that has this iso code.
            //TODO: could be optimized by first testing previous result, which ought to most of the time be the one we look for.
            Airport airport = new Airport( ident );
            for ( int i = 0; i < this.countriesList.size(); i++ )
            {
                Country country = this.countriesList.get( i );
                if ( true == country.getCode().equals( iso ) )
                {
                    this.airportsList.add( airport );
                    country.add( airport );
//                    airport = null;
                    break;
                }
            }
//            if ( null != airport )
//            {
//                System.err.println( "this airport has no country:" + airport );
//            }
        }
        end = System.nanoTime();
        time = ( end - start ) / 1000000.;
        System.err.println( String.format( "added and linked %d airports in %.02fms (%.03f ms per item)", this.airportsList.size(), time, time / this.airportsList.size() ) );
        //now that i have countries and airports, i'll load the runways.
        // for each runway i get, i'll look for the corresponding airport.
        System.err.println( "parsing runways" );
        int runwayParseCount = 0;
        int runwayLinkCount = 0;
        Iterator<CSVRecord> runwaysIterator = runwayParser.iterator();
        start = System.nanoTime();
        Airport previouslyScannedAirport = null;//this is an optimisation to runway addition, so that we avoid looking for an airport and first check if we are not looking for the same one as previously.
        while ( true == runwaysIterator.hasNext() )
        {
            runwayParseCount++;
            CSVRecord nextRunway = runwaysIterator.next();
            String airportIdentity = nextRunway.get( "airport_ident" ).toUpperCase();
            String orientation = nextRunway.get( "le_ident" );
            SurfaceType surfaceType = SurfaceType.instanciate( nextRunway.get( "surface" ) );
            final String length = nextRunway.get( "length_ft" );
            int length_ft = 0;
            if ( 0 == orientation.length() )
            {
                continue;// no orientation, whatsoever, i consider the entry to be invalid and will not store it.
            }
            if ( length.length() > 0 )
            {//this can happen if there are no entries or an empty one.
                length_ft = VALIDATORINSTANCE.validate( length );
            }
            Runway runway = new Runway();
            runway.setLength( length_ft );
            runway.setOrientation( orientation );
            runway.setSurfaceType( surfaceType );
            //ok, all data is there. I now need to link the runway to its airport.
            //looking for it.
            //checking if we are looking for previously edited airport before looking through the list.
            if ( null != previouslyScannedAirport && true == previouslyScannedAirport.getName().equals( airportIdentity ) )
            {//this optimisation has no visible impact on the procedure. Most of the time is taken by parsing of the csv (sigh)
                previouslyScannedAirport.add( runway );
//                    runway = null;
                runwayLinkCount++;
            }
            else
            {
                for ( int index = 0; index < this.airportsList.size(); index++ )
                {
                    Airport airport = this.airportsList.get( index );
                    if ( true == airport.getName().equals( airportIdentity ) )
                    {
                        previouslyScannedAirport = airport;
                        airport.add( runway );
//                    runway = null;
                        runwayLinkCount++;
                        break;
                    }
                }
            }
//            if ( null != runway )
//            {
//                System.err.println( "AIRPORT (" + airportIdentity + ") NOT FOUND FOR RUNWAY:" + runway );
//            }
        }
        end = System.nanoTime();
        time = ( end - start ) / 1000000.;
        System.err.println( String.format( "parsed %d runways, linked %d runways in %.02fms (%.03f ms per item)", runwayParseCount, runwayLinkCount, time, time / runwayParseCount ) );
    }

    @Override
    public Country guessCountry( String name )
    {
        assert 0 != this.countriesList.size() && 0 != this.airportsList.size();
        name = name.toUpperCase();
        //First loop, i'll rapidly check if the input corresponds to the start of a country code or name.
        for ( int index = 0; index < countriesList.size(); index++ )
        {
            Country country = countriesList.get( index );
            if ( true == country.getName().startsWith( name ) || true == country.getCode().startsWith( name ) )
            {
                return country;
            }
        }
        // if theere was no match, we'll check a fuzzy search using Jaro Winkler distance algorithm. (apache implementation from commons-lang3)
        // http://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance
        double bestDistance = 0;// those values are there to store the best result of the whole list.
        Country bestMatch = null;//this instance will be returned upon exit.
        for ( int index = 0; index < countriesList.size(); index++ )
        {
            Country country = countriesList.get( index );
            double distanceCode = StringUtils.getJaroWinklerDistance( name, country.getCode() );
            double distanceName = StringUtils.getJaroWinklerDistance( name, country.getName() );
            if ( distanceCode > bestDistance || distanceName > bestDistance )
            {
                bestDistance = distanceCode > distanceName ? distanceCode : distanceName;//updating best distance found.
                bestMatch = country;
            }
        }
        if ( bestDistance < 0.1 )
        {//if score is too low, the result might be too far from anything logical. Better return nothing not to trouble user.
            return null;//TODO: this is not the domain of the search. This should be filtered by the GUI. Refactor and return a tuple object (score/instance) and let the GUI take the decision.
        }
        else
        {
            return bestMatch;
        }
    }

    @Override
    public Map<String, Collection<SurfaceType>> reportSurfaceTypes()
    {
        // goal here is to list uniques per country.
        HashMap<String, Collection<SurfaceType>> result = new HashMap<>( this.countriesList.size() * 2 );
        int runwayCount = 0;
        long start = System.nanoTime();//for stats
        for ( int index = 0; index < this.countriesList.size(); index++ )
        {
            Country country = this.countriesList.get( index );
            ArrayList<SurfaceType> list = new ArrayList<>();
            result.put( country.getName(), list );
            // so i'll iterate each and every country
            Iterator<Airport> airportIterator = country.iterator();
            while ( airportIterator.hasNext() )
            {
                Airport airport = airportIterator.next();
                final Iterator<Runway> runwayIterator = airport.iterator();
                //and for each and every airport of the country,
                while ( runwayIterator.hasNext() )
                {
                    //i'll check each and every runway.
                    Runway runway = runwayIterator.next();
                    runwayCount++;
                    if ( false == list.contains( runway.getSurfaceType() ) )
                    {
                        //and if the surfacetype of this runway is not listed, i'll add it.
                        list.add( runway.getSurfaceType() );
                    }
                }
            }
        }
        double time = ( System.nanoTime() - start ) / 1000000.;
        System.err.println( String.format( "Managed %d runways in %.02fms (%.03f ms per item)", runwayCount, time, time / runwayCount ) );
        return result;
    }

    @Override
    public List<Country> sortByAirportCount()
    {
        //TODO: country list could be pre-sorted on this criteria after inits, so that further calls have no cost.
        Country[] array = this.countriesList.toArray( new Country[this.countriesList.size()] );
        long start = System.nanoTime();//for stats
        Arrays.sort( array, new Comparator<Object>()
        {

            @Override
            public int compare( Object o1, Object o2 )
            {
                Country c1 = (Country)o1;
                Country c2 = (Country)o2;
                return c2.getAirportCount() - c1.getAirportCount();
            }
        } );//TODO: refactor to lambda.
        double time = ( System.nanoTime() - start ) / 1000000.;
        System.err.println( String.format( "sorted %d countries in %.02fms (%.03f ms per item)", array.length, time, time / array.length ) );
        return Arrays.<Country>asList( array );
    }

    @Override
    public LatitudeScore[] sortByLattitude()
    {
        //here the goal is to count the occurences of each unique lattitude. (which is easy in SQL, a bit less by hand.)
        // One trick was to correctly analyse the latitudes so that they all came down to one way to compare them. Once done, it is much easier.
        // see https://en.wikipedia.org/wiki/Runway#Naming
        // i will first increment score on non-sorted array, so that it is easier and faster.
        LatitudeScore[] result = new LatitudeScore[36];
        for ( int index = 0; index < result.length; index++ )
        {
            result[index] = new LatitudeScore();
            result[index].setOrientation( ( index + 1 ) * 10 );
        }
        long start = System.nanoTime();//for stats
        int runwayCount = 0;//for stats
        for ( int airportIndex = 0; airportIndex < airportsList.size(); airportIndex++ )
        {
            // i'll get all airports, then for each compile orientation for each runway.
            Airport airport = airportsList.get( airportIndex );
            final Iterator<Runway> iterator = airport.iterator();
            while ( iterator.hasNext() )
            {
                Runway runway = iterator.next();
                int orientation = runway.getOrientation();
                if ( orientation > 0 )//ii will only add the valid orientations, omnidirectional ones too.
                {
                    runwayCount++;
                    result[( orientation / 10 ) - 1].increase();
                }
            }
        }
        //now, sorting the list.
        Arrays.sort( result );
        double time = ( System.nanoTime() - start ) / 1000000.;
        System.err.println( String.format( "sorted %d runways in %.02fms (%.03f ms per item)", runwayCount, time, time / runwayCount ) );
        return result;
    }

}
