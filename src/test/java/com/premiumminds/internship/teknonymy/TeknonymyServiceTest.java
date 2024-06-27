package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.TeknonymyService;
import com.premiumminds.internship.teknonymy.Person;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class TeknonymyServiceTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public TeknonymyServiceTest() {
  };

  @Test
  public void PersonNoChildrenTest() {
    Person person = new Person("John",'M',null, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "";
    assertEquals(result, expected);
  }

  @Test
  public void PersonOneChildTest() {
    Person person = new Person(
        "John",
        'M',
        new Person[]{ new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)) },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of Holy";
    assertEquals(result, expected);
  }

  @Test
  public void PersonTwoChildrenTest() {
    Person person = new Person(
        "John",
        'M',
        new Person[]{ 
          new Person("Holy",'F', null, LocalDateTime.of(1045, 1, 1, 0, 0)),
          new Person("Mary",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0))
        },
        LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of Holy";
    assertEquals(result, expected);
  }

  @Test
  public void test4() {
    Person faby = new Person("Faby",'F', null, LocalDateTime.of(1045, 1, 1, 0, 0));
    Person jane = new Person("Jane",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0));
    Person diana = new Person("Diana",'F', null, LocalDateTime.of(1047, 1, 1, 0, 0));
    Person carlos = new Person("Carlos",'M', new Person[]{ faby}, LocalDateTime.of(1048, 1, 1, 0, 0));
    Person marie = new Person("Marie",'F', new Person[]{ jane, diana}, LocalDateTime.of(1049, 1, 1, 0, 0));
    Person liam = new Person("Liam",'M', new Person[]{ carlos}, LocalDateTime.of(1050, 1, 1, 0, 0));
    Person luke = new Person("Luke",'M', null, LocalDateTime.of(1051, 1, 1, 0, 0));
    Person charles = new Person("Charles",'M',null, LocalDateTime.of(1052, 1, 1, 0, 0));
    Person joanne = new Person("Joanne",'F', null, LocalDateTime.of(1053, 1, 1, 0, 0));
    Person lois = new Person("Lois",'F', new Person[]{ liam, luke}, LocalDateTime.of(1054, 1, 1, 0, 0));
    Person john = new Person("John",'M', new Person[]{ marie, joanne}, LocalDateTime.of(1055, 1, 1, 0, 0));
    Person anne = new Person("Anne",'F', new Person[]{ charles}, LocalDateTime.of(1056, 1, 1, 0, 0));
    Person person = new Person("Peter",'M', new Person[]{ john, lois, anne}, LocalDateTime.of(1057, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-great-grandfather of Faby";
    assertEquals(result, expected);
  }

  @Test
  public void Test5(){
    Person jane = new Person("Jane",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0));
    Person diana = new Person("Diana",'F', null, LocalDateTime.of(1047, 1, 1, 0, 0));
    Person marie = new Person("Marie",'F', new Person[]{ jane, diana}, LocalDateTime.of(1049, 1, 1, 0, 0));
    
    Person charles = new Person("Charles",'M',null, LocalDateTime.of(1070, 1, 1, 0, 0));
    Person joanne = new Person("Joanne",'F', null, LocalDateTime.of(1053, 1, 1, 0, 0));
   
    Person john = new Person("John",'M', new Person[]{ marie, joanne}, LocalDateTime.of(1055, 1, 1, 0, 0));
    Person anne = new Person("Anne",'F', new Person[]{ charles}, LocalDateTime.of(1056, 1, 1, 0, 0));
    Person person = new Person("Joanna",'F', new Person[]{ john, anne}, LocalDateTime.of(1057, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-grandmother of Jane";
    assertEquals(result, expected);
  }

  @Test
  public void Test6(){
    Person marie = new Person("Marie",'F', null, LocalDateTime.of(1049, 1, 1, 0, 0));
    Person liam = new Person("Liam",'M', null, LocalDateTime.of(1050, 1, 1, 0, 0));
    Person luke = new Person("Luke",'M', null, LocalDateTime.of(1051, 1, 1, 0, 0));
    Person charles = new Person("Charles",'M',null, LocalDateTime.of(1052, 1, 1, 0, 0));
    Person joanne = new Person("Joanne",'F', null, LocalDateTime.of(1053, 1, 1, 0, 0));
    Person lois = new Person("Lois",'F', new Person[]{ liam, luke}, LocalDateTime.of(1054, 1, 1, 0, 0));
    Person john = new Person("John",'M', new Person[]{ marie, joanne}, LocalDateTime.of(1055, 1, 1, 0, 0));
    Person anne = new Person("Anne",'F', new Person[]{ charles}, LocalDateTime.of(1056, 1, 1, 0, 0));
    Person person = new Person("Peter",'M', new Person[]{ john, lois, anne}, LocalDateTime.of(1057, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of Marie";
    assertEquals(result, expected);
  }


}