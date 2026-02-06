package com.company.java017_newtwork.ver1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
//////////////////////////////////////////////////////////////////////////////////
public class Network002_Sever {
	public static void main(String[] args) {
		//#1. 서버소켓(AS센터) []
		ServerSocket ascenter = null;
		Socket       socket;
		//#2. localhost(127.0.0.1) / port(80 http, 443 https)
		try { 
			ascenter = new ServerSocket(703); 
			System.out.println("[Server] 1. 서버준비완료 A/S 센터 OPEN");
		} 
		catch (IOException e) {  e.printStackTrace(); }
		//#3. 클라이언트 요청(accept)오면 (socket)연결
		try {
			System.out.println("[Server] 2. 고객 기다리는중");
			socket = ascenter.accept(); 
			// 3-1. 연결요청 오기 전까지 기다림
			// 3-2. 연결이 들어올때까지 stop
			// 3-3. 연결이 들어오면
			System.out.println("[Server] 4. 고객 연락와서 상담사(socket)연결");
			
		//#4. 데이터 주고받기
			Thread sender   = new   Sender(socket); sender.start();
			Thread receiver = new Receiver(socket); receiver.start();
		} 
		catch (IOException e) {  e.printStackTrace(); }
	}
}
//////////////////////////////////////////////////////////////////////////////////
/// InputStream > [프로그램] > OutputStream

class Sender extends Thread{//OutputStream
	Socket socket; DataOutputStream out; String who; SimpleDateFormat sdf;
	public Sender() { }
	public Sender(Socket socket) {  
		this.socket = socket; //상대와 연결되어있는 정보
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("Hello.....START!>> ");
			//#1. WHO + 시간
			this.who = "["+(socket.getPort()==70 ? "Client" : "server");
			this.sdf = new SimpleDateFormat("HH:mm:ss]");
			
		} catch (IOException e) {  e.printStackTrace(); }
	}
	@Override public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //키보드로 써서 말하기
		try {	
			while(out!= null) {
				String data = br.readLine();
				String time = sdf.format(System.currentTimeMillis());
				out.writeUTF(who + time + data);
			}
		}catch(Exception e) {			
		}finally {
        	 try {
             if (out != null) {out.close();}
             if (br != null) {br.close();}
        } catch (IOException e) {  e.printStackTrace(); }
        }	  
	}
	
	
}
class Receiver extends Thread{//InputStream
	Socket socket; DataInputStream in;
	public Receiver() { }
	public Receiver(Socket socket) {  
		this.socket = socket; 
		try {in = new DataInputStream(socket.getInputStream()); } 
		catch (IOException e) {  e.printStackTrace(); }
	}
	@Override public void run() {
		try {
            while (in != null) {
                String received = in.readUTF();
                System.out.println(received); 
            }
        } catch (IOException e) {
		
		 System.out.println("통신을 마무리합니다 >> " + socket);
		}finally {
        	 try {
             if (in != null) {in.close();}
             if (socket != null) {socket.close();}
        } catch (IOException e) {  e.printStackTrace(); }
        } System.exit(0);	  
	}
}









/*
1. Http   통신 - 단방향통신(client 요청이 있을때 server가 응답하고 연결이 종료) / jsp, spring
2. Socket 통신 - 양방량통신(특적포트를 통해 실시간으로 정보 주고받기 - tcp/udp)
3. Socket 통신 흐름
 1) 서버소켓(as center), 포트바인딩(문열기)
 2) 클라이언트 연결요청, 수락
 3) 클라이언트 소켓(socket) 
 4) 말하고 주고받기(InputStream/OutputStream)
 
*/