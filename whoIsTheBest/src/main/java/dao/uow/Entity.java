package dao.uow;

public class Entity {
	
	private Object entity;
	
	private IUnitOfWorkRepository repository;
	private EntityState state = EntityState.Unchanged;
	
	public Entity(Object entity) {
		super();
		this.entity = entity;
	}

	public EntityState getState() {
		return state;
	}
	
	public void setState(EntityState state) {
		this.state = state;
	}
	
	public void setRepository(IUnitOfWorkRepository repository) {
		this.repository = repository;
	}

	public enum EntityState{
		New, Unchanged, Changed, Deleted, Unknown
	}

	public IUnitOfWorkRepository getRepository() {
		return repository;
	}
	
	public Object getEntity() {
		return entity;
	}

	public void persist(){
		switch(getState()){
		case Changed:
			getRepository().persistUpdate(this);
			break;
		case Deleted:
			getRepository().persistDelete(this);
			break;
		case New:
			getRepository().persistAdd(this);
			break;
		default:
			break;
		
		}
	}
}
