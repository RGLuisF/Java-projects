/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqix;

import java.util.Scanner;

/**
 *
 * @author Luis
 */

public class Q {
    private int p, q;
    
    public Q(){
        p=0;
        q=1;
    }    

    public Q(int p){
        this.p=p;
        q=1;
    }    

    public Q(int a, int b){
        p=a;
        q=b;
        simplifica();
    }    

    public Q(Q r){
        p=r.obtenNum();
        q=r.obtenDen();
    }    

    public int obtenNum(){
        return p;
    }

    public int obtenDen(){
        return q;
    }

    private int mcd(int a, int b){
        int r;
        if (a<0) a=-a;
        if (b<0) b=-b;
        if (b==0) return a;
        while((r=a%b)!=0){
            a=b;
            b=r;
        }
        return b;
    }
    
    private void simplifica(){
        int d=mcd(p, q);
        if(d>1){
            p/=d;
            q/=d;
        }
        if(q<0){
            p=-p;
            q=-q;
        }
    }
    
    public void inc(int a, int b){
        p=a*q+p*b;
        q*=b;
        simplifica();
    }
    
    public void inc(int a){
        inc(a, 1);
    }
    
    public void inc(Q r){
        inc(r.obtenNum(), r.obtenDen());
    }
    
    public void dec(int a, int b){
        p=q*a-p*b;
        q*=b;
        simplifica();
    }
    
    public Q sumaQ(int a){
        Q ret = new Q(a);
        ret.inc(p, q);
        return ret;
    }
    
    public Q sumaQ(int a, int b){
        Q ret = new Q(a, b);
        ret.inc(p, q);
        return ret;
    }
    
    public Q sumaQ(Q r){
        Q ret = new Q(r.obtenNum(),r.obtenDen());
        ret.inc(p, q);
        return ret;
    }
    
    public Q restaQ(int a){
        Q ret = new Q(a);
        ret.dec(p, q);
        return ret;
    }
    
    public Q restaQ(int a, int b){
        Q ret = new Q(a, b);
        ret.dec(p, q);
        return ret;
    }
    
    public Q restaQ(Q r){
        Q ret = new Q(r.obtenNum(),r.obtenDen());
        ret.dec(p, q);
        return ret;
    }
    
    public void multQ(int a, int b){
        p*=a;
        q*=b;
        simplifica();
    }
    
    public void multQ(int a){
        multQ(a, 1);
    }
    
    public void multQ(Q r){
        multQ(r.obtenNum(), r.obtenDen());
    }
    
    public Q invQ(){
        Q ret = new Q(q,p);
        if(p==0){
            System.out.println("El cero no tiene inverso multiplicativo");
            System.exit(0);
        }
        return ret;
    }
    
    public Q expQ(int e){
        Q ret=new Q((int)Math.pow(p,e),(int)Math.pow(q,e));
        return ret;
    }
    
    public Q prodQ(int a){
        Q ret = new Q(a);
        ret.multQ(p, q);
        return ret;
    }
    
    public Q prodQ(int a, int b){
        Q ret = new Q(a, b);
        ret.multQ(p, q);
        return ret;
    }
    
    public Q prodQ(Q r){
        Q ret = new Q(r.obtenNum(), r.obtenDen());
        ret.multQ(p, q);
        return ret;
    }
    
    public Q divQ(int a){
        Q ret = new Q(a).invQ();
        ret.multQ(p, q);
        return ret;
    }
    
    public Q divQ(int a, int b){
        Q ret = new Q(a,b).invQ();
        ret.multQ(p, q);
        return ret;
    }
    
    public Q divQ(Q r){
        Q ret = new Q(r.obtenNum(), r.obtenDen()).invQ();
        ret.multQ(p, q);
        return ret;
    }
    
    public void leerDN(){
        Scanner readint = new Scanner(System.in);  
        System.out.println("Introducir el numerodor");
        p=readint.nextInt();
        do{ System.out.println("Introducir un denominador distinto de cero");
            q=readint.nextInt();
        }while (q==0);
           
        simplifica(); 
    }
    
    public Q leerQ(){
        Q ret = new Q();

        ret.leerDN();
        return ret;
    }
    
    public boolean igualQ(Q a){
        return p==a.obtenNum() && q==a.obtenDen();  
    }
    
    public void escQ(){
        System.out.format("%+d", p);
        if (q>1) System.out.format("/%d", q);
    }
    
    @Override
    public String toString(){
        return String.format("%+d%s", p, (q>1?"/"+q:""));
    }
    
}