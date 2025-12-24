package org.anas.mcpserver.tools;

import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class McpTools {

    @McpTool(name="getEmployee", description = "Get information about the employee" )
    public Employee getEmployee(@McpArg(description = "The employee's name") String name) {
        return new Employee(name, 12300, 4);
    }

    @McpTool(name="getAllEmployees", description = "Get all the employees")
    public List<Employee> getAllEmployees() {
        return List.of(
                new Employee("Anas", 14000, 1),
                new Employee("Anas2", 15000, 2),
                new Employee("Anas3", 16000, 3)
        );
    }

}

record Employee(String name, double salary, int seniority) {}
