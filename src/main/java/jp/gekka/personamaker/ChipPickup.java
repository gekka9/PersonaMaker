package jp.gekka.personamaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChipPickup {
  public static List<PersonaChip> pickup(List<PersonaChip> chips, int number){
    List<PersonaChip> result= new ArrayList<>();
    Collections.shuffle(chips);
    for(int i=0;i<number;i++){
      result.add(chips.get(i));
    }
    return result;
  }
  
  public static PersonaChip chooseElements(PersonaChip chip, int number){
    PersonaChip result= new PersonaChip(chip.getName());
    
    List<String> triggers = chip.getTriggers();
    Collections.shuffle(triggers);
    for(int i=0;i<number;i++){
      result.addTrigger(triggers.get(i));
    }
    
    List<String> actions = chip.getActions();
    Collections.shuffle(actions);
    for(int i=0;i<number;i++){
      result.addAction(actions.get(i));
    }
   
    
    List<String> exceptions = chip.getExceptions();
    Collections.shuffle(exceptions);
    for(int i=0;i<number;i++){
      result.addException(exceptions.get(i));
    }
    
    return result;
  }
}
