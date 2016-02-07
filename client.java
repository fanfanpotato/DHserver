import java.net.*;
import java.io.*;

public class client {
  
  public static void main(String[] args) throws IOException {
    Socket socket = null;
		
    String svname = new String("April.net16");
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Please enter the server IP address: ");
    svname = br.readLine();
    
    BufferedReader in= null;
    DataOutputStream out =null;
    try{
      socket = new Socket(svname,61234);
      System.out.println("Connect to server "+ svname);
      in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new DataOutputStream(socket.getOutputStream());
	  } catch (IOException e) {
      System.err.println("Can not get the connection to " + svname);
      System.exit(1);
	  }
	
	System.out.println("Please choose one base number: " );
	int basenum = Integer.parseInt(br.readLine());
	System.out.println("Please choose one q number: ");
	int q = Integer.parseInt(br.readLine());
	System.out.println("Please choose one Xa number: ");
	int Xa = Integer.parseInt(br.readLine());
	int Ya = ((int)Math.pow(basenum,Xa))% q;
	System.out.println("Ya is "+Ya);
	out.writeBytes(basenum+"\n"+""+q+"\n"+""+Ya+"\n");
	System.out.println("Send base number and q and Ya to server "+ svname);
	
	int Yb = Integer.parseInt(in.readLine());
	System.out.println("Read Yb from server: " + Yb);
	int key = ((int)Math.pow(Yb,Xa))% q;
	System.out.println("Calculate secret key: " + key);
    
	System.out.println("Please enter the data want to be send : \n");
    String data1=br.readLine();
    
	System.out.println("Before Encryption Data is :");
    System.out.println(data1);
    int length = data1.length();
    int output[] = new int[length];
    for (int i = 0; i < length; i++){
      output[i] = data1.charAt(i)+ key;
    }
    String sb = "";
    for (int i = 0; i < output.length; i++){
      
      sb += (char)output[i];
      
    }
    
    System.out.println("After Encryption Data is :");
    System.out.println(sb);
	System.out.println("Send to server");
	out.writeBytes(sb+"\n");
	
    out.close();
    in.close();
	br.close();
    socket.close();
  }
	
}