package com.example.practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class DemoController {

    /**
     * ユーザーデータテーブル(user_data)へアクセスするリポジトリ
     */
    @Autowired
    UserService userService;         
    
    private UserData userData;

    /**
     * 初期表示画面に遷移する
     * @return 初期表示画面へのパス
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 引数の検索IDに対応するDemoFormデータを取得する
     * @param id 検索ID
     * @return DemoFormデータ(JSON形式)
     */
    //JSON文字列を返却するために、@ResponseBodyアノテーションを付与
    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam("id") String id){
    	System.out.println("start search");   	
        // ユーザーデータを取得し、取得できなければそのまま返す
    	java.util.Map<String, Object> oneUser;
	    oneUser = userService.select(id);
		this.userData = new UserData((String) oneUser.get("id"),(String) oneUser.get("name"),(String) oneUser.get("birthday"));

        return getJson(userData);
    }
    
    
    /**
     * 引数の検索IDに対応するDemoFormデータを取得する
     * @param id 検索ID
     * @return DemoFormデータ(JSON形式)
     */
    @GetMapping("/update")
    @ResponseBody
    public String update(@RequestParam("inputname") String inputname){
    	System.out.println("start update");
    	try {
    		System.out.println("名前" + inputname);
    		this.userData.setName(inputname);
			userService.update(userData);
			return getJson(userData);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println("例外発生:" + e.toString());
			return null;
		}
    }    

    /**
     * 引数の検索IDに対応するDemoFormデータを取得する
     * @param id 検索ID
     * @return DemoFormデータ(JSON形式)
     */
    @GetMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("inputid") String inputid){
    	System.out.println("start delete");
    	try {
    		System.out.println("ID" + inputid);
    		this.userData.setId(inputid);
			userService.delete(userData);
			return getJson(userData);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println("例外発生:" + e.toString());
			return null;
		}
    }    
    
    
    /**
     * 引数の文字列をエンコードする
     * @param data 任意の文字列
     * @return エンコード後の文字列
     */
    private String encode(String data){
        // 引数がnullまたは空文字の場合は、その値を返す
        if(StringUtils.isEmpty(data)){
            return data;
        }
        String retVal = null;
        try{
            retVal = URLEncoder.encode(data, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            System.err.println(e);
        }
        return retVal;
    }

    /**
     * 引数のUserDataオブジェクトをJSON文字列に変換する
     * @param userData UserDataオブジェクト
     * @return 変換後JSON文字列
     */
    private String getJson(UserData userData){
        String retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            retVal = objectMapper.writeValueAsString(userData);
        } catch (JsonProcessingException e) {
            System.err.println(e);
        }
        return retVal;
    }
}