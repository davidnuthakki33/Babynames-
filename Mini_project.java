

import edu.duke.*;
import edu.duke.FileResource;
import org.apache.commons.csv.*;
import java.io.File;
public class Mini_project{
    public int getRank(int year, String name, String gender){
            DirectoryResource dr = new DirectoryResource();
           FileResource f = new FileResource(); 
           CSVParser parser = f.getCSVParser(false);  
           int boyrank = 0; 
           int girlrank = 0; 
           int rank = 0; 
           
           for(CSVRecord rec: parser) {
               
            if(rec.get(1).equals("M")){
                   
                   boyrank +=1; 
                }
                
               else if(rec.get(1).equals("F")){
                
                    girlrank += 1; 
                }
                
                if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                    
                    if(rec.get(1).equals("M")){
                    rank = boyrank;
                    break;                 
                    }
                  
                    
                       else if(rec.get(1).equals("F")){
                    rank = girlrank;
                    break;
                    
                }

            } 
            else {rank = -1;} 
        }
    
        return rank; 
    }public String getName(int year, int rank, String gender){
      String sr = "sorry No name ";
     int m=0, F,f=0;
      FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser(false);
    for(CSVRecord record : parser){
    if(record.get(1).equals("M")){
      m++;
    }else if(record.get(1).equals("F")){
      f++;
    }
  
    if(record.get(1).equals(gender)){
      if(record.get(1).equals("M") && rank==m){
        sr = record.get(0);
          break;
      }else if(record.get(1).equals("F") && rank==f){
        sr = record.get(0);
        break;
      }
    }
  }
  return sr;
}public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank= getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if was born in " + newYear);               
    }public int yearOfHighestRank (String name, String gender){
                       int year = 0;
                       int startRank = 0;
                     DirectoryResource dr = new DirectoryResource();
                       for (File f: dr.selectedFiles()){
                     FileResource fr = new FileResource(f);
                                          String fName = f.getName();
                        String find = "yob";
int startPos = fName.indexOf(find);
int currYear = Integer.parseInt(fName.substring(startPos + 3, startPos + 7));
int currRank = getRank(currYear, name, gender);
if (year == 0){
year = currYear;
startRank = currRank;
}
System.out.println(" Current Rank is : "+currRank+" in Year : "+currYear);
if (currRank < startRank) {
year = currYear;
startRank = currRank;
}
if (currRank == -1) {
year = currRank;
}
}
return year;
}
}
   
   