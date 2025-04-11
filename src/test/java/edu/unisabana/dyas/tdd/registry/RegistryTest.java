package edu.unisabana.dyas.tdd.registry;

import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {
    private Registry registry = new Registry();

    @Test
    public void shouldReturnDeadWhenPersonIsNotAlive() {
        Person person = new Person("Pedro", 1, 30, Gender.MALE, false);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void shouldReturnUnderageWhenPersonIsYoungerThan18() {
        Person person = new Person("Ana", 2, 16, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void shouldReturnInvalidAgeWhenPersonHasNegativeAge() {
        Person person = new Person("Carlos", 3, -5, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldReturnValidWhenPersonIsValid() {
        Person person = new Person("Laura", 4, 25, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldReturnDuplicatedWhenPersonIsAlreadyRegistered() {
        Person person1 = new Person("Mario", 5, 40, Gender.MALE, true);
        Person person2 = new Person("Mario", 5, 40, Gender.MALE, true); // mismo ID

        registry.registerVoter(person1);
        RegisterResult result = registry.registerVoter(person2);
        Assert.assertEquals(RegisterResult.DUPLICATED, result);
    }
}
