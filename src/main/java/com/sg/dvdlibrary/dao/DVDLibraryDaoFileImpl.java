/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author christopherwatnee
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    
    public static final String DVD_LIBRARY_FILE = "dvds.txt";
    public static final String DELIMITER = "::";
    private Map<String, DVD> dvds = new HashMap<>();
    
    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        DVD newDVD = dvds.put(title, dvd);
        return newDVD;
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryPersistenceException {
        DVD removedDVD = dvds.remove(title);
        return removedDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        dvds.remove(title);
        DVD editedDVD = dvds.put(dvd.getTitle(), dvd);
        return editedDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException {
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryPersistenceException {
        return dvds.get(title);
    }

    @Override
    public List<DVD> searchForDVD(String query) throws DVDLibraryPersistenceException {
        Set<String> keys = dvds.keySet();
        Map<String, DVD> dvdSearchResults = new HashMap<>();
        for (String k : keys) {
            if (k.contains(query)) {
                dvdSearchResults.put(k, dvds.get(k));
            }
        }
        return new ArrayList<DVD>(dvdSearchResults.values());
    }

    @Override
    public List<DVD> getDVDsReleasedInLastYears(int years) throws DVDLibraryPersistenceException {
        return dvds.values()
                .stream()
                .filter(d -> d.getAge() <= years)
                .collect(Collectors.toList());
    }

    @Override
    public List<DVD> getDVDsWithMPAARating(String mpaaRating) throws DVDLibraryPersistenceException {
        return dvds.values()
                .stream()
                .filter(d -> d.getMpaaRating().equalsIgnoreCase(mpaaRating))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<DVD>> getDVDsWithDirectorGroupByMPAARating(String directorsName) throws DVDLibraryPersistenceException {
        return dvds.values()
                .stream()
                .filter(d -> d.getDirectorsName().equalsIgnoreCase(directorsName))
                .collect(Collectors.groupingBy(DVD::getMpaaRating));
    }

    @Override
    public List<DVD> getDVDsReleasedByStudio(String studio) throws DVDLibraryPersistenceException {
        return dvds.values()
                .stream()
                .filter(d -> d.getStudio().equalsIgnoreCase(studio))
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageDVDAge() {
        return dvds.values()
                .stream()
                .mapToLong(DVD::getAge)
                .average()
                .getAsDouble();
    }

    @Override
    public DVD getNewestDVD() {
        return dvds.values()
                .stream()
                .min(Comparator.comparing(DVD::getAge))
                .get();
    }

    @Override
    public DVD getOldestDVD() {
        return dvds.values()
                .stream()
                .max(Comparator.comparing(DVD::getAge))
                .get();
    }

    @Override
    public double getAverageNumberOfNotes() {
        return dvds.values()
                .stream()
                .mapToInt(d -> d.getNumberOfNotes())
                .average()
                .getAsDouble();
    }
    
    @Override
    public void loadDVDLibrary() throws DVDLibraryPersistenceException {
        Scanner scanner;
        
        try {
            // Create Scanner for reading the dvd library file
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException("Could not load dvd library data into memory.", e);
        }
        
        String currentLine;
        String[] currentTokens;
        
        // Loop through all the lines in the dvd library file
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            // Break line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create dvd based on tokens
            DVD currentDVD = new DVD(currentTokens[0]);
            String releaseDate = currentTokens[1];
            LocalDate releaseDateLocalDate = LocalDate.parse(releaseDate);
            currentDVD.setReleaseDate(releaseDateLocalDate);
            currentDVD.setMpaaRating(currentTokens[2]);
            currentDVD.setDirectorsName(currentTokens[3]);
            currentDVD.setStudio(currentTokens[4]);
            if (currentTokens.length == 6) {
                currentDVD.setNotes(currentTokens[5]);
            } else {
                currentDVD.setNotes("");
            }
            // Put dvd into map
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // Close scanner
        scanner.close();
    }
    
     @Override
    public void writeDVDLibrary() throws DVDLibraryPersistenceException {
        PrintWriter out;
        
        try {
            // Create PrintWriter for writing to the dvd library file
            out = new PrintWriter(new FileWriter(DVD_LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException("Could not save dvd library data.", e);
        }
        List<DVD> dvdList = this.getAllDVDs();
        // Loop through all dvds
        for (DVD currentDVD : dvdList) {
            // Write dvd to the file
            out.println(currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER
                    + currentDVD.getMpaaRating() + DELIMITER
                    + currentDVD.getDirectorsName() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getNotes());
            // Force line to be writen to file
            out.flush();
        }
        // Close stream
        out.close();
    }
}
