package io.github.eliseoorellana.foro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.eliseoorellana.foro.errores.TratadorDeErrores.TopicNotFoundException;
import io.github.eliseoorellana.foro.errores.ValidacionDeIntegridad;
import io.github.eliseoorellana.foro.model.Topic;
import io.github.eliseoorellana.foro.repository.TopicRepository;
import io.github.eliseoorellana.foro.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    public TopicRepository topicRepository;
    
    @Autowired
    private UserRepository userRepository;

  

    @GetMapping
    public List<Topic> getAlltopics() {
        return topicRepository.findAll();
    }
    
    @PostMapping
    // @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        // Verificar si el usuario existe
        if (!userRepository.existsById(topic.getUserId())) {
            throw new EntityNotFoundException("User not found");
        }

        Topic savedTopic = topicRepository.save(topic);
        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isPresent()) {
            return new ResponseEntity<>(topic.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    

@DeleteMapping("/{id}")
    // @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        if (!topicRepository.existsById(id)) {
            throw new ValidacionDeIntegridad("Topic with ID " + id + " does not exist");
        }
        topicRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
