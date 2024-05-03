package com.example.pi_backend2.Service;

import com.example.pi_backend2.Entity.Prof;
import com.example.pi_backend2.Entity.User;
import com.example.pi_backend2.Repository.TeacherRepository;
import com.example.pi_backend2.Repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Data


@Service
public class UserService {
    public final UserRepository userRepository;
    public final TeacherRepository teacherRepository;

    public UserService(UserRepository userRepository, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
    }

    public User addUser(User user) {
        Optional<User> existingUser = userRepository.findByfirstName(user.getUserName());
        if (!existingUser.isPresent()) {
            userRepository.save(user);
        }
        return user;

    }
    public User updateUser(String userName, User updatedUser) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        User existingUser = userOptional.get();

        // Update other fields as needed
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setEmail(updatedUser.getEmail());

        // Save the updated user
        return userRepository.save(existingUser);

    }


    public Prof updateProf(String userName, Prof updatedTeacher) {

        Optional<Prof> teacherOptional = teacherRepository.findByUserName(userName);
        Prof existingTeacher = teacherOptional.get();

            existingTeacher.setNom(updatedTeacher.getNom());
            existingTeacher.setPrenom(updatedTeacher.getPrenom());
            existingTeacher.setNumeroTel(updatedTeacher.getNumeroTel());
            existingTeacher.setSpecialite(updatedTeacher.getSpecialite());
            existingTeacher.setAdresse(updatedTeacher.getAdresse());
            existingTeacher.setPassword(updatedTeacher.getPassword());
//            existingTeacher.setCodeProf(updatedTeacher.getCodeProf());
            // Update other fields as needed

            // Save the updated user
            return teacherRepository.save(existingTeacher);

    }

//    public void deleteUser(String userName) {
//        Optional<User> userOptional = userRepository.findByUserName(userName);
//
//        if (userOptional.isPresent()) {
//            userRepository.delete(userOptional.get());
//        } else {
//
//        }
//    }
    @Transactional(readOnly = true)

    public User authenticate(String userName, String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            // Paramètres invalides
            System.out.println("Invalid parameters for authentication");
            return null;
        }
        // Recherchez l'utilisateur par nom d'utilisateur dans la base de données
        return userRepository.findByUserNameAndPassword(userName, password);
    }
    public Prof authenticateTeacher(String userName, String password) {
        if (StringUtils.isEmpty(password)||StringUtils.isEmpty(userName)) {
            // Paramètres invalides
            System.out.println("Invalid parameters for authentication");
            return null;
        }
        // Recherchez l'utilisateur par nom d'utilisateur dans la base de données
        return teacherRepository.findByUserNameAndPassword(userName, password);
    }


    public String authentificateAndGenerateToken(String username, String password) {

        return "true ";
    }
//    public List<Film> getFilmsFavorisByLoggedInUser(String userName) {
//        User user=userRepository.getByUserName(userName);
//         List<Film> films=filmRepository.getByAddedByUser(user) ;
//         return films;
//    }
}



