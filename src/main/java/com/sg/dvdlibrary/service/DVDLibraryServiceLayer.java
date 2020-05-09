/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author christopherwatnee
 */
public interface DVDLibraryServiceLayer {
    
    void addDVD(String title, DVD dvd) throws DVDLibraryPersistenceException;

    void removeDVD(String title) throws DVDLibraryPersistenceException;

    void editDVD(String title, DVD dvd) throws DVDLibraryPersistenceException;

    List<DVD> getAllDVDs() throws DVDLibraryPersistenceException;

    DVD getDVD(String title) throws DVDLibraryPersistenceException;

    List<DVD> searchForDVD(String query) throws DVDLibraryPersistenceException;

    List<DVD> getDVDsReleasedInLastYears(int ageInYears) throws DVDLibraryPersistenceException;
    
    List<DVD> getDVDsWithMPAARating(String mpaaRating) throws DVDLibraryPersistenceException;
    
    Map<String, List<DVD>> getDVDsWithDirectorGroupByMPAARating(String directorsName) throws DVDLibraryPersistenceException;
    
    List<DVD> getDVDsReleasedByStudio(String studio) throws DVDLibraryPersistenceException;
     
    double getAverageDVDAge();
    
    DVD getNewestDVD();
    
    DVD getOldestDVD();
    
    double getAverageNumberOfNotes();
    
    public void loadDVDLibrary() throws DVDLibraryPersistenceException;
    
    public void writeDVDLibrary() throws DVDLibraryPersistenceException;
}
