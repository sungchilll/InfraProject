package app.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Emp;
import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmpAPIController {

	private final EmpRepository empRepository;
	
	
	@DeleteMapping("/emp/{empno}")
	public Emp deleteEmpByEmpno(@PathVariable Integer empno) {
	    Emp emp = empRepository.findById(empno)
	            .orElseThrow(() -> new IllegalArgumentException("해당 사원을 찾을 수 없습니다 : " + empno));
	    
	    empRepository.deleteById(empno);

	    return emp;
	}
}