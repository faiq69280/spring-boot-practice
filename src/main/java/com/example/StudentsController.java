package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentsController {

    @PostMapping("/update")
    public String getStudent(@RequestBody Object request) {
        if(request instanceof  String) {
            return "Request type string hit : %s ".formatted((String)request);
        }
        else if(request instanceof List){
            List<Object> lst = (List<Object>)request;

            for(Object obj : lst)
                if(!(obj instanceof String))
                    return "Bad request";

            return ((List<String>)request).toString();
        }

        return "Bad Request";
    }


}
