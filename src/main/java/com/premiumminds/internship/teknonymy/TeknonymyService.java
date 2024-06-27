package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.Person;
import java.util.*;

class TeknonymyService implements ITeknonymyService {

 
  /**
   * Method to get the string equivalent to the solution of the problem 
   * 
   * @param Person descendant
   * @param Person person
   * @param int level
   * @return String which is the Teknonymy Name
  */
  private String getTeknonymyName(Person descendant, Person person, int level) {
    String init;
    if (person.sex().equals('M')){
      init = "father of ";
    } else {
      init = "mother of ";
    }
    if (level > 2) {
      return "great-".repeat(level-3) + "grand" + init + descendant.name();
    } else {
      return init + descendant.name();
    }
  }

   /**
   * Method to get a Person Teknonymy Name trough a breadth-first search
   * 
   * @param Person person
   * @return String which is the Teknonymy Name 
   */
  public String getTeknonymy(Person person) {
    int level = 0;
    int levelsize;
    boolean newlevel = true;
    Person descendant = null;

    Queue<Person> queue = new LinkedList<>();
    queue.add(person);
    
    while(!queue.isEmpty()){
      levelsize = queue.size();
      newlevel = true;

      for(int i = 0; i < levelsize; i++){
        Person current = queue.poll();
        if(current.children() != null){
          for(Person child : current.children()){
            queue.add(child);
            if(newlevel || child.dateOfBirth().isBefore(descendant.dateOfBirth())){
              newlevel = false;
              descendant = child;
            }
          }
        }
      }
      level++;
    }

    if (descendant == null) 
      return "";
    return getTeknonymyName(descendant, person, level);
  }
}