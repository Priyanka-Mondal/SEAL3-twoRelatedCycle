package com.example.tworelatedcycle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	final Handler mhand = new  Handler(Looper.getMainLooper());
	public Handler mHandler;
	int x;
	int y;
	Looper mLooper;
	int i;
		/* sdkversion 14, compile target 19, style: none */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 final Lock lock= new ReentrantLock();
		Runnable myRunnable1 = new Runnable()
		{
			public void run()
			{
				lock.lock();
					x=2;
				lock.unlock();
				System.out.println("threadId: " +Thread.currentThread().getId() +" value of x set to 2");
				Thread t = new Thread()
				{
					public void run()
					{
						
						try
						{
							System.out.println("looper");
							Looper.prepare();
							System.out.println(Looper.myLooper());
							final Handler mmHandler = new Handler();
							mHandler = new Handler();
							
							Runnable myRunnable9 = new Runnable()
							{
								public void run()
								{
									lock.lock();
									x = 0;
									lock.unlock();
								System.out.println("threadId: " +Thread.currentThread().getId() +" value of x set to 0"+" "+mhand.toString());
								Runnable myRunnable8 = new Runnable()
								{
									public void run()
									{
												//y =10;
										i=0;
										while(i<1000000) {
												i++;
										}
									};
						        };
							 Runnable myRunnable6 = new Runnable()
								{
									public void run()
									{	
										i =0;
												while(i < 1000000) {
													
													i++;
												}
												y =10;
												System.out.println("threadId: " +Thread.currentThread().getId() +" value of y set to 10"+" "+mmHandler.toString());
									};
						        };
								mmHandler.post(myRunnable8);
						        mmHandler.post(myRunnable6);
								};
								
					        };
							 
						       
						    mmHandler.post(myRunnable9);
						    
					       
					    }
						catch(Throwable t)
						{
							
						}
						finally
						{
							 Looper.loop();
						}
						//System.out.println(mLooper.getThread());
						/*Runnable myRunnable6 = new Runnable()
						{
							public void run()
							{
									y =10;
									System.out.println("threadId: " +Thread.currentThread().getId() +" value of y set to 10");
							};
						};
						mHandler.post(myRunnable6);*/
					
						//mhand.post(myRunnable9);
						
					};
				};
				t.start();
				
				Thread t2 = new Thread()
				{
					public void run()
					{
						int i =0;
						while(i< 1000000){
							i++;
						}
						int z = y;
						System.out.println("threadId: " +Thread.currentThread().getId() +" value of z set to y");
						
					};
						
					
				};
				t2.start();
				Runnable myRunnable2 = new Runnable()
				{
					public void run()
					{
						//Log.v("TWO","--------Task TWO is executing--------");
						//System.out.println("--------Task TWO is executing---------");
						
						
						//Log.v("Write to x","value of x is "+x);
						Runnable myRunnable4 = new Runnable()
						{
							public void run()
							{
									y =x;
									System.out.println("threadId: " +Thread.currentThread().getId() +" value of y set to x");
							};
						};
						mhand.post(myRunnable4);
						Runnable myRunnable5 = new Runnable()
						{
							public void run()
							{
								//Log.v("FOUR","----------Task FOUR is executing------");
								//System.out.println("--------Task FOUR is executing---------");
								//lock.lock();
									y = 0;
									System.out.println("threadId: " +Thread.currentThread().getId() +" value of y set to 0");
							};
						};
						mhand.post(myRunnable5);
						
						
					};
					
					
					
				};mhand.post(myRunnable2);
				
				/////
				
				
				////
			};
		};
		mhand.post(myRunnable1);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
