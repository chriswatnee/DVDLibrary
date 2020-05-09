/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author christopherwatnee
 */
public class DVDLibraryView {
    
    private UserIO io;
    
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("*** DVD Library Menu ***");
        io.print(" 1. Add DVD to collection");
        io.print(" 2. Remove DVD from collection");
        io.print(" 3. Edit the information for an existing DVD");
        io.print(" 4. List the DVDs in collection");
        io.print(" 5. Display information for a DVD");
        io.print(" 6. Search for DVD by title");
        io.print(" 7. Find all DVDs released within the last number of years");
        io.print(" 8. Find all DVDs with a given MPAA rating");
        io.print(" 9. Find all DVDs by a given director");
        io.print("10. Find all the DVDs released by a particular studio");
        io.print("11. Find the average age of the DVDs in collection");
        io.print("12. Find newest DVD collection");
        io.print("13. Find oldest DVD collection");
        io.print("14. Find the average number of notes in collection");
        io.print("15. Exit");

        return io.readInt("Please select from the above choices.", 1, 15);
    }
    
    public void displayCreateDVDBanner() {
        io.print("*** Add DVD to Collection ***");
    }
    
    public DVD getNewDVDInformation() {
        String title = io.readString("Title:");
        String releaseDate = io.readString("Release date:");
        LocalDate releaseDateLocalDate = LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String mpaaRating = io.readString("MPAA rating:");
        String directorsName = io.readString("Director's name:");
        String studio = io.readString("Studio:");
        String userRating = io.readString("Notes:");
        DVD newDVD = new DVD(title);
        newDVD.setReleaseDate(releaseDateLocalDate);
        newDVD.setMpaaRating(mpaaRating);
        newDVD.setDirectorsName(directorsName);
        newDVD.setStudio(studio);
        newDVD.setNotes(userRating);
        return newDVD;
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully added to collection. Press enter to return to main menu.");
    }
    
    public void displayRemoveDVDBanner() {
        io.print("*** Remove DVD from Collection ***");
    }
    
    public String getDVDTitleChoice() {
        return io.readString("DVD title:");
    }
    
    public void displayRemoveSuccessBanner(DVD dvd) {
        if (dvd != null) {
            io.readString("DVD successfully removed from collection. Press enter to return to main menu.");
        } else {
            io.readString("DVD not removed from collection. Press enter to return to main menu.");
        }
    }
    
    public int printEditDVDMenuAndGetSelection() {
        io.print("*** Edit the Information for an Existing DVD Menu ***");
        io.print("1. Edit title");
        io.print("2. Edit release date");
        io.print("3. Edit MPAA rating");
        io.print("4. Edit director's name");
        io.print("5. Edit studio");
        io.print("6. Edit notes");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public DVD getEditDVDInformation(DVD originalDVD, int field) {
        String editedDVDTitle;
        // Set title String
        if (field == 1) {
            editedDVDTitle = io.readString("Title:");
        } else {
            editedDVDTitle = originalDVD.getTitle();
        }
        // Created edited DVD
        DVD editedDVD = new DVD(editedDVDTitle);
        // Set default values
        editedDVD.setReleaseDate(originalDVD.getReleaseDate());
        editedDVD.setMpaaRating(originalDVD.getMpaaRating());
        editedDVD.setDirectorsName(originalDVD.getDirectorsName());
        editedDVD.setStudio(originalDVD.getStudio());
        editedDVD.setNotes(originalDVD.getNotes());
        // Update applicable field
        switch (field) {
            case 2:
                String releaseDate = io.readString("Release date:");
                LocalDate releaseDateLocalDate = LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                editedDVD.setReleaseDate(releaseDateLocalDate);
                break;
            case 3:
                String mpaaRating = io.readString("MPAA rating:");
                editedDVD.setMpaaRating(mpaaRating);
                break;
            case 4:
                String directorsName = io.readString("Director's name:");
                editedDVD.setDirectorsName(directorsName);
                break;
            case 5:
                String studio = io.readString("Studio:");
                editedDVD.setStudio(studio);
                break;
            case 6:
                String userRating = io.readString("User rating or note:");
                editedDVD.setNotes(userRating);
                break;
        }
        // Return edited DVD
        return editedDVD;
    }
    
    public void displayEditSuccessBanner(DVD dvd) {
        if (dvd != null) {
            io.readString("DVD successfully edited. Press enter to return to main menu.");
        } else {
            io.readString("DVD does not exist. Press enter to return to main menu.");
        }
    }
    
    public void displayDisplayAllBanner() {
        io.print("*** List the DVDs in the Collection ***");
    }
    
    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            io.print("\n" + currentDVD.getTitle());
            LocalDate releaseDate = currentDVD.getReleaseDate();
            String releaseDateString = releaseDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            io.print("Release date: " + releaseDateString);
            io.print("MPAA rating: " + currentDVD.getMpaaRating());
            io.print("Director's name: " + currentDVD.getDirectorsName());
            io.print("Studio: " + currentDVD.getStudio());
            io.print("User rating or note: " + currentDVD.getNotes() + "\n");
        }
    }
    
    public void displayPressEnter() {
        io.readString("Press enter to return to main menu.");
    }
    
    public void displayDisplayDVDBanner() {
        io.print("*** Display information for a DVD ***");
    }
    
    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print("\n" + dvd.getTitle());
            LocalDate releaseDate = dvd.getReleaseDate();
            String releaseDateString = releaseDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            io.print("Release date: " + releaseDateString);
            io.print("MPAA rating: " + dvd.getMpaaRating());
            io.print("Director's name: " + dvd.getDirectorsName());
            io.print("Studio: " + dvd.getStudio());
            io.print("User rating or note: " + dvd.getNotes() + "\n");
        } else {
            io.print("Cannot find dvd.");
        }
    }
    
    public void displaySearchForDVDBanner() {
        io.print("*** Search for DVD by Title ***");
    }
    
    public String getDVDSearchQuery() {
        return io.readString("Search:");
    }
    
    public void displayFindDVDsReleasedInLastYearsBanner() {
        io.print("*** Find all DVDs released within the last number of years ***");
    }
    
    public int getNumberOfYearsChoice() {
        return io.readInt("Years:");
    }
    
    public void displayFindDVDsWithMPAARatingBanner() {
        io.print("*** Find all DVDs with a given MPAA rating ***");
    }
    
    public String getMpaaRatingChoice() {
        return io.readString("MPAA rating:");
    }
    
    public void displayFindDVDsWithDirectorGroupByMPAARatingBanner() {
        io.print("*** Find all DVDs by a given director ***");
    }
    
    public void displayDVDMapGroupByMPAARating(Map<String, List<DVD>> dvdSearchResultsMap) {
        Set<String> mpaaRatings = dvdSearchResultsMap.keySet();
        
        mpaaRatings.stream()
                .forEach(rating -> {
                    io.print("================================");
                    io.print("\"MPAA rating: " + rating);
                    dvdSearchResultsMap.get(rating).stream().forEach(d -> io.print(d.getTitle()));
                });
    }
    
    public String getDirectorsNameChoice() {
        return io.readString("Director's name:");
    }
    
    public void displayFindDVDsReleasedByStudioBanner() {
        io.print("*** Find all the DVDs released by a particular studio ***");
    }
    
    public String getStudioChoice() {
        return io.readString("Studio:");
    }
    
    public void displayFindAverageDVDAgeBanner() {
        io.print("*** Find the average age of the DVDs in collection ***");
    }
    
    public void displayAverageDVDAge(double averageDVDAge) {
        io.print("The average age of the DVDs in collection is " + averageDVDAge + " years old.");
    }
    
    public void displayFindNewestDVDBanner() {
        io.print("*** Find newest DVD collection ***");
    }
    
    public void displayFindOldestDVDBanner() {
        io.print("*** Find oldest DVD collection ***");
    }
    
    public void displayFindAverageNumberOfNotesBanner() {
        io.print("*** Find the average number of notes in collection ***");
    }
    
    public void displayAverageNumberOfNotes(double averageNumberOfNotes) {
        io.print("The average number of notes associated with movies in your collection is " + averageNumberOfNotes + " notes.");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }
    
    public void displayExitBanner() {
        io.print("Thank you!");
    }
    
    public void displayErrorMessage(String message) {
        io.print("Error: " + message);
    }
}
