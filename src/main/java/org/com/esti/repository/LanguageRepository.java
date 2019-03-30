package org.com.esti.repository;

import org.com.esti.domain.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

    Language findByMessageKeyAndLocale(String key, String local);
}
