package app.dto;

import app.entity.Dept;
import app.entity.Emp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@Getter
public class EmpDTO {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private LocalDate hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;

    public static EmpDTO from(Emp emp) {
        
        return new EmpDTO(
                emp.getEmpno(), emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(), emp.getComm(), emp.getDept().getDeptno()
        );
    }

    public Emp toEmp(Dept dept) {
        return Emp.builder()
                .empno(this.empno)
                .ename(this.ename)
                .job(this.job)
                .mgr(this.mgr)
                .hiredate(this.hiredate)
                .sal(this.sal)
                .comm(this.comm)
                .dept(dept).build();
    }

}
