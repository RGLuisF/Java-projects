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
public class CQx{
    public CQ c[];
    private int g;
    
    public CQx(){
        g = -1;
        c = new CQ[0];
    }
    
    public CQx(int g){
        this.g = g;
        c = new CQ[g+1];
        for(int i=0;i<=this.g; i++)
        c[i] = new CQ();
    }
    
    public CQx(CQ ...p){
        g = p.length-1;
        c = new CQ[p.length];
        for(int i=0;i<p.length; i++) c[i]=p[i];
    }
    
    public CQx(CQx a){
        g = a.g;
        c = new CQ[g+1];
        for(int i=0;i<g+1; i++) c[i]=a.c[i];
    }
    
    public CQx CopiaCQx(){
        CQx ret = new CQx(this);
        return ret;
    }
    
    public CQx CopiamenosCQx(){
        CQx ret = new CQx(this.g);
        for(int i=0; i<=g; i++) ret.c[i]=this.c[i].menosCQ();
        return ret;
    }
    
    public CQx sumaCQx(CQx b){
        int min,i;
        CQx ret = new CQx(this);
        
        if (this.g<0){
        return b.CopiaCQx();
        }
        
        if (b.g<0){
        return this.CopiaCQx();
        }
        
        if(this.g<b.g){
            min=this.g;
            ret.g=b.g;
        }
        else{
            min=b.g;
            ret.g=this.g;
        }
        
        for (i=0; i<=min; i++) ret.c[i]= this.c[i].sumaCQ(b.c[i]);
        if (this.g==min) for (i = min+1; i<=b.g; i++) ret.c[i]=b.c[i];
        if (b.g==min) for (i = min+1; i<=b.g; i++) ret.c[i]=b.c[i];
        
        while(ret.g>=0 && ret.c[ret.g].igualCQ(new CQ())) ret.g--;
        return ret;
    }
    
    public CQx restaCQx(CQx b){
        int min,max,i;
        CQx ret;
        
        max=this.g<b.g?b.g:this.g;
        min=this.g<b.g?this.g:b.g;
        
        ret = new CQx(max);

        for (i=0; i<=min; i++) ret.c[i] = this.c[i].restaCQ(b.c[i]);
        
        if (max == this.g) for (; i<=this.g; i++) ret.c[i]=this.c[i];
        if (max == b.g) for (; i<=b.g; i++) ret.c[i]=b.c[i].menosCQ();
        
        while(ret.g>=0 && ret.c[ret.g].igualCQ(new CQ())) ret.g--;
        return ret;
    }

    public CQx prodCQx(CQx b){
        CQx ret;
        int i,j;
        
        if (this.g<0 || b.g<0){
  	    ret = new CQx();
            return ret;
        }
        
        ret = new CQx(this.g+b.g);
        
        for(i=0;i<=this.g;i++){
            for(j=0;j<=b.g; j++)	
    	    ret.c[i+j] = ret.c[i+j].sumaCQ(this.c[i].prodCQ(b.c[j]));
        }
        
    return ret;
    }
    
    public CQx multmonoCQx(CQ a, int e){
        CQx ret = new CQx(this.g+e);
        int i;
        
        for(i=0;i<=ret.g;i++) ret.c[i]= new CQ();
        for(i=e;i<=ret.g;i++) ret.c[i]= this.c[i-e].prodCQ(a);
    
	return ret ;
    }
    
    public CQx divCQx(CQx b){
        CQx aux = new CQx(this), baux = new CQx(this), ret = new CQx(this.g-b.g), mono, res;

        
        if(!(b.g<0)){
             
        for (int i=0; i<=ret.g; i++) ret.c[i]= new CQ();      
        
        do{
        int grad = aux.g-b.g;
        ret.c[grad] = aux.c[aux.g].divCQ(b.c[b.g]);
        mono = b.multmonoCQx(ret.c[grad], grad);
        aux = aux.restaCQx(mono);
        res = new CQx(aux);
 

        }while(!(res.escero()) && res.g>=b.g);
        return ret;
        }
        else return new CQx();
    }
    
    public CQx resCQx(CQx b){
        CQx aux = new CQx(this) , ret = new CQx(this.g-b.g), mono, res;
        
        if(!(b.g<0)){
        
        for (int i=0; i<=ret.g; i++) ret.c[i]= new CQ();

        do{
        int grad = aux.g-b.g;
        ret.c[grad] = aux.c[aux.g].divCQ(b.c[b.g]);
        mono = b.multmonoCQx(ret.c[grad], grad);
        aux = new CQx(aux.restaCQx(mono));
        res = new CQx(aux);
        }while(!(res.g<0) && res.g>=b.g);
        return res;
        }
        else return new CQx();
    }
    
    public CQx mcdCQx(CQx b){
        CQx aaux,baux, r;
        
        if(b.g<0){
          return new CQx();
	}
	
	aaux = new CQx(this);
	baux = new CQx(b);

	r = new CQx(this.resCQx(b));
        
	while(!(r.g<0)){
            r = new CQx(aaux.resCQx(baux));
            aaux = new CQx(baux);
            baux = new CQx(r);
        }
        
	return baux; 
    }
    
    public void escCQx(){
        int i;
	if(this.g<0){
            System.out.print("0");
        }
        else{
	for(i=this.g;i>1;i--){	
	    if(!(this.c[i].igualCQ(new CQ()))){
                   this.c[i].escCQ();
                   System.out.format("x^%d", i);
            }	
	}
	
        if(this.g>=1){
            if(!(this.c[1].igualCQ(new CQ()))){
                this.c[1].escCQ();
                System.out.format("x");
            }
        }	
      
    if(!(this.c[0].igualCQ(new CQ()))) this.c[0].escCQ();
        }
    }
    
    public CQx leerCQx(){
        CQx ret;
        int i,grad;
        
        Scanner readint = new Scanner(System.in);
        System.out.println("Introducir el grado del polinomio");
        grad = readint.nextInt();
        ret = new CQx(grad);
        ret.g=grad;
        for(i=grad;i>=0;i--){
            System.out.format("Introducir el coeficiente que acompana al termino de grado %d\n",i);
	   ret.c[i] = new CQ().leerCQ();
	}
        
        while(ret.g>=0 && ret.c[ret.g].igualCQ(new CQ())) ret.g--;
        return ret;
    }
    
    public boolean escero(){
        return this.g<0;
    }
    
    public CQx monico(){
        CQx ret = new CQx(this.g);
        for (int i=0; i<=this.g; i++) ret.c[i] = this.c[i].divCQ(this.c[this.g]);
        return ret;
    }
    
    public CQx Pol1(){
        Q quno = new Q(1), qcero = new Q(0);
        CQ uno = new CQ(quno,qcero);
        CQx ret = new CQx(uno);
        return ret;
    }
    
    public int CQxg(){
        return this.g;
    }
    
    
}