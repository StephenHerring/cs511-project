package osql;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import implementation.*;

/**
 * Created by Saurabh on 5/3/2016.
 */
public class osql {
    private String queryTxt;
    private String convertedTxt;
    public void set_query_text(String queryText){
         this.queryTxt = queryText;
    }
    public void convert_text(){

        if (this.queryTxt.toLowerCase().contains("select")){
           convertedTxt = this.queryTxt;  ;
        }
        else if (this.queryTxt.toLowerCase().contains("insert")){
            System.out.println("in insert");
            String pattern = "(\\(.*\\))";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(queryTxt);
            if (m.find()) {
                System.out.println("did we match");
                String values = m.group(0);
                if (values.split(",").length == 4) {
                    this.convertedTxt = queryTxt;
                } else {
                    NaiveImplementation ni = new NaiveImplementation();
                    int position = ni.get_highest_index() + 1;
                }
            }
        }
        else if (this.queryTxt.toLowerCase().contains("delete")){
            convertedTxt = this.queryTxt;  ;
        }

    }
    public String get_converted_text(){
        return this.convertedTxt;
    }
    public String convert(String queryTxt){
        this.queryTxt = queryTxt;
        this.convert_text();
        return get_converted_text();
    }
    public osql(){}
}
