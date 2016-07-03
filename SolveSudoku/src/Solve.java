import org.omg.CORBA.PRIVATE_MEMBER;


public class Solve {
	private FrameError frameError;
	
	public int[][] solution(int[][] sudoku){
		int zeroNum=0;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(sudoku[i][j]==0){
					zeroNum+=1;
				}
			}
		}
		int[][] place=new int[zeroNum][2];             
		int t1=0;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(sudoku[i][j]==0){
					place[t1][0]=i;
					place[t1][1]=j;
					t1+=1;
				}
			}
		}
		
		int[][] temp=new int[zeroNum][9];             
		for(int num=0;num<9;num++){
			for(int i=0;i<zeroNum;i++){
				if(check(sudoku,place[i][0],place[i][1],num+1)){
					temp[i][num]=1;
				}	
			}
		}
		int[] tempCount=new int[zeroNum];              
		for(int num=0;num<9;num++){
			for(int i=0;i<zeroNum;i++){
				if(check(sudoku,place[i][0],place[i][1],num+1)){
					tempCount[i]+=1;
				}	
			}
		}
		
		int[][] possibleValue=new int[zeroNum][];     
		for(int i=0;i<zeroNum;i++){
			possibleValue[i]=new int[tempCount[i]];
			int t2=0;
			for(int j=0;j<9;j++){
				if(temp[i][j]==1){
					possibleValue[i][t2]=j+1;
					t2++;
				}
			}
		}
		
		int[] result=new int[zeroNum];
		for(int i=0;i<zeroNum;i++){
			if(i<0){
				frameError=new FrameError();
			}
			if(result[i]==possibleValue[i].length){
				result[i]=0;
				sudoku[place[i][0]][place[i][1]]=0;
				if(i>=1){
					result[i-1]+=1;
					i-=2;
					continue;
				}
			}
			if(check(sudoku,place[i][0], place[i][1],possibleValue[i][result[i]])){
				sudoku[place[i][0]][place[i][1]]=possibleValue[i][result[i]];
			}else{
				result[i]+=1;
				i-=1;
			}
		}
		return sudoku;
	}

	public boolean check(int[][] data,int x,int y,int num){
		boolean isOk=true;

		for(int i=0;i<9;i++){
			if(i!=y){
				if(data[x][i]==num){
					isOk=false;
				}
			}
		}

		for(int i=0;i<9;i++){
			if(i!=x){
				if(data[i][y]==num){
					isOk=false;
				}
			}
		}

		for(int i=(x/3)*3;i<(x/3)*3+3;i++){
			for(int j=(y/3)*3;j<(y/3)*3+3;j++){
				if(x!=i&&y!=j&&data[i][j]==num){
					isOk=false;
				}
			}
		}
		return isOk;
	}
}
