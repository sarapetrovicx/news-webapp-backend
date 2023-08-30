package rs.raf.wpbackend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.wpbackend.entities.User;
import rs.raf.wpbackend.repositories.user.UserRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    UserRepository userRepository;

    public String login(String email, String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUser(email);
        if (user == null || !user.getHashedPassword().equals(hashedPassword) || user.getStatus().equals("inactive")) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("email", email)
                .withClaim("first_name", user.getFirst_name())
                .withClaim("last_name", user.getLast_name())
                .withClaim("role", user.getRole())
                .withClaim("status", user.getStatus())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
        User user = this.userRepository.findUser(email);

        if (user == null){
            return false;
        }
        Claim roleClaim = jwt.getClaim("role");
        String role = roleClaim.asString();

        if (!role.equals("admin")) {
            return false;
        }

        return true;
    }

    public User edit(User user, Integer id){
        return this.userRepository.edit(user, id);
    }

    public User add(User user) {
        return this.userRepository.add(user);
    }

    public List<User> all() {
        return this.userRepository.all();
    }

    public User find(String email) {
        return this.userRepository.findUser(email);
    }

    public void delete(Integer id) {
        this.userRepository.delete(id);
    }
    public void activate(Integer id) {
        this.userRepository.activate(id);
    }
    public void deactivate(Integer id) {
        this.userRepository.deactivate(id);
    }
}
