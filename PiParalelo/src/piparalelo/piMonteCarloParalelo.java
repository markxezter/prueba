/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piparalelo;

/**
 *
 * @author leledezma
 */
public class piMonteCarloParalelo extends Thread{
  private double cx, cy;
  private static int intentos = 0;
  private static Object lock = new Object();
  private int vueltas;
  
  public piMonteCarloParalelo(int n)
  {vueltas = n;}
  
  @Override
  public void run(){
    for(int i=0; i<vueltas; i++){
      cx = Math.random();
      cy = Math.random();
      if(Math.pow(cx, 2)+Math.pow(cy, 2)<=1)
        synchronized(lock){intentos++;}
    }
  }
  
  public static void main(String[] args) throws Exception{
    int nVueltas   = 90000000;
    int nThreads   =4;
    long inicTiempo = System.nanoTime();
    piMonteCarloParalelo[] h = new piMonteCarloParalelo[nThreads];
    for(int i=0; i<nThreads; i++)h[i]=new piMonteCarloParalelo((int)(nVueltas/nThreads));
    for(int i=0; i<nThreads; i++)h[i].start();
    for(int i=0; i<nThreads; i++)h[i].join();
    long tiempoTotal = (System.nanoTime()-inicTiempo)/(long)1.0e9;
    System.out.println(intentos+"Aproximacion: "+4.0*intentos/nVueltas);
    System.out.println("Valor Real: "+Math.PI);
     System.out.println("en "+tiempoTotal+" segundos...");
  }
}
