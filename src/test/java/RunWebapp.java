import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class RunWebapp {

	public static void main(String[] args) throws Exception {
		Server server = new Server();

		Connector connector = new SelectChannelConnector();
		connector.setPort(Integer.getInteger("jetty.port", 8080));

		server.setConnectors(new Connector[] { connector });

		String jetty_home = System.getProperty("jetty.home", "./");

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/testsecurity");
		webapp.setWar(jetty_home + "src/main/webapp");
		webapp.setDefaultsDescriptor(jetty_home + "src/test/resources/webdefault.xml");

		// Remove slf4j from list of classes not exposed to webapp
		webapp.setServerClasses(new String[] { "-org.eclipse.jetty.plus.jaas.", "org.eclipse.jetty." });

		server.setHandler(webapp);

		server.start();
		server.join();
	}

}
