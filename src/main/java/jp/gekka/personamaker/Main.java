package jp.gekka.personamaker;

import java.io.File;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Main{
    public static void main( String[] args ){
        ChipsFactory factory = new ChipsFactory(new File(args[0]));
        List<PersonaChip> chips = factory.run();
        for(PersonaChip chip:chips){
          System.out.println(chip.getName());
          for(String element:chip.getActions()){
            System.out.println("ACT  "+element);
          }
          for(String element:chip.getTriggers()){
            System.out.println("TRG  "+element);
          }
          for(String element:chip.getExceptions()){
            System.out.println("EXC  "+element);
          }
        }
    }
}
