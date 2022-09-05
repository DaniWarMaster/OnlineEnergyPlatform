package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.Sensors;

import java.util.List;
import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensors, Integer> {
    @Query(value = "SELECT s " +
            "FROM Sensors s " +
            "WHERE s.device = null ")
    List<Sensors> findEmptySensors();
}
