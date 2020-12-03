package test;

import javax.enterprise.event.Observes;

public class IsbnObserver {
    public void isbnAdded(@Observes String isbn) {
        System.out.println("Observer works");
    }
}
