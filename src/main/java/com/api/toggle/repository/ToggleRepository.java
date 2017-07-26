package com.api.toggle.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.api.toggle.model.ToggleTypeEnum;
import com.api.toggle.model.entity.ServiceApplication;
import com.api.toggle.model.entity.Toggle;

@Repository
@Transactional
public class ToggleRepository extends AbstractRepository {
	
	public void saveToggle(Toggle toggle) {
		persist(toggle);
	}

	public void deleteToggle(Toggle toggle) {
		delete(toggle);
	}
	
	public void updateToggle(Toggle toggle){
		merge(toggle);
	}

	public List<Toggle> listAllToggles(ServiceApplication service) {
		StringBuilder queryStb = new StringBuilder("select tg from Toggle tg ")
				.append("LEFT JOIN tg.serviceApplicationList sa ")
				.append("where (sa.id =:id AND ")
				.append("tg.type =:typeSpecific) ")
				.append("OR tg.type !=:typeSpecific ")
				.append("GROUP BY tg.id ");
		
		Query query = getSession().createQuery (queryStb.toString());
		query.setParameter("id", service.getId());
		query.setParameter("typeSpecific", ToggleTypeEnum.SPECIFIC);
		@SuppressWarnings("unchecked")
		List<Toggle> toggles = query.list();
		return toggles;
	}
	
	public Toggle findToggleByName(String name){
		if (name.isEmpty()){
			return null;
		}
		StringBuilder queryStb = new StringBuilder("from Toggle tg ")
				.append("where tg.name =:name");
		Query query = getSession().createQuery (queryStb.toString());
		query.setParameter("name", name);
		Toggle toggleResult = (Toggle)query.uniqueResult();
		return toggleResult; 
	}
}
