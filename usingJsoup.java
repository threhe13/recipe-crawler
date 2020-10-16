import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;

public class usingJsoup {
    public static void main(String[] args) throws IOException {

        String path = "https://www.10000recipe.com/recipe/list.html";

        //String path = br.readLine();
        JsoupFromStringEx je = new JsoupFromStringEx(path);


    }
}

class JsoupFromStringEx{
    public JsoupFromStringEx(String url) throws IOException {
        //Document document = Jsoup.connect(url).get();
        //String title = document.title();

        //String description = document.select("meta[name=description]").first().attr("content");
        //System.out.println("Description : " + description);

        //String keywords = document.select("meta[name=keywords]").first().attr("content");
        //System.out.println("Keywords : " + keywords);

        Document doc = Jsoup.connect(url).get();
        //System.out.println(doc);
        Elements contents = doc.select("ul.rcp_m_list2 li.common_sp_list_li"); //레시피 목록 파싱
        //System.out.println(contents);
/*
document.querySelector("#contents_area_full > ul > ul > li:nth-child(2) > div.common_sp_caption > div.common_sp_caption_tit.line2")
 */
        //사진 가져오기
        Elements imageFile = contents.select("div.common_sp_thumb");

        //음식이름 가져오기
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Elements foodName = contents.select("div.common_sp_caption").eq(i);

            String name = foodName.select("div.common_sp_caption_tit.line2").text();
            //list.add(name);

            //System.out.println(imageFile);

            //사진 저장하기
            String image_url = imageFile.eq(i).select("img").attr("src");
            //BufferedImage image = ImageIO.read(new File(image_url));
            //System.out.println(image_url);
            String save = "C:\\Users\\threh\\Downloads" +"\\"+name+".jpg";

            FileDownload(image_url, save);
        }
    }

    public String getFileName(String filepath){
        String[] parts = filepath.split("[/]");
        return parts[parts.length-1];
    }

    public void FileDownload(String url, String saveDir){
        try{
            URL u = new URL(url);
            FileOutputStream fos = new FileOutputStream(saveDir);
            InputStream is = u.openStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = is.read(buf)) > 0){
                fos.write(buf, 0, len);
            }
            fos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
//C:\Users\threh\Downloads
