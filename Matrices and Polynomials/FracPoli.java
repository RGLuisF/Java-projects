/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqix;

/**
 *
 * @author Luis
 */

public class FracPoli{
    CQx p, q;

    
    public FracPoli(){
        p = new CQx();
        q = new CQx().Pol1();
    }    

    public FracPoli(CQx p){
        this.p = p;
        q = new CQx().Pol1();
    }    

    public FracPoli(CQx a, CQx b){
        p = a;
        q = b;
        simplificaFracPoli();
    }    

    public FracPoli(FracPoli r){
        p=r.obtenNum();
        q=r.obtenDen();
    }    

    public CQx obtenNum(){
        return p;
    }

    public CQx obtenDen(){
        return q;
    }
    
    private void simplificaFracPoli(){
        CQx mcd;
        
        if(p.CQxg()<0 || q.CQxg()<0) return;
        
        if(p.CQxg()<q.CQxg()){ mcd = new CQx(q.mcdCQx(p));}
        else mcd = new CQx(p.mcdCQx(q));   
            p=p.divCQx(mcd);
            q=q.divCQx(mcd).monico();
    }
    
     public void incFCQ(CQx a, CQx b){
        p = a.prodCQx(q).sumaCQx(p.prodCQx(b));
        q = q.prodCQx(b);
        simplificaFracPoli();
    }
     
    public FracPoli sumaFCQ(CQx a){
        FracPoli ret = new FracPoli(a,new CQx().Pol1());
        ret.incFCQ(p, q);
        return ret;
    }
    
    public FracPoli sumaFCQ(CQx a, CQx b){
        FracPoli ret = new FracPoli(a,b);
        ret.incFCQ(p, q);
        return ret;
    }
    
    public FracPoli sumaFCQ(FracPoli r){
        FracPoli ret = new FracPoli(r.obtenNum(),r.obtenDen());
        ret.incFCQ(p, q);
        return ret;
    }
    
    public void multFCQ(CQx a, CQx b){
        p = a.prodCQx(p);
        q = b.prodCQx(q);
        simplificaFracPoli();
    }
    
    public FracPoli prodFCQ(CQx a){
        FracPoli ret = new FracPoli(a);
        ret.multFCQ(p, q);
        return ret;
    }
    
    public FracPoli prodFCQ(CQx a, CQx b){
        FracPoli ret = new FracPoli(a,b);
        ret.multFCQ(p, q);
        return ret;
    }
    
    public FracPoli prodFCQ(FracPoli r){
        FracPoli ret = new FracPoli(r);
        ret.multFCQ(p, q);
        return ret;
    }
    
    public FracPoli negFCQ(){
        FracPoli ret = new FracPoli(this.q.CopiamenosCQx(),this.q);
        return ret;
    }
    
    public FracPoli leeFCQ(){
        FracPoli ret;
        CQx a,b;
        a = new CQx().leerCQx();
        do{
           b = new CQx().leerCQx();  
        }while(b.c[0].igualCQ(new CQ(0,1,0,1)));
       
        ret = new FracPoli(a,b);
        return ret;
    }
    
    public void escFCQ(){
        System.out.print("(");
        p.escCQx();
        System.out.print(")");
        if(!q.equals(new CQx().Pol1())){
            System.out.print("/(");
            q.escCQx();
            System.out.print(")");
        }
    System.out.println();
    }
    
     public boolean esceroFCQ(){
         return this.p.escero();
     }
}
