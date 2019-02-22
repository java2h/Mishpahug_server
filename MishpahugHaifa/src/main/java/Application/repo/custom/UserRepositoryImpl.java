package Application.repo.custom;

import Application.entities.EventEntity;
import org.omg.CORBA.Object;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
поля для фильтрации
-никнайм
-имя
-фамилия
-почта
-номер телефона
использовать like '%:parameter%'
 */
public class UserRepositoryImpl implements EventRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EventEntity> searchByFilter(HashMap<String, String> filter) {
        StringBuilder query = new StringBuilder();
        Map<String, String> parameters = new HashMap<>();
        query.append("select e from EventEntity e where 1=1 ");
        if (filter.containsKey("nickname")){
            query.append(" and e.nickname LIKE '%:nickname%'");
            parameters.put("nickname", filter.get("nickname"));
        }
        if (filter.containsKey("firstname")){
            query.append(" and e.firstname LIKE '%:firstname%'");
            parameters.put("firstname", filter.get("firstname"));
        }
        if (filter.containsKey("lastname")){
            query.append(" and e.lastname LIKE '%:lastname%'");
            parameters.put("lastname", filter.get("lastname"));
        }
        if (filter.containsKey("eMail")){
            query.append(" and e.eMail LIKE '%:eMail%'");
            parameters.put("eMail", filter.get("eMail"));
        }
        Query jpaQuery = entityManager.createQuery(query.toString());
        for (Map.Entry<String,String> map : parameters.entrySet()){
            jpaQuery.setParameter(map.getKey(), map.getValue());
        }

        return jpaQuery.getResultList();
    }
}