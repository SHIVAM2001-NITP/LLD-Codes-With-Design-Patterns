package util;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

// A clock we can move by hand, so coupon expiry can be tested without waiting for a real date
public class ManualClock extends Clock {
    private Instant now;

    public ManualClock(Instant start) { this.now = start; }

    public void advance(Duration duration) { now = now.plus(duration); }

    @Override
    public Instant instant() { return now; }

    @Override
    public ZoneId getZone() { return ZoneOffset.UTC; }

    @Override
    public Clock withZone(ZoneId zone) { return this; }
}
