/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationlast120;

/**
 *
 * @author Abdul Hadi
 */
class Connection {
    Location destination;
             Location source;
             public int leng;
             String type;
             boolean requireUpgrade;
             boolean removeNeed;
        boolean removalNeeded;
        public  Connection(Location source, Location destination, int leng, String type){
        this.source = source;
        this.removeNeed = false;
        this.requireUpgrade = false;
        this.leng = leng;
        this.type = type;
        this.destination  =destination;
        }
        public Location getSource(){
            return source;
        }
        public Location getDestination(){
        return destination;
        }
        public int getLength(){
        return leng;
        }
        public String getType(){
            return type;
        }
        public boolean isUpgradeRequired(){
            return requireUpgrade;
        }
        public boolean isRemovalNeeded(){
            return removeNeed;
        }
        }

