/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author christopherwatnee
 */
public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {
    
    private DVDLibraryDao dao;
    
    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao) {
        this.dao = dao;
    }

    @Override
    public void addDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        dao.addDVD(title, dvd);
    }

    @Override
    public void removeDVD(String title) throws DVDLibraryPersistenceException {
        dao.removeDVD(title);
    }

    @Override
    public void editDVD(String title, DVD dvd) throws DVDLibraryPersistenceException {
        dao.editDVD(title, dvd);
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException {
        return dao.getAllDVDs();
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryPersistenceException {
        return dao.getDVD(title);
    }

    @Override
    public List<DVD> searchForDVD(String query) throws DVDLibraryPersistenceException {
        return dao.searchForDVD(query);
    }

    @Override
    public List<DVD> getDVDsReleasedInLastYears(int ageInYears) throws DVDLibraryPersistenceException {
        return dao.getDVDsReleasedInLastYears(ageInYears);
    }

    @Override
    public List<DVD> getDVDsWithMPAARating(String mpaaRating) throws DVDLibraryPersistenceException {
        return dao.getDVDsWithMPAARating(mpaaRating);
    }

    @Override
    public Map<String, List<DVD>> getDVDsWithDirectorGroupByMPAARating(String directorsName) throws DVDLibraryPersistenceException {
        return dao.getDVDsWithDirectorGroupByMPAARating(directorsName);
    }

    @Override
    public List<DVD> getDVDsReleasedByStudio(String studio) throws DVDLibraryPersistenceException {
        return dao.getDVDsReleasedByStudio(studio);
    }

    @Override
    public double getAverageDVDAge() {
        return dao.getAverageDVDAge();
    }

    @Override
    public DVD getNewestDVD() {
        return dao.getNewestDVD();
    }

    @Override
    public DVD getOldestDVD() {
        return dao.getOldestDVD();
    }

    @Override
    public double getAverageNumberOfNotes() {
        return dao.getAverageNumberOfNotes();
    }

    @Override
    public void loadDVDLibrary() throws DVDLibraryPersistenceException {
        dao.loadDVDLibrary();
    }

    @Override
    public void writeDVDLibrary() throws DVDLibraryPersistenceException {
        dao.writeDVDLibrary();
    }
    
}
