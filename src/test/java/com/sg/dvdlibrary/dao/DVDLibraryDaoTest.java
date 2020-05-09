/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author christopherwatnee
 */
public class DVDLibraryDaoTest {
    
    private DVDLibraryDao dao;
    
    public DVDLibraryDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("dvdLibraryDao", DVDLibraryDao.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<DVD> dvdList = dao.getAllDVDs();
        for(DVD dvd : dvdList) {
            dao.removeDVD(dvd.getTitle());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testAddGetDVD() throws Exception {
        DVD dvd = new DVD("The Lion King");
        LocalDate releaseDateLocalDate = LocalDate.parse("06/15/1994", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dvd.setReleaseDate(releaseDateLocalDate);
        dvd.setMpaaRating("G");
        dvd.setDirectorsName("Roger Allers");
        dvd.setStudio("Walt Disney Pictures");
        dvd.setNotes("8.5/10");
        
        dao.addDVD(dvd.getTitle(), dvd);
        
        DVD fromDao = dao.getDVD(dvd.getTitle());
        
        assertEquals(dvd, fromDao);
    }

    /**
     * Test of removeDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testRemoveDVD() throws Exception {
        DVD dvd1 = new DVD("The Lion King");
        LocalDate releaseDateLocalDate1 = LocalDate.parse("06/15/1994", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dvd1.setReleaseDate(releaseDateLocalDate1);
        dvd1.setMpaaRating("G");
        dvd1.setDirectorsName("Roger Allers");
        dvd1.setStudio("Walt Disney Pictures");
        dvd1.setNotes("8.5/10");
        
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD("Jurassic Park");
        LocalDate releaseDateLocalDate2 = LocalDate.parse("06/11/1993", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dvd2.setReleaseDate(releaseDateLocalDate2);
        dvd2.setMpaaRating("PG-13");
        dvd2.setDirectorsName("Steven Spielberg");
        dvd2.setStudio("Universal Pictures");
        dvd2.setNotes("8.1/10");
        
        dao.addDVD(dvd2.getTitle(), dvd2);
        
        dao.removeDVD(dvd1.getTitle());
        assertEquals(1, dao.getAllDVDs().size());
        assertNull(dao.getDVD(dvd1.getTitle()));
        
        dao.removeDVD(dvd2.getTitle());
        assertEquals(0, dao.getAllDVDs().size());
        assertNull(dao.getDVD(dvd2.getTitle()));
    }

    /**
     * Test of editDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testEditDVD() throws Exception {
        DVD dvd1 = new DVD("The Lion King");
        LocalDate releaseDateLocalDate1 = LocalDate.parse("06/15/1994", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dvd1.setReleaseDate(releaseDateLocalDate1);
        dvd1.setMpaaRating("G");
        dvd1.setDirectorsName("Roger Allers");
        dvd1.setStudio("Walt Disney Pictures");
        dvd1.setNotes("8.5/10");
        
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD(dvd1.getTitle());
        LocalDate releaseDateLocalDate2 = LocalDate.parse("07/19/2019", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dvd2.setReleaseDate(releaseDateLocalDate2);
        dvd2.setMpaaRating("PG");
        dvd2.setDirectorsName("Jon Favreau");
        dvd2.setStudio("Walt Disney Pictures");
        dvd2.setNotes("Not yet released");
        
        dao.editDVD(dvd1.getTitle(), dvd2);
        DVD fromDao = dao.getDVD(dvd1.getTitle());
        
        assertEquals(dvd2, fromDao);
    }

    /**
     * Test of getAllDVDs method, of class DVDLibraryDao.
     */
    @Test
    public void testGetAllDVDs() throws Exception {
        DVD dvd1 = new DVD("The Lion King");
        LocalDate releaseDateLocalDate1 = LocalDate.parse("06/15/1994", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dvd1.setReleaseDate(releaseDateLocalDate1);
        dvd1.setMpaaRating("G");
        dvd1.setDirectorsName("Roger Allers");
        dvd1.setStudio("Walt Disney Pictures");
        dvd1.setNotes("8.5/10");
        
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD("Jurassic Park");
        LocalDate releaseDateLocalDate2 = LocalDate.parse("06/11/1993", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dvd2.setReleaseDate(releaseDateLocalDate2);
        dvd2.setMpaaRating("PG-13");
        dvd2.setDirectorsName("Steven Spielberg");
        dvd2.setStudio("Universal Pictures");
        dvd2.setNotes("8.1/10");
        
        dao.addDVD(dvd2.getTitle(), dvd2);
        
        assertEquals(2, dao.getAllDVDs().size());
    }

    /**
     * Test of searchForDVD method, of class DVDLibraryDao.
     */
    @Test
    public void testSearchForDVD() throws Exception {
        DVD dvd1 = new DVD("The Lion King");
        LocalDate releaseDateLocalDate1 = LocalDate.parse("06/15/1994", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dvd1.setReleaseDate(releaseDateLocalDate1);
        dvd1.setMpaaRating("G");
        dvd1.setDirectorsName("Roger Allers");
        dvd1.setStudio("Walt Disney Pictures");
        dvd1.setNotes("8.5/10");
        
        dao.addDVD(dvd1.getTitle(), dvd1);
        
        DVD dvd2 = new DVD("Jurassic Park");
        LocalDate releaseDateLocalDate2 = LocalDate.parse("06/11/1993", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        dvd2.setReleaseDate(releaseDateLocalDate2);
        dvd2.setMpaaRating("PG-13");
        dvd2.setDirectorsName("Steven Spielberg");
        dvd2.setStudio("Universal Pictures");
        dvd2.setNotes("8.1/10");
        
        dao.addDVD(dvd2.getTitle(), dvd2);
        
        assertEquals(1, dao.searchForDVD(dvd1.getTitle()).size());
        assertEquals(2, dao.searchForDVD("i").size());
        assertEquals(0, dao.searchForDVD("Star Wars").size());
    }
    
}
