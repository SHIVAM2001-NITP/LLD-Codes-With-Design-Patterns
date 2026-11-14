package service;

import java.time.LocalDateTime;

// Lets the lock timeout be tested without sleeping: the demo swaps in a clock it can move forward.
public interface TimeProvider {
    LocalDateTime now();
}
