package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Field_;
import models.FieldOptions;

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

public class FieldController extends Controller {

	//create field
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result addField(){
        JsonNode json = request().body().asJson();

        Field_ field = Json.fromJson(json, Field_.class);

        if (field.toString().equals("")){
            return badRequest("Missing parameter");
        }

        JPA.em().persist(field);
        return ok();
    }

	//get all fields
    @Transactional(readOnly = true)
    public Result listField(){
        CriteriaBuilder cb = JPA.em().getCriteriaBuilder();
        CriteriaQuery<Field_> cq = cb.createQuery(Field_.class);

        Root<Field_> root = cq.from(Field_.class);
        CriteriaQuery<Field_> fields = cq.select(root);

        TypedQuery<Field_> allQuery = JPA.em().createQuery(fields);

        JsonNode jsonNode = toJson(allQuery.getResultList());
        return ok(jsonNode);
    }
	
	//get active fields
    @Transactional(readOnly = true)
    public Result listActiveField(){
        CriteriaBuilder cb = JPA.em().getCriteriaBuilder();
        CriteriaQuery<Field_> cq = cb.createQuery(Field_.class);

        Root<Field_> root = cq.from(Field_.class);
        CriteriaQuery<Field_> fields = cq.select(root).where(cb.equal(root.get("active"), true));

        TypedQuery<Field_> allQuery = JPA.em().createQuery(fields);

        JsonNode jsonNode = toJson(allQuery.getResultList());
        return ok(jsonNode);
    }

	//get field by id
    @Transactional
    public Result getField(Long id){
        Field_ field = JPA.em().find(Field_.class, id);

        if (field==null){
            return notFound("Field not found");
        }

        return ok(toJson(field));
    }

	//update field
    @Transactional
    public Result updateField(Long id){
        Field_ field = JPA.em().find(Field_.class, id);

        if (field==null){
            return notFound("Field not found");
        }

        JsonNode json = request().body().asJson();

        Field_ fieldToBe = Json.fromJson(json, Field_.class);

        field.setLabel(fieldToBe.getLabel());
		field.setType(fieldToBe.getType());
		field.setRequired(fieldToBe.isRequired());
		field.setActive(fieldToBe.isActive());

        return ok();
    }

	//delete field
    @Transactional
    public Result deleteField(Long id){
        Field_ field = JPA.em().find(Field_.class, id);

        if (field==null){
            return notFound("Field not found");
        }

        JPA.em().remove(field);

        return ok();
    }
}
