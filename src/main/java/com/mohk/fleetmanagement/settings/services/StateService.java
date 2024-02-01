package com.mohk.fleetmanagement.settings.services;


import com.mohk.fleetmanagement.settings.models.State;
import com.mohk.fleetmanagement.settings.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    //    Fetch all states
    public List<State> findAll(){
        return stateRepository.findAll();
    }

    //get state by id
    public State findById(int id){
        return stateRepository.findById(id).orElse(null);
    }

    //delete state
    public void delete(int id){
        stateRepository.deleteById(id);
    }

    //update state
    public void save(State state){
        stateRepository.save(state);
    }
}
