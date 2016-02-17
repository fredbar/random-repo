/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lunatech.assessment.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.validator.routines.IntegerValidator;

/**
 *
 * @author Fred
 */
public class UptodateLunaModel implements LunaModel
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

    /**
     * List of runwaysof the model.
     */
    private ArrayList<Runway> runwayList = new ArrayList<>();

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
                        this.runwayList.add( runway );
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
        Iterable<Country> iterable = () -> this.countriesList.iterator();
        Stream<Country> stream = StreamSupport.stream( iterable.spliterator(), false );
//        Optional<String> max = stream.map( Country::getName ).max();
        return new Country( "test", "test" );
    }

    @Override
    public Map<String, Collection<SurfaceType>> reportSurfaceTypes()
    {
        //This one is a tricky.one..
//        Map<String, Map<Map<String, List<Runway>>, List<Airport>>> result = this.countriesList
//                .stream()
//                .collect(
//                        Collectors.toMap(
//                                Country::getName,
//                                p -> StreamSupport.stream(
//                                        Spliterators.spliteratorUnknownSize(
//                                                p.iterator(),//airport iterator.
//                                                Spliterator.ORDERED ),
//                                        false
//                                )
//                                .collect(
//                                        Collectors.groupingBy(
//                                                t -> StreamSupport.stream(
//                                                        Spliterators.spliteratorUnknownSize(
//                                                                t.iterator(),//runway iterator
//                                                                Spliterator.ORDERED
//                                                        ),
//                                                        false )
//                                                .filter( k -> k.getSurfaceType().getTypeName() )
//                                        )
//                                )
//                        )
//                )
//        )
//                );
        return new HashMap<>();
    }

    @Override
    public List<Country> sortByAirportCount()
    {
        return this.countriesList.stream().sorted( (s, t) -> t.getAirportCount() - s.getAirportCount() ).collect( Collectors.toList() );
    }

    @Override
    public LatitudeScore[] sortByLattitude()
    {
        long start = System.nanoTime();
        LatitudeScore[] toArray = this.runwayList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                p -> p.getOrientation()
                        )
                )
                .entrySet()
                .stream()
                .map(
                        t -> new LatitudeScore(
                                t.getKey(),
                                t.getValue().size()
                        )
                )
                .filter(
                        t -> t.getOrientation() >= 0
                )
                .sorted()
                .collect(
                        Collectors.toList()
                )
                .toArray( new LatitudeScore[0] );
        double time = ( System.nanoTime() - start ) / 1000000.;
        System.err.println( String.format( "sorted runways in %.02fms", time ) );
        return toArray;
    }

}
