package com.drake.android.cs188project3;

import android.location.Location;

/**
 * Created by Matthew on 11/27/2017.
 */

public class Restaurant {
    private String name;
    private Location location;


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}




//    //public ArrayList<EatData>
//    void checkValidity(int eventID, int maxDistance)
//    {
//        Location user =  new Location("user");
//        Location event = new Location("event");
//
//        //Location of Iowa Capitol Building
//        user.setLatitude(41.6005448);
//        user.setLongitude(-93.6091064);
//
//
//
//        //empties arrays
//
//
//        if(whichList == 1) {
//
//
//            event.setLatitude(eatList.get(eventID).getLatitude());
//            event.setLongitude(eatList.get(eventID).getLongitude());
//
//            double distance = user.distanceTo(event); //meters to miles
////                /1609.39;
//
//            if (distance <= maxDistance) {
//                displayEatList.add(eatList.get(eventID));
//
//            }
//
//            //Checks to see if the entire array has been scanned
//            if (eventID < (eatList.size() - 1)) {
//                checkValidity(eventID + 1, maxDistance);
//            }
//        }
//
//        else if(whichList == 2) {
//            event.setLatitude(drinkList.get(eventID).getLatitude());
//            event.setLongitude(drinkList.get(eventID).getLongitude());
//        }
//
//        else {
//            event.setLatitude(doList.get(eventID).getLatitude());
//            event.setLongitude(doList.get(eventID).getLongitude());}
//
//
//        //finds and compares distance
//
//
//        //Adds to array if applicable
//        if(whichList == 1)
//        {
//
//        }
//
//        else if(whichList == 2)
//        {
//            double distance = user.distanceTo(event); //meters to miles
////                /1609.39;
//            if(distance <= maxDistance)
//            {
//                displayDrinkList.add(drinkList.get(eventID));
//            }
//
//            //Checks to see if the entire array has been scanned
//            if(eventID < (drinkList.size() - 1))
//            {
//                checkValidity(eventID + 1, maxDistance);
//            }
//        }
//
//        else
//        {
//            double distance = user.distanceTo(event); //meters to miles
////                /1609.39;
//            if((distance <= maxDistance) && (checkDate(eventID) == true))
//            {
//                displayDoList.add(doList.get(eventID));
//            }
//
//            //Checks to see if the entire array has been scanned
//            if(eventID < (doList.size() - 1))
//            {
//                checkValidity(eventID + 1, maxDistance);
//            }
//        }
//
//        //return displayEatList;
//    }




