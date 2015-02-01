package edu.pitt.sis.infsci2711.tutorialapi;
import edu.pitt.sis.infsci2711.multidbs.utils.JerseyJettyServer;

public class TutorialServer {
	public static void main(final String[] args) throws Exception {
		final JerseyJettyServer server = new JerseyJettyServer(7654, "edu.pitt.sis.infsci2711.tutorialapi.rest");
		
		Thread serverTread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					server.start();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
		
		serverTread.start();
		
		System.out.println("NOTE: To stop the server, focus on console and hit enter");
		System.in.read();
		
		server.stop();
	}
}
