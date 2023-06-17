/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_wia1002;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdul Hadi
 */
public class TheHand {
    public void activateAbility() {
        System.out.println("You have activated the ability of The Hand!");
        System.out.println("You can now erase space and teleport to different locations!");}
     List<Connection> waterConnections;
        public TheHand() {
            this.waterConnections = new ArrayList<>();
        }
        public void removeWaterConnections(List<Location> locations, Iterable<Connection> connections) {
            for (Location location : locations) {
                int waterConnectionCount = 0;
                Connection removeConnection = null;
                for (Connection connection : connections) {
                    if (connection.type.equals("water connection")) {
                        waterConnectionCount++;
                        if (waterConnectionCount > 1) {
                            connection.removeNeed = true;
                            removeConnection = connection;
                        }
                    }
                }
//                TheHand th = new TheHand();
               
               
                
//                if (removeConnection != null) {
//                    location.connections.remove(removeConnection);
//                    waterConnections.add(removeConnection);
//                }
            }
}
}
