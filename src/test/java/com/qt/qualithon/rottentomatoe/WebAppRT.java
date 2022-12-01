package com.qt.qualithon.rottentomatoe;
import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;
import com.qt.qualithon.rottentomatoe.HomePageRT;

/**
  entry class to hold RottenTomato Web Application UI Model/Page Objects
 **/
public class WebAppRT extends Page{

    public WebAppRT(TestSession testSession){
        super(testSession);
    }

    /**
      launch RottenTomato landing page in browser test session
     
      @return    RottenTomato Web Home Page page object
     **/
    public HomePageRT launch(){
        this.testSession.driver().get("https://www.rottentomatoes.com/");
        return new HomePageRT(this.testSession);
    }
}

