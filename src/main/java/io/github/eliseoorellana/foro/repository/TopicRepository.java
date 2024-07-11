package io.github.eliseoorellana.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.eliseoorellana.foro.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
