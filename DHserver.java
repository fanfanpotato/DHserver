import java.io.*;
import java.net.*;
import java.math.*;

public class DHserver {

  public static int pubbase = 3;
  public static int pubnum = 15;

  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    ServerSocket serverSocket = new ServerSocket(portNumber);
    Socket clientSocket = serverSocket.accept();
    // get the private key, and public key to client
    System.out.println("Please choos a private number: ");
    int servernum = Integer.parseInt(br.readLine());
    int ya = ((int)Math.pow(pubbase,servernum))%pubnum;
    System.out.println("Private Key is: " + servernum);
    System.out.println("Public Key is: " + ya);

    //send public key to the client
    byte buf[] = new byte[3];
    buf[0] = (byte) pubnum;
    buf[1] = (byte) pubbase;
    buf[2] = (byte) ya;
    InetAddress id=InetAddress.getLocalHost();
    DatagramSocket ds=new DatagramSocket(1140);
    DatagramPacket dp=new DatagramPacket(buf,buf.length,id,1141);
    ds.send(dp);
    ds.close();

    // server get the public key form Client
    DatagramSocket ds1=new DatagramSocket(1143);
    DatagramPacket dp1=new DatagramPacket(buf,buf.length);
    ds1.receive(dp1);
    int clientnum = buf[0];
    System.out.println("Public Key of Client is: " + clientnum);

    // calculate the secret key
    int key = ((int)Math.pow(clientnum,servernum))% pubnum;
    System.out.println("Secret Key is : " + key);

    // read data sent to client
    System.out.println("Please enter the data want to be send : \n");
    byte data1=(byte)Integer.parseInt(br.readLine());

    // Encryption
    System.out.println("Before Encryption Data is :");
    System.out.println(" "+ data1);
    byte senddata = (byte)(data1 + key);
    System.out.println("After Encryption Data is :");
    System.out.println(" "+senddata);
    // Send Encrypted Data to Client
    DatagramSocket ds2=new DatagramSocket(1145);
    DatagramPacket dp2=new DatagramPacket(buf,buf.length,id,1146);
    ds2.send(dp2);
    ds2.close();
    }
}
