/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;
import java.util.Map;

/**
 *
 * @author christopherwatnee
 */
public class DVDLibraryController {
    
    private UserIO io = new UserIOConsoleImpl();
    
    DVDLibraryView view;
    DVDLibraryServiceLayer service;

    public DVDLibraryController(DVDLibraryServiceLayer service, DVDLibraryView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        boolean keepRunning = true;
        int menuSelection = 0;
        try {
            service.loadDVDLibrary();
            while (keepRunning) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        createDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        viewDVD();
                        break;
                    case 6:
                        searchForDVD();
                        break;
                    case 7:
                        findDVDsReleasedInLastYears();
                        break;
                    case 8:
                        findDVDsWithMPAARating();
                        break;
                    case 9:
                        findDVDsWithDirectorGroupByMPAARating();
                        break;
                    case 10:
                        findDVDsReleasedByStudio();
                        break;
                    case 11:
                        findAverageDVDAge();
                        break;
                    case 12:
                        findNewestDVD();
                        break;
                    case 13:
                        findOldestDVD();
                        break;
                    case 14:
                        findAverageNumberOfNotes();
                        break;
                    case 15:
                        keepRunning = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
            service.writeDVDLibrary();
        } catch (DVDLibraryPersistenceException e) {
            errorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createDVD() throws DVDLibraryPersistenceException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInformation();
        service.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }
    
    private void removeDVD() throws DVDLibraryPersistenceException {
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitleChoice();
        DVD dvd = service.getDVD(title);
        service.removeDVD(title);
        view.displayRemoveSuccessBanner(dvd);
    }
    
    private void editDVD() throws DVDLibraryPersistenceException {
        String originalDVDTitle = view.getDVDTitleChoice();
        DVD originalDVD = service.getDVD(originalDVDTitle);
        if (originalDVD != null) {
            int editMenuSelection = view.printEditDVDMenuAndGetSelection();
            DVD editedDVD = view.getEditDVDInformation(originalDVD, editMenuSelection);
            service.editDVD(originalDVDTitle, editedDVD);
        }
        view.displayEditSuccessBanner(originalDVD);
    }
    
    private void listDVDs() throws DVDLibraryPersistenceException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = service.getAllDVDs();
        view.displayDVDList(dvdList);
        view.displayPressEnter();
    }
    
    private void viewDVD() throws DVDLibraryPersistenceException {
        view.displayDisplayDVDBanner();
        String title = view.getDVDTitleChoice();
        DVD dvd = service.getDVD(title);
        view.displayDVD(dvd);
        view.displayPressEnter();
    }
    
    private void searchForDVD() throws DVDLibraryPersistenceException {
        view.displaySearchForDVDBanner();
        String query = view.getDVDSearchQuery();
        List<DVD> dvdSearchResultsList = service.searchForDVD(query);
        view.displayDVDList(dvdSearchResultsList);
        view.displayPressEnter();
    }
    
    private void findDVDsReleasedInLastYears() throws DVDLibraryPersistenceException {
        view.displayFindDVDsReleasedInLastYearsBanner();
        int years = view.getNumberOfYearsChoice();
        List<DVD> dvdSearchResultsList = service.getDVDsReleasedInLastYears(years);
        view.displayDVDList(dvdSearchResultsList);
        view.displayPressEnter();
    }
    
    private void findDVDsWithMPAARating() throws DVDLibraryPersistenceException {
        view.displayFindDVDsWithMPAARatingBanner();
        String mpaaRating = view.getMpaaRatingChoice();
        List<DVD> dvdSearchResultsList = service.getDVDsWithMPAARating(mpaaRating);
        view.displayDVDList(dvdSearchResultsList);
        view.displayPressEnter();
    }
    
    private void findDVDsWithDirectorGroupByMPAARating() throws DVDLibraryPersistenceException {
        view.displayFindDVDsWithDirectorGroupByMPAARatingBanner();
        String directorsName = view.getDirectorsNameChoice();
        Map<String, List<DVD>> dvdSearchResultsMap = service.getDVDsWithDirectorGroupByMPAARating(directorsName);
        view.displayDVDMapGroupByMPAARating(dvdSearchResultsMap);
        view.displayPressEnter();
    }
    
    private void findDVDsReleasedByStudio() throws DVDLibraryPersistenceException {
        view.displayFindDVDsReleasedByStudioBanner();
        String studio = view.getStudioChoice();
        List<DVD> dvdSearchResultsList = service.getDVDsReleasedByStudio(studio);
        view.displayDVDList(dvdSearchResultsList);
        view.displayPressEnter();
    }
    
    private void findAverageDVDAge() throws DVDLibraryPersistenceException {
        view.displayFindAverageDVDAgeBanner();
        double averageDVDAge = service.getAverageDVDAge();
        view.displayAverageDVDAge(averageDVDAge);
        view.displayPressEnter();
    }
    
    private void findNewestDVD() throws DVDLibraryPersistenceException {
        view.displayFindNewestDVDBanner();
        DVD dvd = service.getNewestDVD();
        view.displayDVD(dvd);
        view.displayPressEnter();
    }
    
    private void findOldestDVD() throws DVDLibraryPersistenceException {
        view.displayFindOldestDVDBanner();
        DVD dvd = service.getOldestDVD();
        view.displayDVD(dvd);
        view.displayPressEnter();
    }
    
    private void findAverageNumberOfNotes() throws DVDLibraryPersistenceException {
        view.displayFindAverageNumberOfNotesBanner();
        double averageNumberOfNotes = service.getAverageNumberOfNotes();
        view.displayAverageNumberOfNotes(averageNumberOfNotes);
        view.displayPressEnter();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void errorMessage(String message) {
        view.displayErrorMessage(message);
    }
}
