package com.jimluisf.springbootawsdynamodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimluisf.springbootawsdynamodb.model.Student;
import com.jimluisf.springbootawsdynamodb.repository.DynamoDbRepository;

@RestController
@RequestMapping("/dynamoDb")
public class DynamoDbController 
{
	@Autowired
	private DynamoDbRepository repo;
	
	@PostMapping
	public String inerstEntoDynamoDb(@RequestBody Student student)
	{
		repo.insertIntoDynamoDB(student);
		return "Successfully inserted into DynamoDB table";
	}
	
	@GetMapping
	public ResponseEntity<Student> getOneStudentDetails(@RequestParam String studentId, @RequestParam String lastName)
	{
		Student student = repo.getOneStudentDetails(studentId, lastName);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@PutMapping
	public void updateStudentDetails(@RequestBody Student student)
	{
		repo.updateStudentDetails(student);
	}
	
	@DeleteMapping(value="{studentId}/{lastName}")
	public void deleteStudentDetails(@PathVariable String studentId, @PathVariable String lastName)
	{
		Student student = new Student();
		student.setStudentId(studentId);
		student.setLastName(lastName);
		repo.deleteStudentDetails(student);
	}
}
