package org.jboss.tools.hibernate.runtime.v_4_0.internal;

import java.util.HashSet;
import java.util.Iterator;

import org.hibernate.mapping.KeyValue;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.RootClass;
import org.hibernate.mapping.Value;
import org.jboss.tools.hibernate.runtime.common.AbstractPersistentClassFacade;
import org.jboss.tools.hibernate.runtime.common.IFacade;
import org.jboss.tools.hibernate.runtime.spi.IFacadeFactory;
import org.jboss.tools.hibernate.runtime.spi.IPersistentClass;
import org.jboss.tools.hibernate.runtime.spi.ITable;
import org.jboss.tools.hibernate.runtime.spi.IValue;

public class PersistentClassFacadeImpl extends AbstractPersistentClassFacade {
	
	private ITable rootTable = null;
	private HashSet<IPersistentClass> subclasses = null;

	public PersistentClassFacadeImpl(
			IFacadeFactory facadeFactory,
			PersistentClass persistentClass) {
		super(facadeFactory, persistentClass);
	}

	public PersistentClass getTarget() {
		return (PersistentClass)super.getTarget();
	}

	@Override
	public void setIdentifier(IValue value) {
		assert value instanceof IFacade;
		assert ((IFacade)value).getTarget() instanceof KeyValue;
		assert getTarget() instanceof RootClass;
		((RootClass)getTarget()).setIdentifier((KeyValue)((IFacade)value).getTarget());
	}

	@Override
	public void setDiscriminator(IValue discr) {
		assert getTarget() instanceof RootClass;
		assert discr instanceof IFacade;
		((RootClass)getTarget()).setDiscriminator((Value)((IFacade)discr).getTarget());
	}

	@Override
	public void setProxyInterfaceName(String name) {
		getTarget().setProxyInterfaceName(name);
	}

	@Override
	public void setLazy(boolean b) {
		getTarget().setLazy(b);
	}

	@Override
	public Iterator<IPersistentClass> getSubclassIterator() {
		if (subclasses == null) {
			initializeSubclasses();
		}
		return subclasses.iterator();
	}
	
	private void initializeSubclasses() {
		Iterator<?> origin = getTarget().getSubclassIterator();
		subclasses = new HashSet<IPersistentClass>();
		while (origin.hasNext()) {
			subclasses.add(getFacadeFactory().createPersistentClass(origin.next()));
		}
	}

	@Override
	public boolean isCustomDeleteCallable() {
		return getTarget().isCustomDeleteCallable();
	}

	@Override
	public boolean isCustomInsertCallable() {
		return getTarget().isCustomInsertCallable();
	}

	@Override
	public boolean isCustomUpdateCallable() {
		return getTarget().isCustomUpdateCallable();
	}

	@Override
	public boolean isDiscriminatorInsertable() {
		return getTarget().isDiscriminatorInsertable();
	}

	@Override
	public boolean isDiscriminatorValueNotNull() {
		return getTarget().isDiscriminatorValueNotNull();
	}

	@Override
	public boolean isDiscriminatorValueNull() {
		return getTarget().isDiscriminatorValueNull();
	}

	@Override
	public boolean isExplicitPolymorphism() {
		return getTarget().isExplicitPolymorphism();
	}

	@Override
	public boolean isForceDiscriminator() {
		return getTarget().isForceDiscriminator();
	}

	@Override
	public boolean isInherited() {
		return getTarget().isInherited();
	}

	@Override
	public boolean isJoinedSubclass() {
		return getTarget().isJoinedSubclass();
	}

	@Override
	public boolean isLazy() {
		return getTarget().isLazy();
	}

	@Override
	public boolean isLazyPropertiesCacheable() {
		return getTarget().isLazyPropertiesCacheable();
	}

	@Override
	public boolean isMutable() {
		return getTarget().isMutable();
	}

	@Override
	public boolean isPolymorphic() {
		return getTarget().isPolymorphic();
	}

	@Override
	public boolean isVersioned() {
		return getTarget().isVersioned();
	}

	@Override
	public int getBatchSize() {
		return getTarget().getBatchSize();
	}

	@Override
	public String getCacheConcurrencyStrategy() {
		return getTarget().getCacheConcurrencyStrategy();
	}

	@Override
	public String getCustomSQLDelete() {
		return getTarget().getCustomSQLDelete();
	}

	@Override
	public String getCustomSQLInsert() {
		return getTarget().getCustomSQLInsert();
	}

	@Override
	public String getCustomSQLUpdate() {
		return getTarget().getCustomSQLUpdate();
	}

	@Override
	public String getDiscriminatorValue() {
		return getTarget().getDiscriminatorValue();
	}

	@Override
	public String getLoaderName() {
		return getTarget().getLoaderName();
	}

	@Override
	public int getOptimisticLockMode() {
		return getTarget().getOptimisticLockMode();
	}

	@Override
	public String getTemporaryIdTableDDL() {
		return getTarget().getTemporaryIdTableDDL();
	}

	@Override
	public String getTemporaryIdTableName() {
		return getTarget().getTemporaryIdTableName();
	}

	@Override
	public String getWhere() {
		return getTarget().getWhere();
	}

	@Override
	public ITable getRootTable() {
		if (rootTable == null) {
			rootTable = getFacadeFactory().createTable(getTarget().getRootTable());
		}
		return rootTable;
	}
	
	

}
