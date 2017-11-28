package dao;

import impl.*;
import java.util.*;

/**
 * Created by adixith.
 */
public class House {
    private static House house;
    private static List<Member> allRegisteredMembers = new ArrayList<>();
    private static long currentId;
    private static List<Player> housePlayers = new ArrayList<>();
    private int numerOfHousePlayers;

    public static long addAPlayer() {
        Member m = new Member(++currentId);
        allRegisteredMembers.add(m);
        return currentId;
    }

    public void initHouse() {
        currentId = 10000;
        numerOfHousePlayers = 100;
        //House players have the id 1 to 100
        for(int housePlayer = 1;housePlayer <= numerOfHousePlayers; housePlayer++) {
            housePlayers.add(new Player(100000000000l, housePlayer));
        }
    }

    public Player getHousePlayer() {
        return housePlayers.get(0);
    }

    private House() {
        initHouse();
    }

    public static House getInstance() {
        return (house == null)? new House(): house;
    }
}
