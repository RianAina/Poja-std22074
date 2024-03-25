import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SoratraController {

    private final Map<String, String> poemes = new HashMap<>();

    @PutMapping("/soratra/{id}")
    public ResponseEntity<Void> processSoratra(@RequestBody String poeme, @RequestHeader("id") String id) {

        if (!poeme.equals(poeme.toLowerCase())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        poemes.put(id, poeme);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
