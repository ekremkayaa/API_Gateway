package com.company.project.controllers;

import java.util.List;
import java.util.ArrayList;
import com.company.project.entity.Person;
import com.company.project.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @DeleteMapping("/del/{id}")
    public String removePerson(@PathVariable int id){
        Person apisixPerson = repository.findById(id).get();
        repository.delete(apisixPerson);
        return apisixPerson.getName() + " silindi";
    }
	
	@GetMapping("/all")
    public List<Person> getPerson() {
        List<Person> apisixPerson = new ArrayList<>();
		repository.findAll().forEach(apisixPerson::add);
        return apisixPerson;
    }

	@PostMapping("/new")
	public ResponseEntity<String> addNewPerson(@RequestBody Person person){
		Person apisixPerson = repository.save(person);
		return ResponseEntity.status(HttpStatus.CREATED).body(
                apisixPerson.getName() + " kaydedildi");
	}

    @PutMapping("/update/{id}")
    public Person updatePerson(@PathVariable int id, @RequestBody Person person){
        Person apisixPerson = repository.findById(id).get();
        apisixPerson.setName(person.getName());
        repository.save(apisixPerson);
        return apisixPerson;
    }
}
