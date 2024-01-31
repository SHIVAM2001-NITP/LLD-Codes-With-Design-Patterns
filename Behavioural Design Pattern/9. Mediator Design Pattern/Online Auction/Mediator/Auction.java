package Mediator;

import Mediator.AuctionMediator;
import Colleague.Colleague;


import java.util.ArrayList;
import java.util.List;
//Mediator Concrete Class
public class Auction implements AuctionMediator {
    List<Colleague> colleagues=new ArrayList<>();
    @Override
    public void addBidder(Colleague bidder) {
        colleagues.add(bidder);
    }

    @Override
    public void placeBid(Colleague bidder, int bidAmount) {
        for(Colleague colleague:colleagues){
            if(!colleague.getName().equals(bidder.getName())){
                colleague.receiveBidNotification(bidAmount);
            }
        }

    }
}
