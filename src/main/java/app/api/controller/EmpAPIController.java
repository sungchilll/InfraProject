package app.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.entity.ExceptionMessage;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmpAPIController {

	private final EmpRepository empRepository;
	
	@GetMapping("/emps")
	ResponseEntity<?> searchAll(){
		List<Emp> emps = empRepository.findAll();
		if(emps.isEmpty()) return new ResponseEntity<>(new ExceptionMessage("사원정보가 존재하지 않습니다"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(emps, HttpStatus.OK);
	}
  	
	@GetMapping("/emp/{empno}")
	ResponseEntity<?> SearchByNo(@PathVariable Integer empno){
		Optional<Emp> emp = empRepository.findById(empno);
		if(!emp.isPresent()) return new ResponseEntity<>(new ExceptionMessage("사원정보가 존재하지 않습니다"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
  
  @PostMapping("/emp")
	public Emp registerEmp(@RequestBody Emp newEmp) {
		Emp emp = empRepository.save(newEmp);
		return emp;
	}
}

