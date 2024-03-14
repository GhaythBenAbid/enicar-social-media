package Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Models.Field;
import Services.FieldService;

@RestController
@RequestMapping("/api/field")
@CrossOrigin(origins = "http://localhost:4200/")
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping
    public ResponseEntity<Field> createField(@RequestBody Field field) {
        System.out.println(field);
        Field createdField = fieldService.createField(field);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdField);
    }

    @GetMapping
    public ResponseEntity<List<Field>> getAllFields() {
        List<Field> fields = fieldService.getAllFields();
        return ResponseEntity.ok(fields);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable int id) {
        Optional<Field> field = fieldService.getFieldById(id);
        return field.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Field> updateField(@PathVariable int id, @RequestBody Field updatedField) {
        Field field = fieldService.updateField(id, updatedField);
        return field != null ? ResponseEntity.ok(field) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable int id) {
        fieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }
}
