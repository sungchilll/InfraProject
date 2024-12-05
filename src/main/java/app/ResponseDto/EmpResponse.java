package app.ResponseDto;

import java.time.LocalDate;

import app.entity.Emp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class EmpResponse {
	
	private Integer empno;

	private String ename;
	
	private String job;
	
	private Integer mgr;
	
	private LocalDate hiredate;
	
	private Double sal;
	
	private Double comm;
	
	private int deptno;
	
	public static EmpResponse from(Emp emp) {
		return new EmpResponse(
								emp.getEmpno(),
								emp.getEname(),
								emp.getJob(),
								emp.getMgr(),
								emp.getHiredate(),
								emp.getSal(),
								emp.getComm(),
								emp.getDept().getDeptno()
							);
	}

}
