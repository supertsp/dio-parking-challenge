package br.com.tiagopedroso.dioparkingchallenge.service;


import br.com.tiagopedroso.dioparkingchallenge.exception.UserNotFoundException;
import br.com.tiagopedroso.dioparkingchallenge.model.User;
import br.com.tiagopedroso.dioparkingchallenge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(id)
                );
    }

    public User findByUsername(String username) {
        final var model = repository.findByUsername(username);

        if (model == null) {
            throw new UserNotFoundException(username);
        }

        return model;
    }

    public User create(User model) {
        //encrypting before saving to the bank
        model.setPassword(
                encoder.encode(
                        model.getPassword()
                )
        );

        final var savedModel = repository.save(model);

        return savedModel;
    }
}
