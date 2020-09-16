package model.test;

import model.MovingObject;
import model.Map;
import model.Vector2;

public class TestMovingObject {
    public TestMovingObject(){}

    public static void testSearch(){
        System.out.println("                ---Test Block search---");

        searchTop();
        searchBot();
        searchRight();
        searchLeft();
    }

    public static void searchBot(){
        Map testLvl = new Map();

        Vector2 testPosition;

        MovingObject testObj = new MovingObject(testLvl);

        System.out.println("        Search bot:");
        System.out.println("Search 1");
        testPosition = new Vector2(90,300);

        testObj.searchBotFirst(testPosition);
        System.out.println("    " + testObj.getmOnGround());

        testPosition = new Vector2(90,420);
        testObj.searchBotFirst(testPosition);

        System.out.println("Search 2");
        testPosition = new Vector2(60,300);

        testObj.searchBotSecond(testPosition);
        System.out.println("    " + testObj.getmOnGround());

        testPosition = new Vector2(90,420);
        testObj.searchBotFirst(testPosition);

        System.out.println("Search 3");
        testPosition = new Vector2(30,300);

        testObj.searchBotThird(testPosition);
        System.out.println("    " + testObj.getmOnGround());

        testPosition = new Vector2(90,420);
        testObj.searchBotFirst(testPosition);
    }

    public static void searchTop(){
        Map testLvl = new Map();

        Vector2 testPosition;

        MovingObject testObj = new MovingObject(testLvl);

        System.out.println("        Search top:");
        System.out.println("Search 1");

        testPosition = new Vector2(90,120);

        testObj.searchTopFirst(testPosition);
        System.out.println("    " + testObj.getmPushesTopWall());

        testPosition = new Vector2(0,0);
        testObj.searchTopFirst(testPosition);

        System.out.println("Search 2");
        testPosition = new Vector2(60,120);

        testObj.searchTopSecond(testPosition);
        System.out.println("    " + testObj.getmPushesTopWall());

        testPosition = new Vector2(0,0);
        testObj.searchTopFirst(testPosition);

        System.out.println("Search 3");
        testPosition = new Vector2(30,120);

        testObj.searchTopThird(testPosition);
        System.out.println("    " + testObj.getmPushesTopWall());

        testPosition = new Vector2(0,0);
        testObj.searchTopFirst(testPosition);
    }

    public static void searchRight(){
        Map testLvl = new Map();

        Vector2 testPosition;

        MovingObject testObj = new MovingObject(testLvl);

        System.out.println("        Search right:");
        System.out.println("Search 1");
        testPosition = new Vector2(360,0);

        testObj.searchRightTopFirst(testPosition);
        System.out.println("    " + testObj.getmPushesRightWall());

        testPosition = new Vector2(0,0);
        testObj.searchRightTopSecond(testPosition);

        System.out.println("Search 2");
        testPosition = new Vector2(360,0);

        testObj.searchRightTopSecond(testPosition);
        System.out.println("    " + testObj.getmPushesRightWall());

        testPosition = new Vector2(0,0);
        testObj.searchRightBotFirst(testPosition);

        System.out.println("Search 3");
        testPosition = new Vector2(360,0);

        testObj.searchRightBotFirst(testPosition);
        System.out.println("    " + testObj.getmPushesRightWall());

        testPosition = new Vector2(0,0);
        testObj.searchBotFirst(testPosition);

        System.out.println("Search 4");
        testPosition = new Vector2(360,0);

        testObj.searchRightBotSecond(testPosition);
        System.out.println("    " + testObj.getmPushesRightWall());

        testPosition = new Vector2(0,0);
        testObj.searchBotFirst(testPosition);
    }

    public static void searchLeft(){
        Map testLvl = new Map();

        Vector2 testPosition;

        MovingObject testObj = new MovingObject(testLvl);

        System.out.println("        Search left:");
        System.out.println("Search 1");
        testPosition = new Vector2(-780,420);

        testObj.searchLeftTopFirst(testPosition);
        System.out.println("    " + testObj.getmPushesLeftWall());

        testPosition = new Vector2(0,0);
        testObj.searchLeftTopFirst(testPosition);

        System.out.println("Search 2");
        testPosition = new Vector2(-780,420);

        testObj.searchLeftTopSecond(testPosition);
        System.out.println("    " + testObj.getmPushesLeftWall());

        testPosition = new Vector2(0,0);
        testObj.searchLeftTopFirst(testPosition);

        System.out.println("Search 3");
        testPosition = new Vector2(-780,420);

        testObj.searchLeftBotFirst(testPosition);
        System.out.println("    " + testObj.getmPushesLeftWall());

        testPosition = new Vector2(0,0);
        testObj.searchLeftTopFirst(testPosition);

        System.out.println("Search 4");
        testPosition = new Vector2(-780,420);

        testObj.searchLeftBotSecond(testPosition);
        System.out.println("    " + testObj.getmPushesLeftWall());

        testPosition = new Vector2(0,0);
        testObj.searchLeftTopFirst(testPosition);
    }
}
