package org.jboss.tools.hibernate.runtime.v_4_0.internal;

import org.hibernate.mapping.Collection;
import org.hibernate.mapping.ManyToOne;
import org.hibernate.mapping.OneToMany;
import org.hibernate.mapping.ToOne;
import org.hibernate.mapping.Value;
import org.jboss.tools.hibernate.proxy.PersistentClassProxy;
import org.jboss.tools.hibernate.runtime.common.AbstractValueFacade;
import org.jboss.tools.hibernate.runtime.spi.IFacadeFactory;
import org.jboss.tools.hibernate.runtime.spi.IPersistentClass;

public class ValueFacadeImpl extends AbstractValueFacade {
		
	public ValueFacadeImpl(IFacadeFactory facadeFactory, Value value) {
		super(facadeFactory, value);
	}

	public Value getTarget() {
		return (Value)super.getTarget();
	}

	@Override
	public boolean isInverse() {
		assert getTarget() instanceof Collection;
		return ((Collection)getTarget()).isInverse();
	}

	@Override
	public IPersistentClass getAssociatedClass() {
		assert getTarget() instanceof OneToMany;
		return ((OneToMany)getTarget()).getAssociatedClass() != null ? 
				getFacadeFactory().createPersistentClass(((OneToMany)getTarget()).getAssociatedClass()) :
					null;
	}

	@Override
	public void setLazy(boolean b) {
		assert getTarget() instanceof Collection;
		((Collection)getTarget()).setLazy(b);
	}

	@Override
	public void setRole(String role) {
		assert getTarget() instanceof Collection;
		((Collection)getTarget()).setRole(role);
	}

	@Override
	public void setReferencedEntityName(String name) {
		assert (getTarget() instanceof ToOne || getTarget() instanceof ManyToOne);
		if (isToOne()) {
			((ToOne)getTarget()).setReferencedEntityName(name);
		} else if (isOneToMany()) {
			((OneToMany)getTarget()).setReferencedEntityName(name);
		}
	}

	@Override
	public void setAssociatedClass(IPersistentClass persistentClass) {
		assert getTarget() instanceof OneToMany;
		assert persistentClass instanceof PersistentClassProxy;
		((OneToMany)getTarget()).setAssociatedClass(((PersistentClassProxy)persistentClass).getTarget());
	}

}