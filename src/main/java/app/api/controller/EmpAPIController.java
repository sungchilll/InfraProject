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

import app.RequestDto.EmpRequest;
import app.ResponseDto.EmpResponse;
import app.entity.Dept;
import app.entity.Emp;
import app.entity.ExceptionMessage;
import app.repository.DeptRepository;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmpAPIController {

	private final EmpRepository empRepository;
	private final DeptRepository deptRepository;
	
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
	public ResponseEntity<?> registerEmp(@RequestBody EmpRequest newEmp) {
	  	
	    Dept dept = deptRepository.getReferenceById(newEmp.getDeptno());
	    
	  	Emp emp = new Emp(newEmp.getEmpno(), newEmp.getEname(), newEmp.getJob(), newEmp.getMgr(), newEmp.getHiredate(), newEmp.getSal(), newEmp.getComm(), dept);
		EmpResponse empResponse = EmpResponse.from(empRepository.save(emp));
		
		return new ResponseEntity<>(empResponse, HttpStatus.OK);
	}
  
  @PutMapping("emp/{empno}")
    public ResponseEntity<EmpResponse> updateEmp(@PathVariable Integer empno, @RequestBody EmpRequest updated) {

        Emp emp;
        Dept dept = deptRepository.getReferenceById(updated.getDeptno());
        
        ResponseEntity<EmpResponse> response;

        if (empRepository.findById(empno).isPresent()) {
            emp = empRepository.findById(empno).get();
            emp.setEname(updated.getEname());
            emp.setJob(updated.getJob());
            emp.setMgr(updated.getMgr());
            emp.setHiredate(updated.getHiredate());
            emp.setSal(updated.getSal());
            emp.setComm(updated.getComm());
            emp.setDept(dept); 
            
            EmpResponse empResponse = EmpResponse.from(emp);

            response = ResponseEntity.ok(empResponse);
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