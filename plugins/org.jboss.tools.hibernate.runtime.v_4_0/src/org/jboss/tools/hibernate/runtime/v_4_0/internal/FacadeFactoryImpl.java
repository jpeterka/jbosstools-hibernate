package org.jboss.tools.hibernate.runtime.v_4_0.internal;

import org.hibernate.Criteria;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.reveng.DefaultDatabaseCollector;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.ForeignKey;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.tool.ide.completion.HQLCodeAssist;
import org.hibernate.tool.ide.completion.HQLCompletionProposal;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.jboss.tools.hibernate.proxy.ClassMetadataProxy;
import org.jboss.tools.hibernate.proxy.CollectionMetadataProxy;
import org.jboss.tools.hibernate.proxy.ColumnProxy;
import org.jboss.tools.hibernate.proxy.ConfigurationProxy;
import org.jboss.tools.hibernate.proxy.CriteriaProxy;
import org.jboss.tools.hibernate.proxy.DatabaseCollectorProxy;
import org.jboss.tools.hibernate.proxy.EntityMetamodelProxy;
import org.jboss.tools.hibernate.proxy.EnvironmentProxy;
import org.jboss.tools.hibernate.proxy.ForeignKeyProxy;
import org.jboss.tools.hibernate.proxy.HQLCodeAssistProxy;
import org.jboss.tools.hibernate.proxy.HQLCompletionProposalProxy;
import org.jboss.tools.hibernate.proxy.HibernateMappingExporterExtension;
import org.jboss.tools.hibernate.proxy.HibernateMappingExporterProxy;
import org.jboss.tools.hibernate.runtime.common.AbstractFacadeFactory;
import org.jboss.tools.hibernate.runtime.spi.IClassMetadata;
import org.jboss.tools.hibernate.runtime.spi.ICollectionMetadata;
import org.jboss.tools.hibernate.runtime.spi.IColumn;
import org.jboss.tools.hibernate.runtime.spi.IConfiguration;
import org.jboss.tools.hibernate.runtime.spi.ICriteria;
import org.jboss.tools.hibernate.runtime.spi.IDatabaseCollector;
import org.jboss.tools.hibernate.runtime.spi.IEntityMetamodel;
import org.jboss.tools.hibernate.runtime.spi.IEnvironment;
import org.jboss.tools.hibernate.runtime.spi.IForeignKey;
import org.jboss.tools.hibernate.runtime.spi.IHQLCodeAssist;
import org.jboss.tools.hibernate.runtime.spi.IHQLCompletionProposal;
import org.jboss.tools.hibernate.runtime.spi.IHibernateMappingExporter;

public class FacadeFactoryImpl extends AbstractFacadeFactory {
	
	public ClassLoader getClassLoader() {
		return FacadeFactoryImpl.class.getClassLoader();
	}
	
	@Override
	public IClassMetadata createClassMetadata(Object target) {
		return new ClassMetadataProxy(this, (ClassMetadata)target);
	}
	
	@Override
	public ICollectionMetadata createCollectionMetadata(Object target) {
		return new CollectionMetadataProxy(this, (CollectionMetadata)target);
	}
	
	@Override
	public IColumn createColumn(Object target) {
		return new ColumnProxy(this, (Column)target);
	}
	
	@Override
	public IConfiguration createConfiguration(Object target) {
		return new ConfigurationProxy(this, (Configuration)target);
	}
	
	@Override
	public ICriteria createCriteria(Object target) {
		return new CriteriaProxy(this, (Criteria)target);
	}
	
	@Override
	public IDatabaseCollector createDatabaseCollector(Object target) {
		return new DatabaseCollectorProxy(this, (DefaultDatabaseCollector)target);
	}
	
	@Override
	public IEntityMetamodel createEntityMetamodel(Object target) {
		return new EntityMetamodelProxy(this, (EntityMetamodel)target);
	}
	
	@Override
	public IEnvironment createEnvironment(Object target) {
		return new EnvironmentProxy(this);
	}
	
	@Override
	public IForeignKey createForeignKey(Object target) {
		return new ForeignKeyProxy(this, (ForeignKey)target);
	}
	
	@Override
	public IHibernateMappingExporter createHibernateMappingExporter(Object target) {
		return new HibernateMappingExporterProxy(this, (HibernateMappingExporterExtension)target);
	}
	
	@Override
	public IHQLCodeAssist createHQLCodeAssist(Object target) {
		return new HQLCodeAssistProxy(this, (HQLCodeAssist)target);
	}
	
	@Override
	public IHQLCompletionProposal createHQLCompletionProposal(Object target) {
		return new HQLCompletionProposalProxy(this, (HQLCompletionProposal)target);
	}
	
}
