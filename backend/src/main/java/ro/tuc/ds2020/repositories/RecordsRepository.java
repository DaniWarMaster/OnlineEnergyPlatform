package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.Records;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecordsRepository extends JpaRepository<Records, Integer> {

}
