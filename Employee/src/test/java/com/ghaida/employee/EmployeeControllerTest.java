package com.ghaida.employee;

import com.ghaida.employee.dto.EmployeeRequestDTO;
import com.ghaida.employee.entity.Department;
import com.ghaida.employee.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import tools.jackson.databind.ObjectMapper;
import com.ghaida.employee.repository.DepartmentRepository;
import com.ghaida.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @Test
    void createEmployee() throws Exception {
        Department department = new Department();
        department.setDepartmentName("Engineering");
        departmentRepository.save(department);

        EmployeeRequestDTO dto = new EmployeeRequestDTO();
        dto.setName("Ghaida");
        dto.setEmail("ghaida@test.com");
        dto.setPhone("123456789");
        dto.setPosition("software engineering");
        dto.setDepartmentId(department.getId());

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value("Ghaida"))
                .andExpect(jsonPath("$.data.email").value("ghaida@test.com"));
    }

    @Test
    void getEmployeeById() throws Exception {
        Department department = new Department();
        department.setDepartmentName("Engineering");
        departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setName("Ghaida");
        employee.setEmail("Ghaida@test.com");
        employee.setPhone("123456789");
        employee.setPosition("software engineering");
        employee.setDepartment(department);
        employeeRepository.save(employee);

        Integer id = employee.getId();

        mockMvc.perform(get("/employees/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Ghaida"))
                .andExpect(jsonPath("$.data.id").value(id));
    }

}