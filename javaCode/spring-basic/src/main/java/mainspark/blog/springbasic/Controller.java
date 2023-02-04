package mainspark.blog.springbasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/entry")
public class Controller {
    @GetMapping("/{id}")
    private Mono<String> getEmployeeById(@PathVariable String id) {
        return Mono.just(id);
    }
}
