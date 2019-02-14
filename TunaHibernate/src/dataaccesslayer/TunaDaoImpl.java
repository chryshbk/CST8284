/* File: TunaDaoImpl.java
 * Author: Stanley Pieda
 * Date: November 2018
 * Modified By: Chrystian Santos
 * References:
 * Ram N. (2013). Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 */
package dataaccesslayer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import datatransfer.Tuna;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

// enum for singleton
/**
 * TunaDaoImpl which implements TunaDao interface, does all the procedures to interact with the database.
 * @author Chrystian Santos
 */
public enum TunaDaoImpl implements TunaDao{
	
	/** Only use one constant for Singleton Design Pattern */
	INSTANCE;

	private SessionFactory factory;
	private StandardServiceRegistry registry;
	
	private TunaDaoImpl(){
            
		try {
			// A SessionFactory is set up once for an application!
                        registry = new StandardServiceRegistryBuilder().configure().build();
                        MetadataImplementor meta = (MetadataImplementor) new MetadataSources(registry)
                                .addAnnotatedClass(Tuna.class).buildMetadata();
                        factory = meta.buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, 
			// but if we are here we had trouble building the SessionFactory
			// so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
                        
			if(registry != null)
                shutdown();
			throw e;
		}
	}

        /**
         * Inserts a Tuna into the database by using Transaction and Session (Hibernate)
         * @param tuna 
         */
	@Override
	public void insertTuna(Tuna tuna){
            // Starts the transaction
            try(Session session = factory.openSession()) {
                // Starts the transaction
                session.beginTransaction();
                try {
                    session.persist(tuna); // Adds a new entity instance to the persistence context.
                    session.getTransaction().commit(); // Makes the database commit.
                }catch(IllegalStateException ise){ session.getTransaction().rollback(); Logger.getLogger(ise.getMessage()); }
                // closes the session.
                }
        }
        /**
         * Finds the Tuna UUID in the database. Uses Session and Criteria from Hibernate. 
         * @param uuid
         * @return Tuna 
         */
	@SuppressWarnings({"unchecked", "deprecation"})
	@Override
	public Tuna findByUUID(String uuid){
           Tuna tuna;
           // Starts the transaction
           try(Session session = factory.openSession()) {
            
           // Starts the transaction
           session.beginTransaction();
           
           Criteria criteria = session.createCriteria(Tuna.class); // Creates criteria for Tuna class.
           criteria.add(Restrictions.eq("uuid", uuid)); // Adds restriction to uuid.
           tuna = (Tuna) criteria.uniqueResult(); // Assigns tuna object with the criteria unique result from the database.
           }
           
           return tuna;
	}

        /**
         * Shuts down the instance by destroying the registry.
         */
	@Override
	public void shutdown() {
            factory.close(); // Closes the factory
            StandardServiceRegistryBuilder.destroy(registry); // Destroys the registry.
           
	}
}
