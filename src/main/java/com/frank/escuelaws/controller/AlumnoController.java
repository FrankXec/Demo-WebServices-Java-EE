
package com.frank.escuelaws.controller;

import com.frank.escuelaws.models.Alumno;
import com.frank.escuelaws.repository.AlumnoRepository;
import java.util.List;

public class AlumnoController {
    private AlumnoRepository repository;
    
    public AlumnoController(){
        repository = new AlumnoRepository();
    }
    
    public List<Alumno> getAll(){
        return repository.getAll();
    }
    
    public Alumno getOneById(Long id){
        return repository.getOneById(id);
    }
    
    public boolean create(Alumno save){
        return repository.save(save);
    }
    
    /*public boolean update(Alumno update){
        return repository.update(update);
    }*/
    
    public boolean update(Long id, Alumno update){
        return repository.update(id, update);
    }
    
    public boolean delete(Long id){
        return repository.delete(id);
    }
}
