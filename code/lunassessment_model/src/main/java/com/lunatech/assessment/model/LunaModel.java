package com.lunatech.assessment.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Hierarchical model for country/airport/runway system.
 * Also holds the methods to query the model, but only in limited and
 * task-oriented ways.
 * <br>
 * <br>
 * NOTE: Model is empty at first, one needs to fill it first from CSV streams.
 * Model can be filled multiple times with different file sets but does not
 * offer other writing method, and neither does it filters duplicates during
 * imports.
 * Model will have multiple implementations thus is defined as an interface.
 *
 * @author Frederic Barachant
 */
public interface LunaModel
{

    /**
     * Fills the model with data coming from CSV files.
     * * CSV files contain more fields than needed for the assessment. Only
     * those
     * needed will be used or imported, namely:
     * <br>
     * <br>
     * Countries:
     * <ul>
     * <li><code>code</code> (iso-3166 like) (imported)</li>
     * <li><code>name</code> (iso-3166 like) (imported)</li>
     * </ul>
     *
     * <br>
     * Airports:
     * <ul>
     * <li><code>ident</code> so that runways can find their airport (used)</li>
     * <li><code>iso_country</code> so that we can link back to the country.
     * (ref
     * country.code) </li>
     * </ul>
     *
     * <br>
     * Runways:
     * <ul>
     * <li><code>airport-ident</code> to get the airport reference correctly
     * (used)</li>
     * <li><code>ie-ident</code>, for latitude filtering (imported)</li>
     * <li><code>surface</code>, for hall of fame (imported to a surface
     * factory)</li>
     * <li><code>length_ft</code>, as a bonus data, but unuseful (imported)</li>
     * </ul>
     *
     *
     * @see com.lunatech.assessment.model.Airport
     * @see com.lunatech.assessment.model.Country
     * @see com.lunatech.assessment.model.Runway
     * @see
     * <a href="http://data.okfn.org/data/core/country-list#resource-data">ISO
     * country codes</a>
     * @param countries List of countries, that must hold the minimal columns
     * stated in this method documentation.
     * @param airports List of airports, that must hold the minimal columns
     * stated in this method documentation.
     * @param runways List of runways, that must hold the minimal columns stated
     * in this method documentation.
     *
     * @throws IOException
     */
    public void fillModel( final InputStreamReader countries, final InputStreamReader airports, final InputStreamReader runways ) throws IOException;

    /**
     * Tries to find a Country that matches the given name.
     * Will search for similarities in the name and country code of the current
     * country list and return the one that is seen as the closest.
     * If similarity score is too low, no match will be returned. Score
     * boundaries are not mutable and depend on implementation.
     *
     * @param name the name to be matched against the model.
     *
     * @return a <code>Country</code> object in case of a valid result,
     * <code>null</code> otherwise.
     */
    public Country guessCountry( final String name );

    /**
     * Lists all countries, sorted by the airport count.
     *
     * @return A collection of countries, ordered from highest to lowest airport
     * count.
     */
    public List<Country> sortByAirportCount();

    /**
     * Reports all surface types per country.
     * Lists all countries. No sorting.
     *
     * @return a Map where key is the country and value is a Collection of
     * SurfaceType in that country.
     */
    public Map<String, Collection<SurfaceType>> reportSurfaceTypes();

    /**
     * Counts occurences of lattitudes and returns an array of 36 integers.
     * Each entry in the array represents the count at each possible
     * orientation, starting at 10 degrees and ending at 360.
     *
     * @return an array representing the count for each and every possible 36
     * cardinal direction.
     */
    public LatitudeScore[] sortByLattitude();

}
