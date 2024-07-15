package raf.diplomski.mmgcritic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.diplomski.mmgcritic.data.entities.Item;
@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
