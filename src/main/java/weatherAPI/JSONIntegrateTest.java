package weatherAPI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class JSONIntegrateTest {

    enum WeatherValue {
       TMP, PTY, POP, UUU, VEC, VVV, WSD, WAV, SKY, PCP
    }

    public static void main(String[] args) throws Exception {

        // �Է¹��� weather ��ü
        VO weather = new VO();
        // ���� ����
        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
		// Ȩ���������� ���� Ű
		String serviceKey = "He%2FIScsvFBeeDqm9YejpMBQ9vi6LkLBt9tSIuMaQ6fdlaxj9H7vsbcxwCOkp%2BpEIm3dC85GGbrBhH1D2mOcf1g%3D%3D";
		String nx = "60";	//����
		String ny = "125";	//�浵
		String baseDate = "20230110";	//��ȸ�ϰ���� ��¥
		String baseTime = "1700";	//��ȸ�ϰ���� �ð�
		String type = "JSON";	//Ÿ�� xml, json ��� ..
		String numOfRows = "153";	//�� ������ ��� �� 

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows=10", "UTF-8"));    // ���� ǥ
        urlBuilder.append("&" + URLEncoder.encode("pageNo=1", "UTF-8"));    // ������ ��
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8")); // �������� Ÿ��
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* ��ȸ�ϰ���� ��¥*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* ��ȸ�ϰ���� �ð� AM 02�ú��� 3�ð� ���� */
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //�浵
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //����

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
        JSONObject parse_body = (JSONObject) parse_response.get("body"); // response �� ���� body ã�ƿ���
        JSONObject parse_items = (JSONObject) parse_body.get("items"); // body �� ���� items �޾ƿ���
        // items �� ���� itemList : �ڿ� [ �� �����ϹǷ� jsonArray �̴�.
        JSONArray parse_item = (JSONArray) parse_items.get("item");
        System.out.println("--------------------------");

        // item ���� ���� List �� �ݺ��� �ȿ��� ����ϱ� ���� �̸� ���
        JSONObject object;
        // item ������ category �� ���� ����ϱ� ���ؼ� ���
        String category;
        String value;

        // jsonArray�� �ݺ��ڷ� �ݺ�
        for (int temp = 0; temp < parse_item.size(); temp++) {
            object = (JSONObject) parse_item.get(temp);
            category = (String) object.get("category"); // item ���� ī�װ��� �˻�

            // Error �߻��Ҽ��� ������ �޾ƿ� ������ double�� �ƴ϶� ���ڿ��� ������ ����
            value = (String) object.get("fcstValue");

            WeatherValue weatherValue = WeatherValue.valueOf(category);

            switch (weatherValue) {
            	case TMP:
            		weather.setTMP(value);
            		break;
                case PTY:
                    weather.setPTY(value);
                    break;
                case PCP:
                    weather.setPCP(value);
                    break;
                case POP:
                    weather.setPOP(value);
                    break;
                case UUU:
                    weather.setUUU(value);
                    break;
                case VEC:
                    weather.setVEC(value);
                    break;
                case VVV:
                    weather.setVVV(value);
                    break;
                case SKY:
                    weather.setSKY(value);
                    break;
                case WSD:
                    weather.setWSD(value);
                    break;
                case WAV:
                    weather.setWAV(value);
                    break;
                default:
                    break;
            }
        }
        weather.setDate(baseDate);
        weather.setTime(baseTime);
        // �� ��µǴ��� Ȯ���ϰ� ������ �Ʒ� �ּ� ����
        System.out.println(weather);

    }
}