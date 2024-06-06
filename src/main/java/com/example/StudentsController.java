package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentsController {


    StudentsService studentsService;

    public StudentsController(StudentsService studentsService){
        this.studentsService = studentsService;
    }

    @PostMapping("/update")
    public String getStudent(@RequestBody Object request) {
        if(request instanceof  String) {
            return "Request type string hit : %s ".formatted("Some random msg");
        }
        else if(request instanceof List){
            List<Object> lst = (List<Object>)request;

            for(Object obj : lst)
                if(!(obj instanceof String))
                    return "400 bad request";

            return ((List<String>)request).toString();
        }

        return "400 bad Request";
    }


}
