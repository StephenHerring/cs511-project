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
            String pattern = "(\\(.*,.*,.*,.*\\))";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(queryTxt);
            if (m.find()) {
                String values = m.group(1);
                int formatLen =  values.split(",").length;
                if (formatLen == 7) {
                    this.convertedTxt = queryTxt;
                } else if (formatLen == 5) {
                    NaiveImplementation ni = new NaiveImplementation();
                    int position = ni.get_highest_index() + 1;
                    String splitTxt [] = queryTxt.split("\\(");
                    this.convertedTxt = splitTxt[0] + "(trank," + splitTxt[1] + "(" + position + ","
                            + splitTxt[2];
                }
                else
                    this.convertedTxt = "Malformed Expression";
            }
        }
        else if (this.queryTxt.toLowerCase().contains("delete")){
            this.convertedTxt = this.queryTxt;  ;
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
    public data.Row get_insert_row(){
        String pattern = "((\\d+\\s*,\\s*[a-z0-9]+\\s*,\\s*\\d+\\.?\\d*\\s*,\\s*\\d+\\s*))";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(this.convertedTxt);
        if (m.find()) {
            System.out.println(m.group(0));
            String splitTxt []= m.group(0).split(",");
            int trank = Integer.parseInt(splitTxt[0]);
            float gpa = Float.parseFloat(splitTxt[2].trim()+"f");
            System.out.println(splitTxt[3]);
            long student_id = Long.parseLong("+" + splitTxt[3].trim());
            System.out.println(trank + "\t" +  splitTxt[1] + "\t" +   gpa + "\t" + student_id);

            data.Row element = new data.Row(trank,splitTxt[1],gpa,student_id );
            return element;
        }
     return null;
    }
    /* we parse this way to make the implementation future proof */
    public data.Row get_delete_row() {
        String pattern = "(\\d+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(this.convertedTxt);
        if (m.find()) {
        data.Row element = new data.Row(Integer.parseInt(m.group(0)), "dummy", 0.0f, 8263263);
        return element;
        }
        return null;

    }
    public osql(){}
}
