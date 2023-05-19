package com.prueba.crudtaks.repository;

import com.prueba.crudtaks.entity.TaksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaksRepository extends JpaRepository<TaksEntity, Long> {
    List<TaksEntity> findTaksByUserId(Long idUser);

    @Query(value = """
            select *
            from taks t
            where t.task_user_id = (
            select u.user_id
            from user u
            where u.user_id = :idUser
            )
            and t.taks_initial_date > :fechaI
            and t.taks_initial_date < :fechaF
            """,nativeQuery = true)
    List<TaksEntity> findTaksByUserIdAndFecha(
            @Param("idUser") Long idUser, @Param("fechaI") Date fechaI, @Param("fechaF") Date fechaF);
}
