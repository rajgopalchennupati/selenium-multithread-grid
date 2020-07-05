package tourtests;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import tourpages.SearchPage;
import tourpages.ToursTestBase;

@Test
public class SearchTest extends ToursTestBase {

    
    @Parameters({"keyword"})
    public void search(String keyword){
        SearchPage searchPage = new SearchPage();
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        int size = searchPage.getResult();

        Assert.assertTrue(size > 0);
    }
    
    
    
    @Parameters({"keyword"})
    public void search2(String keyword){
        SearchPage searchPage = new SearchPage();
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        int size = searchPage.getResult();

        Assert.assertTrue(size > 0);
    }


}
