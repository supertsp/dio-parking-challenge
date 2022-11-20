package br.com.tiagopedroso.dioparkingchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tiagopedroso.dioparkingchallenge.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {
}
