package com.qt.qualithon.rottentomatoe;
import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

/**
 * page object class represents elements and actions on the IMDb Movie Page
 **/
public class MoviePageRT extends Page{

    public MoviePageRT(TestSession testSession){
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
                By.xpath("//h1[@slot=\"title\"]")
            ) 
        ).getText();
    }
    
    /**
     * get movie rating
     * 
     * @return movie rating
    **/
//    public String rating() {
//    	WebElement root = this.testSession.driverWait().until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        By.xpath("//score-board[@tomatometerstate='certified-fresh']")
//                    ));
////    	JavascriptExecutor js = (javascriptExecutor)driver();
////    	WebElement shadowDom1=(WebElement)js.executeScript("return argument[0].shadowRoot",root);
////    	return shadowDom1.findElement(By.cssSelector(".percentage").getText();
//    } 
    
    /**
     * get movie maturity rating
     * 
     * @return movie maturity rating
    **/
    public String maturityRating() {
        return this.testSession.driverWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("section[class='panel panel-rt panel-box movie_info media'] li:nth-child(1) div:nth-child(2)")
                ) 
            ).getText().substring(0, 5);
    } 
    /**
     * get movie director name
     *
     * @return    movie director name
     **/
    public String director(){
        return this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//a[@data-qa='movie-info-director']"))).getText();
    }

    /**
     * get list of movie genres
     *
     * @return    list of movie genres
     **/
    public String genres(){ 
        
        return  this.testSession.driverWait().until(
                ExpectedConditions.presenceOfElementLocated(
                  By.xpath("(//li[@class=\"meta-row clearfix\"])[2]//div[2]"))).getText();
      
          }
    
    /**
     * get movie release year
     *
     * @return    movie release year
     **/
    public String releaseYear(){
        return this.testSession.driverWait().until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//li[@class=\"meta-row clearfix\"])[7]//time")
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
        WebElement credits = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfElementLocated(
              By.xpath("(//li[@class=\"meta-row clearfix\"])[6]")));

        // traverse credits sections to find the section with Writers
      
            try{
            		// traverse list of writers on page to add to writers list
                    List<WebElement> writersElements = credits.findElements(By.cssSelector("a"));
                    for(int i = 0; i < writersElements.size() ; i++){
                        writers.add(writersElements.get(i).getText());
                        }
            }
            catch(NoSuchElementException e){}
     

        // if writers list is empty throw exception
        if(writers.isEmpty()){
            throw new NoSuchElementException("Could not lookup Writers on movie page");
        }
        return writers;
    }

}

