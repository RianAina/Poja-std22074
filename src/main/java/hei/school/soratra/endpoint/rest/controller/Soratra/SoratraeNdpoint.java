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

    @GetMapping("/soratra/{id}")
    public ResponseEntity<Map<String, String>> retrievePoeme(@PathVariable("id") String id) {
        // Vérifier si l'ID existe dans la structure de données
        if (!poemes.containsKey(id)) {
            // Si l'ID n'existe pas, renvoyer une réponse HTTP 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Récupérer la phrase poétique associée à l'ID
        String poeme = poemes.get(id);

        // Construire les URL originales et transformées
        String originalUrl = "https://original.url/" + id;
        String transformedUrl = "https://transformed.url/" + id;

        // Construire le corps de la réponse JSON
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("original_url", originalUrl);
        responseBody.put("transformed_url", transformedUrl);

        // Renvoyer une réponse HTTP 200 OK avec le corps de la réponse JSON
        return ResponseEntity.ok(responseBody);
    }
}
}
