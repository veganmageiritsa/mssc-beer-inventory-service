package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.brewery.model.events.NewInventoryEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NewInventoryListener {
    private static final Logger logger = LoggerFactory.getLogger(NewInventoryListener.class);
    private final BeerInventoryRepository beerInventoryRepository;


    public NewInventoryListener(BeerInventoryRepository beerInventoryRepository) {
        this.beerInventoryRepository = beerInventoryRepository;
    }

    @JmsListener(destination = JmsConfig.INVENTORY_REQUEST_QUEUE)
    public void listen(NewInventoryEvent event) {

        logger.debug("Got Inventory : {}", event.toString());

        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(event.getBeerDto().getId())
                .upc(event.getBeerDto().getUpc())
                .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                .build());
    }
}
