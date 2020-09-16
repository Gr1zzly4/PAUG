package model.test;
import model.Vector2;

public class TestVector2 {


        private Vector2 testVecOne = new Vector2();
        private Vector2 testVecTwo = new Vector2();
        private Vector2 testZeroVec = new Vector2();

        public TestVector2(){}

        public void testVector2() {
            System.out.println("                ---Test Vector---");

            testZeroVec.setX(0.0);
            testZeroVec.setY(0.0);

            testVecOne.setX(100);
            testVecOne.setY(100);

            testVecOne = testVecOne.zero();

            if (testVecOne.getX() == 0 && testVecOne.getY() == 0)
                System.out.println("Vector2 test Zero() is " + true);
            else
                System.out.println("Vector2 test Zero() is " + false);

            testVecOne.setX(2);
            testVecOne.setY(2);

            testVecTwo.setX(2);
            testVecTwo.setY(2);

            testVecOne = testVecOne.add(testVecTwo);

            if (testVecOne.getX() == 4 && testVecOne.getY() == 4)
                System.out.println("Vector2 test Add() is " + true);
            else
                System.out.println("Vector2 test Add() is " + false);

            testVecOne = testVecOne.mul(2);

            if (testVecOne.getX() == 8 && testVecOne.getY() == 8)
                System.out.println("Vector2 test Mul() is " + true);
            else
                System.out.println("Vector2 test Mul() is " + false);
        }

}

