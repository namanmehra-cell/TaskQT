package com.qt.qualithon.ui.imdb;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;

/**
 * page object class represents elements and actions on the IMDb Movie Page
 **/
public class MoviePage extends Page{

    public MoviePage(TestSession testSession){
        super(testSession);

        // adjust page for tablet formfactor
        WebElement showMoreLink = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfElementLocated(
              By.xpath("//button[@data-testid='title-pc-expand-more-button']")));
       
        if(showMoreLink.isDisplayed()){
            showMoreLink.click();
        }

    }

    /**
     * get movie title
     *
     * @return    movie title
     **/
    public String title(){
        return this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1[data-testid='hero-title-block__title']")
            ) 
        ).getText();
    }
    
    /**
     * get movie rating
     * 
     * @return movie rating
    **/
    public String rating() {
        return this.testSession.driverWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@data-testid='hero-rating-bar__aggregate-rating__score']//span[1]")
                ) 
            ).getText();
    } 
    
    /**
     * get movie maturity rating
     * 
     * @return movie maturity rating
    **/
    public String maturityRating() {
        return this.testSession.driverWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[class='sc-80d4314-0 fjPRnj'] li:nth-child(2) a:nth-child(1)")
                ) 
            ).getText();
    } 
    /**
     * get movie director name
     *
     * @return    movie director name
     **/
    public String director(){
        List<WebElement> credits = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
              By.xpath("//li[@class='ipc-metadata-list__item']")));

        // traverse credits sections to find the section with Directors
        for(WebElement credit:credits){
            try{
                if(credit.findElement(By.tagName("button")).getText().equalsIgnoreCase("Director")){
                    // find director name from child element of section
                    return credit.findElement(By.tagName("a")).getText();
                }
            }catch(NoSuchElementException e){System.out.println("No director Found");}
        }
        throw new NoSuchElementException("Failed to lookup Director on page");
    }

    /**
     * get list of movie genres
     *
     * @return    list of movie genres
     **/
    public List<String> genres(){ 
        List<String> genres = new ArrayList<>();
        List<WebElement> genress = this.testSession.driverWait().until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                  By.xpath("(//div[@class=\"ipc-chip-list__scroller\"])[1]")));
        for(WebElement c:genress) {
            try{
                    // traverse list of writers on page to add to writers list
                    List<WebElement> gElements = c.findElements(By.cssSelector("a"));
                    for(int i = 0; i < gElements.size() ; i++){
                        genres.add(gElements.get(i).getText());
                    }
                    break;
                    }
                    catch(NoSuchElementException e){}
                    
        }
        
        
        // if genres list is empty throw exception
        if(genres.isEmpty()){
            throw new NoSuchElementException("Could not lookup genres on Movie page");
        }
        return genres;
    }
    
    /**
     * get movie release year
     *
     * @return    movie release year
     **/
    public String releaseYear(){
        return this.testSession.driverWait().until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//ul[@data-testid='hero-title-block__metadata']//li[1]")
            ) 
        ).getText();
    }

    /**
     * get list of movie writers
     *
     * @return    list of movie writer names
     **/
    public List<String> writers(){
        List<String> writers = new ArrayList<>();
        List<WebElement> credits = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
              By.xpath("(//div[@class=\"ipc-metadata-list-item__content-container\"])[2]")));

        // traverse credits sections to find the section with Writers
        for(WebElement credit:credits){
            try{
                //if(credit.findElement(By.cssSelector("button")).getText().equalsIgnoreCase("Writers")){
                    // traverse list of writers on page to add to writers list
                    List<WebElement> writersElements = credit.findElements(By.cssSelector("a"));
                    for(int i = 0; i < writersElements.size() ; i++){
                        writers.add(writersElements.get(i).getText());
                  //  }
                    
                }
                    break;
            }catch(NoSuchElementException e){}
        }

        // if writers list is empty throw exception
        if(writers.isEmpty()){
            throw new NoSuchElementException("Could not lookup Writers on movie page");
        }
        return writers;
    }

}
