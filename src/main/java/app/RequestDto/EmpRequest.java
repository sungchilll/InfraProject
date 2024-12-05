package app.RequestDto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class EmpRequest {
	
	private Integer empno;

	private String ename;
	
	private String job;
	
	private Integer mgr;
	
	private LocalDate hiredate;
	
	private Double sal;
	
	private Double comm;
	
	private Integer deptno;
	
	
}
