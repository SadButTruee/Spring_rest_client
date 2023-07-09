package com.rtracee.spring.rest.client;

import com.rtracee.spring.rest.client.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private final String BASE_URL = "http://localhost:8080/Spring_rest/api/employees";

    @Autowired
    private RestTemplate restTemplate;

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(
                BASE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});

        List<Employee> allEmployees = responseEntity.getBody();
        return allEmployees;
    }

    public Employee getEmployeeById(int id) {
        Employee employee = restTemplate.getForObject(BASE_URL + "/" + id, Employee.class);

        return employee;
    }

    public void addEmployee(Employee employee) {
        int id = employee.getId();
        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    BASE_URL, employee, String.class);
            System.out.println("New employee was added tp DB");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(BASE_URL, employee);
            System.out.println("Employee whith id " + id + " was updated");
        }
    }

    public void deleteEmployee(int id) {
        restTemplate.delete(BASE_URL + "/" + id);
        System.out.println("Employee with id " + id + " was deleted");
    }
}
