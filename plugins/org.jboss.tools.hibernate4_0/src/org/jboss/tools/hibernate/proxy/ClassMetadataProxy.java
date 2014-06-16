package org.jboss.tools.hibernate.proxy;

import java.util.ArrayList;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.jboss.tools.hibernate.spi.IClassMetadata;
import org.jboss.tools.hibernate.spi.ISession;
import org.jboss.tools.hibernate.spi.IType;

public class ClassMetadataProxy implements IClassMetadata {
	
	private ClassMetadata target = null;
	private IType[] propertyTypes = null;
	private IType identifierType = null;

	public ClassMetadataProxy(ClassMetadata classMetadata) {
		target = classMetadata;
	}

	@Override
	public String getEntityName() {
		return target.getEntityName();
	}

	@Override
	public String getIdentifierPropertyName() {
		return target.getIdentifierPropertyName();
	}

	@Override
	public String[] getPropertyNames() {
		return target.getPropertyNames();
	}

	@Override
	public IType[] getPropertyTypes() {
		if (propertyTypes == null) {
			initializePropertyTypes();
		}
		return propertyTypes;
	}
	
	private void initializePropertyTypes() {
		Type[] origin = target.getPropertyTypes();
		ArrayList<IType> propertyTypes = new ArrayList<IType>(origin.length);
		for (Type type : origin) {
			propertyTypes.add(new TypeProxy(type));
		}
		this.propertyTypes = propertyTypes.toArray(new IType[origin.length]);
	}

	@Override
	public Class<?> getMappedClass() {
		return target.getMappedClass();
	}

	@Override
	public IType getIdentifierType() {
		if (identifierType == null) {
			identifierType = new TypeProxy(target.getIdentifierType());
		}
		return identifierType;
	}

	@Override
	public Object getPropertyValue(Object object, String name) {
		return target.getPropertyValue(object, name);
	}

	@Override
	public boolean hasIdentifierProperty() {
		return target.hasIdentifierProperty();
	}

	@Override
	public Object getIdentifier(Object object) {
		return target.getIdentifier(object);
	}

	@Override
	public Object getIdentifier(Object object, ISession session) {
		Object result = null;
		if (session instanceof SessionProxy) {
			SessionImplementor impl = (SessionImplementor)((SessionProxy)session).getTarget();
			result = target.getIdentifier(object, impl);
		}
		return result;
	}

}