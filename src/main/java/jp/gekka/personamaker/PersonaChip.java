package jp.gekka.personamaker;

import java.util.ArrayList;
import java.util.List;

public class PersonaChip{
  private String antionName="";
  private List<String> triggers = new ArrayList<>();
  private List<String> actions = new ArrayList<>();
  private List<String> exceptions = new ArrayList<>();
  
  public PersonaChip(String name){
    this.antionName=name;
  }
  public void addAction(String action){
    this.actions.add(action);
  }
  
  public void addTrigger(String exception){
    this.triggers.add(exception);
  }
  
  public void addException(String exception){
    this.exceptions.add(exception);
  }
  public String getName(){
    return this.antionName;
  }
  
  public List<String> getTriggers(){
    return this.triggers;
  }
  
  public List<String> getActions(){
    return this.actions;
  }
  
  public List<String> getExceptions(){
    return this.exceptions;
  }
}
