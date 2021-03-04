package hotciv.standard;

import hotciv.framework.AgingStrategy;

public class BetaAgingStrategy implements AgingStrategy {

    @Override
    public int ageWorld(int currentAge) {

        //first determine what stage we are in
        if(currentAge < -100){
           currentAge += 100;
        }else if( currentAge < -1) {
            currentAge += 99;
        }else if( currentAge < 0){
            currentAge += 2;
        }else if( currentAge < 2){
            currentAge += 49;
        }else if( currentAge < 1750){
            currentAge += 50;
        }else if( currentAge < 1900){
            currentAge += 25;
        }else if(currentAge < 1970){
            currentAge += 5;
        }else{
            currentAge += 1;
        }

        return currentAge;
    }
}
