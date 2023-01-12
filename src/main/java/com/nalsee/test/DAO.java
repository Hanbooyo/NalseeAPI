package com.nalsee.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;

@Repository
public class DAO {
	enum WeatherValue {
		TMP, PTY, POP, UUU, VEC, VVV, WSD, WAV, SKY, PCP, REH, SNO, TMX, TMN
    }
	
	public List<String> nalseeAPI() throws Exception {

        // 입력받을 weather 객체
        VO weather = new VO();
        // 변수 설정
        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
		// 홈페이지에서 받은 키
		String serviceKey = "He%2FIScsvFBeeDqm9YejpMBQ9vi6LkLBt9tSIuMaQ6fdlaxj9H7vsbcxwCOkp%2BpEIm3dC85GGbrBhH1D2mOcf1g%3D%3D";
		String nx = "60";	//위도
		String ny = "125";	//경도
		String baseDate = "20230112";	//조회하고싶은 날짜
		String baseTime = "1100";	//조회하고싶은 시간
		String type = "JSON";	//타입 xml, json 등등 ..
		String numOfRows = "72";	//한 페이지 결과 수 

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") +"="+numOfRows );    // 숫자 표
        urlBuilder.append("&" + URLEncoder.encode("pageNo=1", "UTF-8"));    // 페이지 수
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8")); // 받으려는 타입
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도

        URL url = new URL(urlBuilder.toString());
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result = sb.toString();

        System.out.println(result);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONObject parse_response = (JSONObject) jsonObject.get("response");
        JSONObject parse_body = (JSONObject) parse_response.get("body"); // response 로 부터 body 찾아오기
        JSONObject parse_items = (JSONObject) parse_body.get("items"); // body 로 부터 items 받아오기
        // items 로 부터 itemList : 뒤에 [ 로 시작하므로 jsonArray 이다.
        JSONArray parse_item = (JSONArray) parse_items.get("item");
        System.out.println("--------------------------");

        // item 들을 담은 List 를 반복자 안에서 사용하기 위해 미리 명시
        JSONObject object;
        // item 내부의 category 를 보고 사용하기 위해서 사용
        String category;
        String value;
        String fcstTime;
        List<String> nalsee = new ArrayList<String>();

        // jsonArray를 반복자로 반복
        for (int temp = 0; temp < parse_item.size(); temp++) {
            object = (JSONObject) parse_item.get(temp);
            category = (String) object.get("category"); // item 에서 카테고리를 검색
            fcstTime = (String) object.get("fcstTime");

            // Error 발생할수도 있으며 받아온 정보를 double이 아니라 문자열로 읽으면 오류
            value = (String) object.get("fcstValue");

            WeatherValue weatherValue = WeatherValue.valueOf(category);
            

            switch (weatherValue) {
            	case TMP:
            		weather.setTMP(value);
            		nalsee.add("TMP:"+value);
            		break;
                case PTY:
                    weather.setPTY(value);
                    nalsee.add("PTY:"+value);
                    break;
                case PCP:
                    weather.setPCP(value);
                    nalsee.add("PCP:"+value);
                    break;
                case POP:
                    weather.setPOP(value);
                    nalsee.add("POP:"+value);
                    break;
                case UUU:
                    weather.setUUU(value);
                    nalsee.add("UUU:"+value);
                    break;
                case VEC:
                    weather.setVEC(value);
                    nalsee.add("VEC:"+value);
                    break;
                case VVV:
                    weather.setVVV(value);
                    nalsee.add("VVV:"+value);
                    break;
                case SKY:
                    weather.setSKY(value);
                    nalsee.add("SKY:"+value);
                    break;
                case WSD:
                    weather.setWSD(value);
                    nalsee.add("WSD:"+value);
                    break;
                case WAV:
                    weather.setWAV(value);
                    nalsee.add("WAV:"+value);
                    break;
                default:
                    break;
            }
        }
        weather.setDate(baseDate);
        weather.setTime(baseTime);
        // 잘 출력되는지 확인하고 싶으면 아래 주석 해제
        System.out.println("DAO 통과");
        System.out.println(nalsee);
        return nalsee;

    }
}