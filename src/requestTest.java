import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.HashMap;
import java.util.Map;

public class requestTest {
    public static void main(String[] args) {
        //登录
        System.out.println(login("test","test",getLoginPageToken()));
        //请求某张表数据
        System.out.println(apiGetTableInfo("JL_BASE_2006","1","100"));
    }

    //获取登陆界面-get
    public static String getLoginPageToken(){
        String requestURL = "http://180.153.108.40:8080/longboApis/login";
        String token = "";
        String ans = HttpUtil.doGet(requestURL);
        try {
            Document loginDoc = Jsoup.parse(ans);
            token = loginDoc.select("input[name=_csrf]").attr("value");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
    //登陆-post
    public static String login(String username, String password, String token){
        String requestURL = "http://180.153.108.40:8080/longboApis/login";
        Map paras = new HashMap();
        paras.put("username",username);
        paras.put("password",password);
        paras.put("_csrf",token);
        String ans = HttpUtil.doPost(requestURL,paras);
        return ans;

    }
    //表数据请求-get
    public static String apiGetTableInfo(String tableName, String pageNum, String pageSize){
        String requestURL = "http://180.153.108.40:8080/longboApis/jlzx/"+tableName+"/"+pageNum+"/"+pageSize;
        String ans = HttpUtil.doGet(requestURL);
        return ans;
    }

    public static String urlTest(String requestURL){
        String ans = HttpUtil.doGet(requestURL);
        return ans;
    }


}
