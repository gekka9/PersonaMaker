package jp.gekka.personamaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChipsFactory{
  enum Type{INIT,NAME,TRIGGER,ACTION,EXCEPTION,FINISH}
  
  private List<String> targetStrings=new ArrayList<>();
  private Type type = Type.NAME;
  
  public ChipsFactory(File target){
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(target));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String line = "";
    try {
      while((line = reader.readLine()) != null){
        this.targetStrings.add(line);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public List<PersonaChip> run(){
    List<String> blocks = this.createBlocks();
    List<PersonaChip> chips = new ArrayList<>();
    for(String block:blocks){
      chips.add(this.createChip(block));
    }
    return chips;
  }
  
  private List<String> createBlocks(){
    StringBuilder builder = new StringBuilder();
    List<String> result = new ArrayList<>();
    Type type = Type.NAME;
    for(String line : this.targetStrings){
      if(line.equals("")){
      }else{
        builder.append(line+System.getProperty("line.separator"));
        switch (type){
          case NAME:  
            type=Type.TRIGGER;
            break;
          case TRIGGER:
            type=Type.ACTION;
            break;
          case ACTION:
            type=Type.EXCEPTION;
            break;
          case EXCEPTION:
            result.add(builder.toString());
            builder = new StringBuilder();
            type=Type.NAME;
            break;
          default:
            break;
        }
      }
    }
    return result;
  }
  
  private PersonaChip createChip(String target){
    String[] lines = target.split(System.getProperty("line.separator"));
    PersonaChip chip=null;
    Type type=Type.NAME;
    for(String line : lines){
      if(type==Type.NAME){
        chip = new PersonaChip(line);
        type=Type.TRIGGER;
      }else{
        for(String element: line.split(",")){
          switch (type){
            case TRIGGER:
              chip.addTrigger(element);
              break;
            case ACTION:
              chip.addAction(element);
              break;
            case EXCEPTION:
              chip.addException(element);
              break;
            default:
              break;
          }
        }
        switch (type){
        case TRIGGER:
          type=Type.ACTION;
          break;
        case ACTION:
          type=Type.EXCEPTION;
          break;
        case EXCEPTION:
          type=Type.NAME;
          break;
        default:
          break;
        }
      }
    }
    return chip;
  }
}
