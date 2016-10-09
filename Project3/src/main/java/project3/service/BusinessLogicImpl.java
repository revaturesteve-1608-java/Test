package project3.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project3.dto.Person;
import project3.simpledao.SimpleDao;

@Service
public class BusinessLogicImpl implements BusinessLogic{
	
	@Autowired
	SimpleDao dao;
	
	@Override
	public Person getPersonById(int id) {
		return dao.getPersonById(id);
	}
	
	@Override
	public Person getPersonByUsername(String username){
		return dao.getPersonByUsername(username);
	}
	
	@Override
	@Transactional
	public void updateTempPerson(String username, String pass, String newUsername){
		dao.updateTempPerson(username, pass, newUsername);
	}
}
