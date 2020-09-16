package model.test;

import model.test.*;

public class Test {
    public static void main(String[] args) {

        TestVector2 testVector2 = new TestVector2();
        TestCharacter testCharacter = new TestCharacter();
        TestMovingObject testMovingObject = new TestMovingObject();
        TestGame testGame = new TestGame();

        testVector2.testVector2();
        testMovingObject.testSearch();
        testGame.test();
        testCharacter.testState();

    }
}
