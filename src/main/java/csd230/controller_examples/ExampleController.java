package csd230.controller_examples;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("controller")
public class ExampleController {

    /*
    * A Spring @Controller can receive parameters through various mechanisms, including path variables, query parameters, request bodies, headers and multipart data. These parameters are used to process the incoming request and prepare a model for the view.
    * Here's a detailed example of how a @Controller receives parameters:
    * Path Variables: These are parameters embedded directly in the URI. They are extracted using the @PathVariable annotation.
    */

    /* In this example, the @PathVariable String name in the getEmployeeInJSON and
     * getEmployeeInXML methods extracts the value of name from the URL path, such
     * as /employees/Bob. The @PathVariable annotation maps the variable within
     * the curly braces {} in the @RequestMapping path to the method parameter.
     * */
    Employee employee = new Employee();
    @RequestMapping(value = "/employee/{name}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Employee getEmployeeInJSON(@PathVariable String name) {
        employee.setName(name);
        employee.setEmail("fred.carella@saultcollege.ca");
        return employee;
    }

    @RequestMapping(value = "/employee/{name}.xml", method = RequestMethod.GET, produces = "application/xml")
    public @ResponseBody Employee getEmployeeInXML(@PathVariable String name) {
        employee.setName(name);
        employee.setEmail("fred.carella@saultcollege.ca");
        return employee;
    }

    /*
    * Query Parameters:
    * These are parameters appended to the URL after a question mark (?firstname="...":lastname="...").
    * They are accessed using the @RequestParam annotation. While not explicitly shown in the
    * provided example, they would be used like this:
    * */
    @GetMapping(value = "/employeeById")
    public String getEmployee(Model model, @RequestParam(name="id", required=true) String itemId, @RequestParam(name="name", required=false) String itemName){
        //logic here

        // find employee by id
        // here we just make it
        employee = new Employee(1L,"Fred","fred.carella@gmail.com");
        model.addAttribute("employee", employee);
        return "employee";
    }

    /*
    * Request Body: For requests that send data in the body (like POST or PUT requests),
    * the data can be mapped to a method parameter using the @RequestBody annotation.
    *
    * */
    @GetMapping(value = "/employeeForm")
    public String getEmployee(Model model){
        employee = new Employee(1L,"Fred","fred.carella@gmail.com");
        model.addAttribute("employee", employee);
        return "employeeForm";

    }
    @PostMapping(value = "/employeeForm")
    public String createEmployee(@RequestBody Employee employee) {
        //logic here to save the employee object
        return "viewName";
    }


}
