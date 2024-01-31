package Aggregator;


import java.util.Iterator;
import java.util.List;
import Book.Book;
import Iterator.BookIterator;


public class Library implements Aggregate{
    private  List<Book> booksList;
    public Library(List<Book>booksList){
        this.booksList=booksList;
    }
    @Override
    public Iterator createIterator() {
        return (Iterator) new BookIterator(booksList);
    }
}
