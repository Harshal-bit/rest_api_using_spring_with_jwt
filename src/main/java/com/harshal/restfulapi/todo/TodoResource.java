package com.harshal.restfulapi.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class TodoResource {
	
	TodoService todoservice;

	TodoResource(TodoService todoservice){
		this.todoservice = todoservice;
	}
	//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodosByUserName(@PathVariable String username){
		return todoservice.findByUsername(username);
	}
	//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username,@PathVariable int id){
		return todoservice.findById(id);
	}
	//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable int id){
		todoservice.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username,@PathVariable int id,@RequestBody Todo todo){
		
		todoservice.updateTodo(todo);
		return todo;
	}
	
	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@PathVariable String username, @RequestBody Todo todo){
		
		Todo createdTodo = todoservice.addTodo(username,todo.getDescription(),todo.getTargetDate(),todo.isisDone());
		return createdTodo;
	}
	
}
