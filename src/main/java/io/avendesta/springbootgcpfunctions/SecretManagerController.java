package io.avendesta.springbootgcpfunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretManagerController {

    @Autowired
    private SecretManagerService secretManagerService;

    @GetMapping("/")
    public String home(){
        return "Welcome Home!";
    }
    @GetMapping("/getAllSecret")
    public String getAllSecrets() {
        return secretManagerService.getAllSecrets();
    }
    @GetMapping("/getSecret")
    public String getSecret() {
        return secretManagerService.getSecret();
    }
}
