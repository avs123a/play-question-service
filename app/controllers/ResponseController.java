package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Response;
import models.ResponseField;

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

public class ResponseController extends Controller {

	//create response
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result addResponse(){
        JsonNode json = request().body().asJson();

        Response response = Json.fromJson(json, Response.class);

        if (response.toString().equals("")){
            return badRequest("Missing parameter");
        }

        JPA.em().persist(response);
        return ok();
    }

	//get all response
    @Transactional(readOnly = true)
    public Result listResponse(){
        CriteriaBuilder cb = JPA.em().getCriteriaBuilder();
        CriteriaQuery<Response> cq = cb.createQuery(Response.class);

        Root<Response> root = cq.from(Response.class);
        CriteriaQuery<Response> responses = cq.select(root);

        TypedQuery<Response> allQuery = JPA.em().createQuery(responses);

        JsonNode jsonNode = toJson(allQuery.getResultList());
        return ok(jsonNode);
    }
	
	//get responses by user
    @Transactional(readOnly = true)
    public Result listUserResponse(Long userId){
        CriteriaBuilder cb = JPA.em().getCriteriaBuilder();
        CriteriaQuery<Response> cq = cb.createQuery(Response.class);

        Root<Response> root = cq.from(Response.class);
        CriteriaQuery<Response> responses = cq.select(root).where(cb.equal(root.get("userId"), userId));

        TypedQuery<Response> allQuery = JPA.em().createQuery(responses);

        JsonNode jsonNode = toJson(allQuery.getResultList());
        return ok(jsonNode);
    }
	
	


	//get response by id
    @Transactional
    public Result getResponse(Long id){
        Response response = JPA.em().find(Response.class, id);

        if (response==null){
            return notFound("Response not found");
        }

        return ok(toJson(response));
    }

	//update response
    @Transactional
    public Result updateResponse(Long id){
        Response response = JPA.em().find(Response.class, id);

        if (response==null){
            return notFound("Response not found");
        }

        JsonNode json = request().body().asJson();

        Response responseToBe = Json.fromJson(json, Response.class);

        response.setUserId(responseToBe.getUserId());
		response.setUser(responseToBe.getUser());
		response.setResponseFields(responseToBe.getResponseFields());

        return ok();
    }

	//delete response
    @Transactional
    public Result deleteResponse(Long id){
        Response response = JPA.em().find(Response.class, id);

        if (response==null){
            return notFound("Response not found");
        }

        JPA.em().remove(response);

        return ok();
    }
}
