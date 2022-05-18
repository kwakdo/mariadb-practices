package hr.dao.test;

import java.util.List;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;

public class EmployeeDaoTest {

	public static void main(String[] args) {
		testfindByFirstNameOrLastName("ken");

	}
	public static void testfindByFirstNameOrLastName(String name) {
		List<EmployeeVo> list = new EmployeeDao().findByFirstNameOrLastName(name);
		for(EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}

}
