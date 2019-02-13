import java.util.LinkedList;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory factory;
	
	static {
		try{

			Configuration cfg=new Configuration();  
			cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
			cfg.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
			

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			factory = cfg.buildSessionFactory(serviceRegistry);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return factory;
	}
	
	public static void kill(){
		factory.close();
	}
}
