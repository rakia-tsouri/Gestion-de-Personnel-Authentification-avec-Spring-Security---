package gl2.example.personnel.controller;

import gl2.example.personnel.model.Employee;
import gl2.example.personnel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.ok("✅ Employé ajouté avec succès !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> existing = employeeService.getEmployeeById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        employee.setId(id);
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getEmployeeCount() {
        return ResponseEntity.ok(employeeService.countEmployees());
    }

    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le nom ne doit pas être vide");
        }

        List<Employee> result = employeeService.searchEmployeesByName(name);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun employé trouvé avec ce nom");
        }

        return result;
    }
}

//Invoke-WebRequest -Uri "http://localhost:8080/api/employees" -Method POST  -Headers @{ "Content-Type" = "application/js
//on" } -Body '{"name": "John Doe", "position": "Developer", "salary": 50000}' -Credential $credential
// Invoke-WebRequest -Uri "http://localhost:8080/api/employees" -Method GET -Credential $credential
