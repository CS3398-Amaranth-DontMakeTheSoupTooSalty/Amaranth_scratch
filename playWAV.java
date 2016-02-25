/*
 * Still needs to be set up for later importation to other files. 
 * Having a loopForIteration() function that plays a sound a certain
 * number of times might be also be helpful. The current form should 
 * be sufficient for background music or other singular sound 
 * effects who either need to be looped or only played once. 
 */


import java.io.*;
import java.util.Scanner;
import sun.audio.*;


public class playWAV {
	private AudioStream aud_stream;
	private InputStream in;
	private ContinuousAudioDataStream cont_aud;
	private AudioData aud_dat;
	private String aud_path;
	private boolean loop;

	//Constructor 
	public playWAV(String path, boolean loop) throws IOException
	{
		InputStream in = new FileInputStream(path);
		this.loop = loop;
		if(loop)
		{
			aud_path = path;
			aud_stream = new AudioStream(in);
			AudioData ads = aud_stream.getData();
			cont_aud = new ContinuousAudioDataStream(ads);
		}
		else
		{
			aud_path = path;
			aud_stream = new AudioStream(in);
		}
		
	}
	
	// Plays the sound file
	public void start()
	{
		if(loop)
		{
			AudioPlayer.player.start(cont_aud);
		}
		else
		{
			AudioPlayer.player.start(aud_stream);
		}
	}
	// Stops the sound file
	public void stop()
	{
		if(loop)
		{
			AudioPlayer.player.stop(cont_aud);
		}
		else
		{
			AudioPlayer.player.stop(aud_stream);
		}
	}
	
	// Loops for length seconds. Thread.sleep operates in miliseconds, hence
	// the *= 1000
	public void loopForSeconds(int length)
	{ 
		length *= 1000;
		this.start();
		try
		{
			Thread.sleep(length);
			this.stop();
		}catch(InterruptedException e){}
	}
	
	// Simply illustrates the use of the class 
	public static void main(String[] args) 
	{
		String choice, file_path;
		int loop_dur; 
		
		Scanner input = new Scanner( System.in );
		System.out.println("Please enter the file path of your WMV file!");
		file_path = input.nextLine();
		System.out.println("Would you like this sound to loop?[Y/N]");
		choice = input.nextLine();
		
		
		if(choice.equals("Y") || choice.equals("y"))
		{
			System.out.println("How long would you like it to loop? (seconds)");
			loop_dur = input.nextInt();
			try
			{
				playWAV sound = new playWAV(file_path, true);
				sound.loopForSeconds(loop_dur);
			}catch(IOException e){}		
		}
		else if(choice.equals("N") || choice.equals("n"))
		{
			try
			{
				playWAV sound = new playWAV(file_path,false);
				sound.start();
			}catch(IOException e){}
		}
		input.close();
		return;
	}

}