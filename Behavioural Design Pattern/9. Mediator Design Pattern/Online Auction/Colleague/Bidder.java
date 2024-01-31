package Colleague;

import Colleague.Colleague;
import Mediator.AuctionMediator;

public class Bidder implements Colleague {
   String name;
   AuctionMediator auctionMediator;

    public Bidder(String name, AuctionMediator auctionMediator) {
        this.name = name;
        this.auctionMediator = auctionMediator;
        auctionMediator.addBidder(this);
    }

    @Override
    public void placeBid(int bidAmount) {
        auctionMediator.placeBid(this,bidAmount);
    }

    @Override
    public void receiveBidNotification(int bidAmount) {
     System.out.println("Colleague.Bidder: "+name+" got the notification that someone has put bid of : "+bidAmount);
    }

    @Override
    public String getName() {
        return name;
    }
}
