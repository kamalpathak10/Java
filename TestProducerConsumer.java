package com.github.threads;

import java.util.Scanner;

public class TestProducerConsumer {

	public static void main(String args[]) throws InterruptedException {

		ProducerConsumer pc = new ProducerConsumer();

		Thread th1 = new Thread(new Runnable() {

			public void run() {
				pc.produce();
			}

		});

		Thread th2 = new Thread(new Runnable() {

			public void run() {
				pc.consume();
			}

		});
		th1.start();
		th2.start();
		th1.join();
		th2.join();
		System.out.println("Done");
	}

}

class ProducerConsumer {
	String str = "";

	public void produce() {

		while (true) {

			synchronized (this) {
				System.out.println("In Produce, Please input to produce or press x to exit");
				Scanner sc = new Scanner(System.in);
				str = sc.nextLine();
				if (str.equalsIgnoreCase("x")) {
					break;
				}
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("After wait in producer");
			}
		}
	}

	public void consume() {

		while (true) {

			try {
				Thread.sleep(1000);

				synchronized (this) {

					System.out.println("In consume, Consumed=" + str);
					if (str.equalsIgnoreCase("x")) {
						break;
					}
					notify();
					System.out.println("After notify in consumer");
					Thread.sleep(2000);

				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
