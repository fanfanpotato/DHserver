import java.net.*;
import java.io.*;

public class server {
  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    ServerSocket svSocket = null;
    try{
      svSocket = new ServerSocket(61234);
    } catch(IOException e){
      System.err.println("Can not listen the socket.");
      System.exit(1);
    }

    System.out.println("Listen at port number 61234");

    Socket clntSocket = null;
    try{
      clntSocket = svSocket.accept();
    }catch (IOException e){
      System.err.println("Get connection failed.");
      System.exit(1);
    }
    BufferedReader in=new BufferedReader(new InputStreamReader(clntSocket.getInputStream()));
    DataOutputStream out = new DataOutputStream(clntSocket.getOutputStream());
	System.out.println("Read data from client.");
	int basenum = Integer.parseInt(in.readLine());
	int q = Integer.parseInt(in.readLine());
	int Ya = Integer.parseInt(in.readLine());
	
	System.out.println("Please choose one Xb number: " );
	int Xb = Integer.parseInt(br.readLine());
	int Yb = ((int)Math.pow(basenum,Xb))% q;
	System.out.println("Send Yb to client" + Yb);
	out.writeBytes(Yb+ "\n");
	
    int key = ((int)Math.pow(Ya,Xb))% q;
	System.out.println("Calculate secret key: " + key);
	
    System.out.println("Before Decryption, received data is: ");
	String getdata;
	
	getdata = in.readLine();
	System.out.println(getdata);
	System.out.println("After Decryption: ");
	int length = getdata.length();
    int output[] = new int[length];
    for (int i = 0; i < length; i++){
      output[i] = getdata.charAt(i) - key;
    }

    String sb = "";
    for (int i = 0; i < length; i++){
      
      sb += (char)output[i];
      
    }
    String strI = sb.toString();
    System.out.println(strI);
    
	out.close();
    in.close();
	br.close();
    clntSocket.close();
	svSocket.close();
  }
}