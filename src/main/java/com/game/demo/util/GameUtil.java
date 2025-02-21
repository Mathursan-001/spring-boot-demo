package com.game.demo.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class GameUtil {

    public String getResult(Integer val,Integer rand){

      if(rand.equals(val)){
          return "WIN";
      }else{
          return "LOSE";
      }

    }

    public Integer getRandom(){

        Random rand = new Random();

        return rand.nextInt(10);

    }

}
