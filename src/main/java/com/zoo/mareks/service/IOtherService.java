package com.zoo.mareks.service;

import java.util.Collection;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.Zoo;

public interface IOtherService {
    
    public abstract Collection<Zoo> selectAllZoosByAnimal(Animal animal);
    
    public abstract Zoo searchForZooByAddress(String address);
}
