package repository;

import java.util.List;

public interface IRepository<Entity> {

	void Add(Entity entity);
	void Update(Entity entity);
	void Delete(Entity entity);
	List<Entity> GetAll();
	Entity GetById(Long id);
}
