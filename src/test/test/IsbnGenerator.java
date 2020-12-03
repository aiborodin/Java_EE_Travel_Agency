package test;

import javax.enterprise.event.Event;
import javax.inject.Inject;

public class IsbnGenerator implements NumberGenerator{

    @Inject
    Event<String> isbnAddedEvent;

    @Override
    public String generateSmallNumber(int a) {
        String isbn = "Small Isbn" + a;
        System.out.println("Event fires");
        isbnAddedEvent.fire(isbn);
        System.out.println("Return to generateSmall");
        return isbn;
    }

    @Override
    public String generateBigNumber() {
        return "Big Isbn";
    }
}
