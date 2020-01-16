package guru.sfg.brewery.model.events;

import guru.sfg.brewery.model.BeerDto;

import java.io.Serializable;

public class BeerEvent implements Serializable {

    private static final long serialVersionUID = -5755422283023894641L;

    private BeerDto beerDto;

    public BeerEvent() {
    }

    public BeerEvent(BeerDto beerDto) {
        this.beerDto = beerDto;
    }

    public BeerDto getBeerDto() {
        return beerDto;
    }

}
