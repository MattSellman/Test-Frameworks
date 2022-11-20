package stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.objects.CucumberDataTables.Book;
import utils.objects.CucumberDataTables.BookStore;
import utils.objects.CucumberDataTables.Magazine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CucumberTransformsSteps {

    private BookStore store;
    private List<Book> foundBooks;

    @Before
    public void setUp() {
        store = new BookStore();
        foundBooks = new ArrayList<>();
    }

    @Given("^I have the following books in the store with an arrayList$")
    public void haveBooksInTheStoreByList(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);

        for (List<String> columns : rows) {
            store.addBook(new Book(columns.get(0), columns.get(1)));
        }
    }

    @Given("^I have the following books in the store with a Map$")
    public void haveBooksInTheStoreByMap(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            store.addBook(new Book(columns.get("title"), columns.get("author")));
        }
    }

    @Given("^I have the following books in the store with a Table Transformer$")
    public void haveBooksInTheStoreByTableTransform(DataTable table) {
        List<Magazine> magazineList = table.asList(Magazine.class);

        for (Magazine magazine : magazineList) {
            System.out.println(magazine);
        }

        // asList() creates a List of magazine objects.
    }

    @When("^I search for books by author \"([^\"]*)\"$")
    public void searchForBooksByAuthor(String authorName) {
        foundBooks = store.booksByAuthor(authorName);
    }

    @Then("^I find \"([^\"]*)\" books$")
    public void iFindBooks(int bookNumber) {
         assertEquals(bookNumber, foundBooks.size());
    }

    @Then("I expect to have the following books")
    public void iExpectToHaveTheFollowingBooks(List<Book> expectedBooks) {
        assertNotSame(expectedBooks, foundBooks);
    }
}
