package app.api.controller;

import app.entity.Emp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/emp")
public class EmpAPIController {

    private final EmpRepository empRepository;

    @PutMapping("/{empno}")
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
}