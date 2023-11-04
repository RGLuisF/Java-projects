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
public class MatFP {
    FracPoli ent[][];
    int n;
    
    public MatFP(){
        n=0;
    }
    
    public MatFP(int n){
        this.n = n;
        ent = new FracPoli[n][n];
        for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			ent[i][j]= new FracPoli();
		}
	}	
    }
    
    public MatFP(MatFP A){
        n = A.n;
        ent = new FracPoli[n][n];
        for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			ent[i][j]= A.ent[i][j];
		}
	}
    }
    
    public MatFP sumaMatFP(MatFP B){
        MatFP ret = new MatFP(this.n);
        
        for(int i=0;i<ret.n;i++){
		for(int j=0;j<ret.n;j++){
			ret.ent[i][j]= this.ent[i][j].sumaFCQ(B.ent[i][j]);
		}
	}	
	return ret;
    }
    
    public MatFP prodMatFP(MatFP B){
        MatFP ret = new MatFP(this.n);
        
        for(int i=0; i<ret.n; i++)
          for(int j=0; j<ret.n; j++)
            for(int k=0; k<ret.n; k++)
	ret.ent[i][j]=ret.ent[i][j].sumaFCQ(this.ent[i][k].prodFCQ(B.ent[k][j]));

        return ret;
    }
    
    public MatFP E1(int t ,FracPoli c){
        MatFP aux = new MatFP(this.n);
        for(int i=0; i<this.n; i++) aux.ent[i][i] = new FracPoli(new CQx().Pol1());
        aux.ent[t][t] = c;
        aux.escMatFP();
        aux = aux.prodMatFP(this);
        return aux;
    }
    
    public MatFP E2(int r1, int r2){
        MatFP ret = new MatFP(this.n);
        
        for(int i=0; i<ret.n; i++) ret.ent[i][i] = new FracPoli(new CQx().Pol1());
        ret.ent[r1][r1] = new FracPoli();
        ret.ent[r2][r2] = new FracPoli();
        ret.ent[r1][r2] = new FracPoli(new CQx().Pol1());
        ret.ent[r2][r1] = new FracPoli(new CQx().Pol1());
        ret = ret.prodMatFP(this);
        return ret;
    }
    
    public MatFP E3(FracPoli c, int p, int s){
        MatFP ret = new MatFP(this.n);
        
        for(int i=0; i<ret.n; i++) ret.ent[i][i] = new FracPoli(new CQx().Pol1());
        ret.ent[s][p] = c;
        ret = ret.prodMatFP(this);
        return ret;
    }
    
    public MatFP leerMatFP(int n){
        MatFP ret = new MatFP(n);
        
        for(int i=0; i<ret.n; i++)
          for(int j=0; j<ret.n; j++){
              System.out.format("Introducir la entrada (%d,%d)\n",i+1,j+1);
              ret.ent[i][j] = new FracPoli().leeFCQ();
          }
        return ret;
    }
    
    public void escMatFP(){
        for(int i=0; i<this.n; i++)
          for(int j=0; j<this.n; j++){
              System.out.format("[%d,%d]:",i+1,j+1);
              this.ent[i][j].escFCQ();
              System.out.println();
          }
    }
    
    public MatFP cerodiag(int t){
        MatFP ret = new MatFP(this);
        
        for(int i=t+1; i<this.n; i++){
        ret = ret.E3(ret.ent[i][t].negFCQ().prodFCQ(new FracPoli(ret.ent[t][t].q, ret.ent[t][t].p)), t, i);
        }
         
        return ret;
    }
    
    public MatFP cerodiagsup(int t){
        MatFP ret = new MatFP(this);
        
        for(int i=n-1; i>=0; i--){
        ret = ret.E3(ret.ent[i][t].negFCQ().prodFCQ(new FracPoli(ret.ent[t][t].q, ret.ent[t][t].p)), t, i);
        }
         
        return ret;
    }
    
    public MatFP triangular(){
        MatFP ret = new MatFP(this);
        int i = 0,p = 0;
        
        for(int j=0; j<this.n-1; j++){
        while(this.ent[i][j].esceroFCQ() && i<this.n) i++;
          if(i!= this.n){
            if(i!=j){
             ret = ret.E2(i, j); 
             p++;
            }
           ret = ret.cerodiag(j);
          }
          i=j+1;
        }
        
        if(p%2==1) ret.ent[0][0].p = ret.ent[0][0].p.CopiamenosCQx();
        
        return ret;
    }
    
    public FracPoli det(){
        MatFP aux = new MatFP(this).triangular();
        FracPoli det = aux.ent[0][0];
        
        for(int i=1; i<this.n; i++) det = det.prodFCQ(aux.ent[i][i]) ;
        return det;
    }
    
    public MatFP invMatFP(){
        MatFP ret = new MatFP(this.n), aux = new MatFP(this);
        int i;
        
        for(i=0; i<ret.n; i++) ret.ent[i][i] = new FracPoli(new CQx().Pol1());
        
        for(int j=0; j<this.n-1; j++){ i=j;
        while(this.ent[i][j].esceroFCQ() && i<this.n) i++;
          if(i!= this.n){
            if(i!=j){
             aux = aux.E2(i, j); 
             ret = ret.E2(i, j);
            }
           aux = aux.cerodiag(j);
           ret = ret.cerodiag(j);
          }
        }
        
        for(int j=this.n-1; j>=0; j--){ i=j;
        while(this.ent[i][j].esceroFCQ() && i>=0) i--;
          if(i!= 0){
            if(i!=j){
             aux = aux.E2(i, j); 
             ret = ret.E2(i, j);
            }
           aux = aux.cerodiagsup(j);
           ret = ret.cerodiagsup(j);
          }
        }
        
        return aux;
    }

}
