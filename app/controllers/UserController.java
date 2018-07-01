package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import models.UserLoginDTO;
import play.*;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static play.libs.Json.toJson;

public class UserController extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

	//create user
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result addUser(){
        JsonNode json = request().body().asJson();

        User user = Json.fromJson(json, User.class);

        if (user.toString().equals("")){
            return badRequest("Missing parameter");
        }

        JPA.em().persist(user);
        return ok();
    }

	//get all users
    @Transactional(readOnly = true)
    public Result listUser(){
        CriteriaBuilder cb = JPA.em().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);
        CriteriaQuery<User> users = cq.select(root);

        TypedQuery<User> allQuery = JPA.em().createQuery(users);

        JsonNode jsonNode = toJson(allQuery.getResultList());
        return ok(jsonNode);
    }


	//get user by id
    @Transactional
    public Result getUser(Long id){
        User user = JPA.em().find(User.class, id);

        if (user==null){
            return notFound("User not found");
        }

        return ok(toJson(user));
    }

	//update user
    @Transactional
    public Result updateUser(Long id){
        User user = JPA.em().find(User.class, id);

        if (user==null){
            return notFound("User not found");
        }

        JsonNode json = request().body().asJson();

        User userToBe = Json.fromJson(json, User.class);

        user.setFirstName(userToBe.getFirstName());
		user.setLastName(userToBe.getLastName());
		user.setEmail(userToBe.getEmail());
		user.setPassword(userToBe.getPassword());
		user.setFirstName(userToBe.getPhone());

        return ok();
    }

	//delete user
    @Transactional
    public Result deleteUser(Long id){
        User user = JPA.em().find(User.class, id);

        if (user==null){
            return notFound("User not found");
        }

        JPA.em().remove(user);

        return ok();
    }

	
    @Transactional
    public Result searchUserToAuth(){

		JsonNode json = request().body().asJson();

        UserLoginDTO userToLogin = Json.fromJson(json, UserLoginDTO.class);
	
        TypedQuery<User> query = JPA.em().createQuery
                ("select p from User p where p.email = :email and p.password = :password", User.class)
                .setParameter("email", userToLogin.getEmail())
				.setParameter("password", userToLogin.getPassword());

        try {
            User user = query.getSingleResult();
            return ok(toJson(user));

        }catch (NoResultException e){
            return notFound("User not found");
        }

    }
}
