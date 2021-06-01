package hu.webuni.hr.lilla.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.model.Position;
import hu.webuni.hr.lilla.repository.PositionRepository;

@Service
public class PositionService {

	@Autowired
	PositionRepository positionRepository;

	public void assignPosition(Employee employee) {
		String positionName = employee.getPosition().getName();
		if (positionName !=null) {
			List<Position> positions = positionRepository.findByName(positionName);
			Position position = null;
			if (positions.isEmpty()) {
				position = positionRepository.save(employee.getPosition());
			} else {
				position = positions.get(0);
			}
			employee.setPosition(position);
		}
	}
}
