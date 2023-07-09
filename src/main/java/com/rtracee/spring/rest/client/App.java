package com.rtracee.spring.rest.client;

import com.rtracee.spring.rest.client.configuration.Config;
import com.rtracee.spring.rest.client.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        Communication communication = applicationContext.getBean("communication", Communication.class);

        List<Employee> employees = communication.getAllEmployees();
        System.out.println(employees);

        Employee employee = communication.getEmployeeById(1);
        System.out.println(employee);

//        Employee newEmployee = new Employee("Sveta", "Sokolova", "HR", 900);
////        communication.addEmployee(newEmployee);
//        newEmployee.setId(8);
//        newEmployee.setDepartment("It");
//        communication.addEmployee(employee);

        communication.deleteEmployee(7);
    }
}
