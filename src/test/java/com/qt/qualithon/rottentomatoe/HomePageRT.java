package com.qt.qualithon.rottentomatoe;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

/**
 * represents IMDb Web Home Page elements and ui actions (page object)
 **/
public class HomePageRT extends Page {

    public HomePageRT(TestSession testSession){
        super(testSession);
    }

    /**
     * perform a search for movie title and return the resultlist page
     *
     * @param     movieTitle    movie name
     * @return    IMDb Results Page page object
     **/
    public ResultsPageRT search(String movieTitle){
        WebElement searchInput = this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@class=\"search-text\"]")
            )
        );
        searchInput.sendKeys(movieTitle);
        searchInput.submit();

        return new ResultsPageRT(this.testSession);
    }
}
