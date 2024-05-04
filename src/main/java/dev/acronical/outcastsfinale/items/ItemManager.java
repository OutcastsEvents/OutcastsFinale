package dev.acronical.outcastsfinale.items;

import static dev.acronical.outcastsfinale.items.impl.AstelinaBarrel.createAstelinaBarrel;
import static dev.acronical.outcastsfinale.items.impl.FerveeGhast.createFerveeGhast;
import static dev.acronical.outcastsfinale.items.impl.RazmooseMeal.createRazmooseMeal;
import static dev.acronical.outcastsfinale.items.impl.SquidSocks.createSquidSocks;
import static dev.acronical.outcastsfinale.items.impl.TingzNecklace.createTingzNecklace;
import static dev.acronical.outcastsfinale.items.impl.WoocieHorse.createWoocieHorse;
import static dev.acronical.outcastsfinale.items.impl.WymsicaalRock.createWymsicaalRock;

public class ItemManager {

    public static void init() {
        createTingzNecklace();
        createSquidSocks();
        createWoocieHorse();
        createFerveeGhast();
        createAstelinaBarrel();
        createRazmooseMeal();
        createWymsicaalRock();
    }

}