package model.test;

import model.Map;
import model.Character;
import model.Character.CharacterState;

public class TestCharacter {

    private Map testLvl = new Map();
    Character plr = new Character(testLvl);
    CharacterState state = CharacterState.Walk;

    public void testState(){
        System.out.println("                ---Test Character---");
        System.out.println("        Test state:");

        System.out.println("Test case Stand");

        plr.setmCurrentState(CharacterState.Stand);
        plr.CharacterUpdate();
        if(plr.getSpeed().getX() == 0 && plr.getSpeed().getY() == 0)
            System.out.println(true);
        else
            System.out.println(false);

        System.out.println("Test case Walk");

        plr.setmCurrentState(CharacterState.Walk);
        plr.CharacterUpdate();
        if( plr.getSpeed().getY() == 0)
            System.out.println(true);
        else
            System.out.println(false);

        System.out.println("Test case Jump");

        plr.setmCurrentState(CharacterState.Jump);
        plr.CharacterUpdate();
        if(plr.getSpeed().getX() == 0 && plr.getSpeed().getY() == -0.4)
            System.out.println(true);
        else
            System.out.println(false);


        System.out.println("Test case Walk inpunts 1");

        plr.setmCurrentState(CharacterState.Stand);
        plr.setmInputs(false,true,false,false);
        plr.CharacterUpdate();
        if(plr.getmCurrentState() == CharacterState.Walk)
            System.out.println(true);
        else
            System.out.println(false);

        plr.setmInputs(false,false,false,false);

        System.out.println("Test case Walk inputs 2");

        plr.setmCurrentState(CharacterState.Stand);
        plr.setmInputs(true,false,false,false);
        plr.CharacterUpdate();
        if(plr.getmCurrentState() == CharacterState.Walk)
            System.out.println(true);
        else
            System.out.println(false);

        plr.setmInputs(false,false,false,false);

        System.out.println("Test case Jump inputs 1");

        plr.setmCurrentState(CharacterState.Stand);
        plr.setmInputs(true,false,true,false);
        plr.CharacterUpdate();
        if(plr.getmCurrentState() == CharacterState.Walk)
            System.out.println(true);
        else
            System.out.println(false);


        plr.setmInputs(false,false,false,false);

        System.out.println("Test case Jump inputs 2");

        plr.setmCurrentState(CharacterState.Stand);
        plr.setmInputs(false,true,true,false);
        plr.CharacterUpdate();
        if(plr.getmCurrentState() == CharacterState.Walk)
            System.out.println(true);
        else
            System.out.println(false);

        plr.setmInputs(false,false,false,false);

        System.out.println("Test case Jump inputs 3");

        plr.setmCurrentState(CharacterState.Stand);
        plr.setmInputs(false,false,true,false);
        plr.CharacterUpdate();
        if(plr.getmCurrentState() == CharacterState.Stand)
            System.out.println(true);
        else
            System.out.println(false);

    }


}
