package Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Models.Field;
import Repositories.FieldRepository;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    public Field createField(Field field) {
        return fieldRepository.save(field);
    }

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Optional<Field> getFieldById(int id) {
        return fieldRepository.findById((long) id);
    }

    public Field updateField(int id, Field updatedField) {
        if (fieldRepository.existsById((long) id)) {
            updatedField.setId(id);
            return fieldRepository.save(updatedField);
        } else {
            return null;
        }
    }

    public void deleteField(int id) {
        fieldRepository.deleteById((long) id);
    }
}
