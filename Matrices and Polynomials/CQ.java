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

public class CQ{
    private Q re, im;
    
    public CQ(){
        re = new Q();
        im = new Q();
    }
    
    public CQ(int a, int b, int c, int d){
        re = new Q(a,b);
        im = new Q(c,d);   
    }
    
    public CQ(Q a,Q b){
        re = a;
        im = b;   
    }
    
    public CQ(CQ a){
        re = a.obtenRe();
        im = a.obtenIm();   
    }
    
    public Q obtenRe(){
        return re;
    }

    public Q obtenIm(){
        return im;
    }
    
    public CQ menosCQ(){
        CQ ret = new CQ(this.re.prodQ(-1),this.im.prodQ(-1));
        return ret;
    }
    
    public void sumCompQ(Q a,Q b){
        re=re.sumaQ(a);
        im=im.sumaQ(b);
    }
    
    public void resCompQ(Q a,Q b){
        re=a.restaQ(re);
        im=b.restaQ(im);
    }
    
    public CQ sumareCQ(Q a){
        CQ ret = new CQ(a,new Q());
        ret.sumCompQ(re, im);
        return ret;
    }
    
    public CQ sumaimCQ(Q a){
        CQ ret = new CQ(new Q(),a);
        ret.sumCompQ(re, im);
        return ret;
    }
    
    public CQ sumaCQ(Q a, Q b){
        CQ ret = new CQ(a,b);
        ret.sumCompQ(re, im);
        return ret;
    }
    
    public CQ sumaCQ(CQ a){
        CQ ret = new CQ(a);
        ret.sumCompQ(re, im);
        return ret;
    }
    
    public CQ restareCQ(Q a){
        CQ ret = new CQ(a,new Q());
        ret.resCompQ(re, im);
        return ret;
    }
    
    public CQ restaimCQ(Q a){
        CQ ret = new CQ(new Q(),a);
        ret.resCompQ(re, im);
        return ret;
    }
    
    public CQ restaCQ(Q a, Q b){
        CQ ret = new CQ(a,b);
        ret.resCompQ(re, im);
        return ret;
    }
    
    public CQ restaCQ(CQ a){
        CQ ret = new CQ(a);
        ret.resCompQ(re, im);
        return ret;
    }
    
    public void multCQ(Q a, Q b){
        Q tre=new Q(re),tim = new Q(im);
        re=tre.prodQ(a).restaQ(tim.prodQ(b));
        im=a.prodQ(tim).sumaQ(tre.prodQ(b));
    }
    
    public CQ prodCQ(Q a, Q b){
        CQ ret = new CQ(a,b);
        ret.multCQ(re, im);
        return ret;
    }
    
    public CQ prodCQ(CQ a){
        CQ ret = new CQ(a);
        ret.multCQ(re, im);   
        return ret;
    }
    
    public CQ invCQ(){
        Q n = new Q(re.prodQ(re).sumaQ(im.prodQ(im)));
        CQ ret= new CQ(re.divQ(n),im.prodQ(new Q(-1)).divQ(n));
        
        if(Math.abs(ret.re.obtenNum())< Math.abs(ret.re.obtenDen())*1e-5) ret.re = new Q();
        if(Math.abs(ret.im.obtenNum())< Math.abs(ret.im.obtenDen())*1e-5) ret.im = new Q();
        
        return ret;
    }
    
    public CQ divCQ(Q a, Q b){
        CQ ret = new CQ(a,b).invCQ();
        ret.multCQ(re, im);
        return ret;
    }
    
    public CQ divCQ(CQ a){
        CQ ret = new CQ(a.invCQ());
        ret.multCQ(re, im);
        return ret;
    }
    
    public void escCQ(){
        if(re.igualQ(new Q()) && im.igualQ(new Q()))
            System.out.print("0");
        else{
            if(re.igualQ(new Q()));
            else re.escQ();
            if(im.igualQ(new Q()));
            else{
                if(im.igualQ(new Q(1))) System.out.print("+i");
                else{
                    if(im.igualQ(new Q(-1))) System.out.print("-i");
                    else {
                        im.escQ();
                        System.out.print("i");
                    }
                }
            }
        }
    }
    
    public CQ leerCQ(){
        CQ ret;
        Q a,b;
        System.out.println("Introducir la parte real");
        a = new Q().leerQ();
        System.out.println("Introducir la parte imaginaria");
        b = new Q().leerQ();
        return ret = new CQ(a,b);
    }
    
    public boolean igualCQ(CQ b){
        return this.re.igualQ(b.obtenRe()) && this.im.igualQ(b.obtenIm());
    }

}
