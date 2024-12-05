package app.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  
  @PutMapping("emp/{empno}")
    public ResponseEntity<Emp> updateEmp(@PathVariable Integer empno, @RequestBody Emp updated) {

        Emp emp;
        ResponseEntity<Emp> response;

        if (empRepository.findById(empno).isPresent()) {
            emp = empRepository.findById(empno).get();
            emp.setEname(updated.getEname());
            emp.setJob(updated.getJob());
            emp.setMgr(updated.getMgr());
            emp.setHiredate(updated.getHiredate());
            emp.setSal(updated.getSal());
            emp.setComm(updated.getComm());
            emp.setDept(updated.getDept()); //

            response = ResponseEntity.ok(emp);
        } else {
            response = ResponseEntity.notFound().build();
        }
     return response;
  }
  
  @DeleteMapping("/emp/{empno}")
	public Emp deleteEmpByEmpno(@PathVariable Integer empno) {
	    Emp emp = empRepository.findById(empno)
	            .orElseThrow(() -> new IllegalArgumentException("사원정보가 존재하지 않습니다 : " + empno));
	    
	    empRepository.deleteById(empno);

	    return emp;
	}

}