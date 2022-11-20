package br.com.tiagopedroso.dioparkingchallenge.service;

import br.com.tiagopedroso.dioparkingchallenge.exception.ParkingNotFoundException;
import br.com.tiagopedroso.dioparkingchallenge.model.Parking;
import br.com.tiagopedroso.dioparkingchallenge.repository.ParkingRepository;
import br.com.tiagopedroso.dioparkingchallenge.tool.Uuid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ParkingService {

    private final ParkingRepository repository;


    @Transactional(readOnly = true)
    public List<Parking> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Parking findById(String id) {
        return repository.findById(id).orElseThrow(
                () -> new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking model) {
        model.setId(Uuid.generate());
        model.setEntryDate(LocalDateTime.now());

        final var savedModel = repository.save(model);

        return savedModel;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    @Transactional
    public Parking update(String id, Parking updatedModel) {
        final var model = findById(id);

        model.setColor(updatedModel.getColor());
        model.setState(updatedModel.getState());
        model.setModel(updatedModel.getModel());
        model.setLicense(updatedModel.getLicense());

        final var savedModel = repository.save(model);

        return savedModel;
    }

    @Transactional
    public Parking checkOut(String id) {
        final var model = findById(id);

        model.setExitDate(LocalDateTime.now());
        model.setBill(ParkingCheckOut.getBill(model));

        return repository.save(model);
    }

}
