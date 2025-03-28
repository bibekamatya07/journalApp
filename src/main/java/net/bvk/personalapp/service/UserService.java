package net.bvk.personalapp.service;

import lombok.extern.slf4j.Slf4j;
import net.bvk.personalapp.entity.User;
import net.bvk.personalapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public boolean saveNewUser(User user){
        try{
            // encodes the password before saving in db
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }catch (Exception e){
            log.error("New User {} not saved ", user.getUsername(), e);
            return false;
        }
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public void deleteByUserName(String username){
        userRepository.deleteByUsername(username);
    }

    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }
}
