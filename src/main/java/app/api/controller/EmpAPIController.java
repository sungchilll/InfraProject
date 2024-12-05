package app.api.controller;

import app.dto.EmpDTO;
import app.entity.Dept;
import app.entity.Emp;
import app.repository.DeptRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.repository.EmpRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/emp")
public class EmpAPIController {

    private final EmpRepository empRepository;
    private final DeptRepository deptRepository;

    @PutMapping("/{empno}")
    public ResponseEntity<EmpDTO> updateEmp(@PathVariable Integer empno, @RequestBody EmpDTO updated) {

        Emp emp;
        ResponseEntity<EmpDTO> response;

        if (empRepository.findById(empno).isPresent()) {
            emp = empRepository.findById(empno).get();
            emp.setEname(updated.getEname());
            emp.setJob(updated.getJob());
            emp.setMgr(updated.getMgr());
            emp.setHiredate(updated.getHiredate());
            emp.setSal(updated.getSal());
            emp.setComm(updated.getComm());

            Dept dept = deptRepository.findById(updated.getDeptno()).get();
            emp.setDept(dept);

            EmpDTO dto = EmpDTO.from(emp);

            response = ResponseEntity.ok(dto);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
}