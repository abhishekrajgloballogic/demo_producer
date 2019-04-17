package com.gl.demo_producer.service;

import com.gl.demo_producer.entity.User;
import com.gl.demo_producer.repo.UserRepo;
import com.gl.demo_producer.util.PropertyFileReader;
import com.gl.demo_producer.util.UpdateMapper;
import com.gl.demo_producer.validator.UserValidator;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UpdateMapper updateMapper;

    @Autowired
    private PropertyFileReader propertyFileReader;

    @Transactional
    public User saveUser(User user)  {
       UserValidator.isValidUserRequest(user);
        return userRepo.save(user);
    }

    @Transactional
    public Optional<User> getById(Long id) {
        Optional<User> user = userRepo.findById(id);
      /*  if (!user.isPresent())
            throw new BadRequestException_400(propertyFileReader.get("user.not.found"), HttpStatus.NOT_FOUND.value());*/
        return user;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    public User updateUser(User updateUser, Long id) throws InvocationTargetException, IllegalAccessException {
        User user = userRepo.findById(id).get();
       updateMapper.copyProperties(updateUser,user);
        return userRepo.save(user);
    }


}
