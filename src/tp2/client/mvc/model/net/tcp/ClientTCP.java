package tp2.client.mvc.model.net.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientTCP {

	private Socket clientSocket;
	private DataOutputStream messageToSend;
	private BufferedReader messageToReceive;
	protected String hostClient;
	protected int portClient;

	public ClientTCP(String hostClient, int portClient) {
		
		this.hostClient=hostClient;
		this.portClient=portClient;
		
		int numberMaxOfConnectionTry = 3; 
		try{
			do{
				try{
					this.clientSocket = new Socket(hostClient,portClient);
				}
				catch(ConnectException ce){
					System.err.println("Client Connexion host :"+hostClient+", port:"+portClient+", refused. Maybe Server is not running.");
					if( (clientSocket==null || !clientSocket.isConnected()) && numberMaxOfConnectionTry > 0) {
						numberMaxOfConnectionTry--;
						System.out.print("Retry in 20 sec ...");
						try {
							Thread.sleep(5000);
							System.out.print("15 sec ...");
							Thread.sleep(5000);
							System.out.print(" 10 sec ...");
							Thread.sleep(5000);
							System.out.print(" 5 sec ...");
							Thread.sleep(5000);
							System.out.println("retry connection to "+hostClient+", port:"+portClient+"...");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else{
						System.out.println("Sorry number max of retry connection ended. Retry later please.");
						System.exit(0);
					}
				}
			} while((clientSocket==null) || !(clientSocket.isConnected()));
			this.messageToSend = new DataOutputStream(this.clientSocket.getOutputStream());
			this.messageToReceive = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Client Connexion host :"+hostClient+", port:"+portClient+", success.");
	}

	public void writeRequest(String request) throws IOException{
		if(clientSocket!=null && clientSocket.isConnected()){
			try{
				messageToSend.writeBytes(request+"\n");
			}catch(SocketException se){
				System.err.println("Server connexion lost, server was shutdown.");
				System.exit(0);
			}
		}
	}

	public String getResponse() throws IOException{
		String res = messageToReceive.readLine();
		return res;
	}

	public void closeConnectionClient() throws IOException{
		this.clientSocket.close();
		System.out.println("Client Connexion host :"+hostClient+", port:"+portClient+", exit success.");
		System.exit(0);
	}

}
